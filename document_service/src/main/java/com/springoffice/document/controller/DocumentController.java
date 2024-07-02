package com.springoffice.document.controller;


import com.springoffice.document.entity.DepartmentRepository;
import com.springoffice.document.entity.Document;
import com.springoffice.document.service.DocumentService;
import com.springoffice.global.util.DataResult;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {
    @Resource
    private DocumentService documentService;

    @GetMapping("/query")
    public DataResult<Document> getDocumentById(@RequestParam(name = "id") Integer id) {
        return documentService.getDocumentById(id);
    }

    @DeleteMapping("/delete/{id}")
    public DataResult<Object> deleteDocument(@PathVariable Integer id) {
        return documentService.deleteDocument(id);
    }

    @PostMapping("/personal/upload")
    public DataResult<Document> handleFileUpload(
            @RequestParam(name = "file") MultipartFile file,
            @RequestParam(name = "uploader_id") Integer uploaderId,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "message", required = false) String message
    ) {
        return documentService.uploadDocument(file, handleParam(uploaderId, title, message));
    }

    @GetMapping("/personal/list")
    public DataResult<List<Document>> getPersonalDocumentList(@RequestParam(name = "user_id") Integer userId) {
        return documentService.getDocumentListByUserId(userId);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable Integer id) {
        return documentService.downLoadFile(id);
    }

    @PostMapping("/department/upload")
    public DataResult<Object> uploadToDepartment(@RequestBody DepartmentRepository json) {
        return documentService.uploadToDepartment(json.getDocumentId(), json.getDeptId());
    }

    @PostMapping("/department/remove")
    public DataResult<Object> removeFromDepartment(@RequestBody DepartmentRepository json) {
        return documentService.removeFromDepartment(json.getDocumentId(), json.getDeptId());
    }

    @GetMapping("/department/list")
    public DataResult<List<Document>> getDepartmentDocumentList(@RequestParam(name = "dept_id") Integer deptId) {
        return documentService.getDepartmentDocumentList(deptId);
    }

    private Document handleParam(Integer uploaderId, String title, String message) {
        Document document = new Document();
        document.setUploaderId(uploaderId);
        document.setTitle(title);
        document.setMessage(message);
        return document;
    }
}
