package com.springoffice.document.service.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.springoffice.document.client.UserClient;
import com.springoffice.document.entity.DepartmentRepository;
import com.springoffice.document.entity.Document;
import com.springoffice.document.entity.User;
import com.springoffice.document.mapper.DepartmentRepositoryMapper;
import com.springoffice.document.mapper.DocumentMapper;
import com.springoffice.document.service.DocumentService;
import com.springoffice.global.util.DataResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service("document-service")
public class DocumentServiceImpl implements DocumentService {
    @Value("${file.upload.dir}")
    private String uploadDir;
    @Resource
    private DocumentMapper documentMapper;
    @Resource
    private DepartmentRepositoryMapper repositoryMapper;
    @Resource
    private UserClient userClient;

    @Override
    public ResponseEntity<InputStreamResource> downLoadFile(Integer id) {
        DataResult<String> pathResult = getPathById(id);
        if (!pathResult.success()) {
            System.err.println(pathResult.getMessage());
            return null;
        }
        File file = new File(pathResult.unwrap());
        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", file.getName());
        headers.setContentLength(file.length());
        try {
            // 创建文件输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamResource resource = new InputStreamResource(fileInputStream);
            // 返回响应实体
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DataResult<Document> uploadDocument(MultipartFile file, Document document) {
        DataResult<String> saveResult = saveFile(file, document.getUploaderId());
        if (!saveResult.success()) {
            return DataResult.error("Document上传失败[" + saveResult.getMessage() + "]", document);
        }
        String documentPath = saveResult.unwrap();
        document.setPath(documentPath);
        document.setUploadTime(new Timestamp(System.currentTimeMillis()));
        int resultValue = documentMapper.insert(document);
        if (resultValue <= 0) {
            return DataResult.error("Document保存失败", document);
        }
        return DataResult.ok("Document保存成功", document);
    }

    @Override
    public DataResult<Document> getDocumentById(Integer id) {
        Document document = documentMapper.selectById(id);
        if (document == null) {
            return DataResult.error("Document查询失败，ID:" + id + "不存在");
        }
        loadUploaderName(document);
        return DataResult.ok("Document查询成功", document);
    }

    @Override
    public DataResult<Object> deleteDocument(Integer id) {
        int resultValue = documentMapper.deleteById(id);
        if (resultValue < 0) {
            return DataResult.error("Document删除失败");
        }
        return DataResult.ok("Document删除成功");
    }

    @Override
    public DataResult<List<Document>> getDocumentListByUserId(Integer userId) {
        LambdaQueryWrapper<Document> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Document::getUploaderId, userId);
        List<Document> documentList = documentMapper.selectList(wrapper);
        documentList.forEach(this::loadUploaderName);
        return DataResult.ok("Document list查询成功", documentList);
    }

    @Override
    public DataResult<Object> uploadToDepartment(Integer id, Integer deptId) {
        DepartmentRepository repository = new DepartmentRepository(deptId, id);
        int resultValue = repositoryMapper.insert(repository);
        if (resultValue <= 0) {
            return DataResult.error("部门文档上传失败");
        }
        return DataResult.ok("部门文档上传成功");
    }

    @Override
    public DataResult<Object> removeFromDepartment(Integer id, Integer deptId) {
        LambdaQueryWrapper<DepartmentRepository> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DepartmentRepository::getDeptId, deptId)
                .eq(DepartmentRepository::getDocumentId, id);
        int resultValue = repositoryMapper.delete(wrapper);
        if (resultValue <= 0) {
            return DataResult.error("部门文档移除失败");
        }
        return DataResult.ok("部门文档移除成功");
    }

    @Override
    public DataResult<List<Document>> getDepartmentDocumentList(Integer deptId) {
        LambdaQueryWrapper<DepartmentRepository> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DepartmentRepository::getDeptId, deptId);
        List<DepartmentRepository> repositoryList = repositoryMapper.selectList(wrapper);
        List<Document> documentList = new ArrayList<>();
        for (DepartmentRepository repository : repositoryList) {
            Document document = documentMapper.selectById(repository.getDocumentId());
            if (document == null) continue;
            documentList.add(document);
        }
        documentList.forEach(this::loadUploaderName);
        return DataResult.ok("Document list查询成功", documentList);
    }

    private DataResult<String> getPathById(Integer id) {
        Document document = documentMapper.selectById(id);
        if (document == null) {
            return DataResult.error("Document不存在，ID:" + id);
        }
        return DataResult.ok("查询Path成功", document.getPath());
    }

    private DataResult<String> saveFile(MultipartFile file, Integer uploaderId) {
        if (file == null || file.isEmpty()) {
            return DataResult.error("File不存在");
        }
        try {
            String folderPath = uploadDir + uploaderId;
            createFolder(folderPath);
            String path = folderPath + "/" + file.getOriginalFilename();
            File uploadedFile = new File(path);
            file.transferTo(uploadedFile);
            return DataResult.ok("File成功写入", path);
        } catch (Exception e) {
            e.printStackTrace();
            return DataResult.error("File写入失败");
        }
    }

    private void createFolder(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                System.out.println("目录已创建：" + directory.getAbsolutePath());
            } else {
                System.out.println("无法创建目录：" + directory.getAbsolutePath());
            }
        } else {
            System.out.println("目录已存在：" + directory.getAbsolutePath());
        }
    }

    private void loadUploaderName(Document document) {
        User user = userClient.getUserById(document.getUploaderId()).unwrap();
        if (user == null) {
            System.err.println("Document uploader ID:" + document.getUploaderId() + "不存在");
            return;
        }
        document.setUploaderName(user.getName());
    }
}
