package com.springoffice.document.service.service;

import com.springoffice.document.entity.Document;
import com.springoffice.document.mapper.DocumentMapper;
import com.springoffice.document.service.DocumentService;
import com.springoffice.global.util.DataResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.sql.Timestamp;

@Service("document-service")
public class DocumentServiceImpl implements DocumentService {
    @Value("${file.upload.dir}")
    private String uploadDir;
    @Resource
    private DocumentMapper documentMapper;

    @Override
    public DataResult<Document> uploadDocument(MultipartFile file, Document document) {
        DataResult<String> saveResult = saveFile(file, document.getUploaderId());
        System.out.println(saveResult);
        if (!saveResult.success()) {
            return DataResult.error("Document上传失败[" + saveResult.getMessage() + "]", document);
        }
        String documentPath = saveResult.unwrap();
        System.out.println(documentPath);
        document.setPath(documentPath);
        document.setUploadTime(new Timestamp(System.currentTimeMillis()));
        System.out.println(document);
        int resultValue = documentMapper.insert(document);
        if (resultValue <= 0) {
            return DataResult.error("Document保存失败", document);
        }
        return DataResult.ok("Document保存成功", document);
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
}
