package com.springoffice.document.controller;


import com.springoffice.document.entity.Document;
import com.springoffice.document.service.DocumentService;
import com.springoffice.global.util.DataResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/document")
public class DocumentController {
    @Resource
    private DocumentService documentService;

    @PostMapping("/upload")
    public DataResult<Document> handleFileUpload(
            @RequestParam(name = "file") MultipartFile file,
            @RequestParam(name = "uploader_id") Integer uploaderId,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "message", required = false) String message
    ) {
        return documentService.uploadDocument(file, handleParam(uploaderId, title, message));
    }

    private Document handleParam(Integer uploaderId, String title, String message) {
        Document document = new Document();
        document.setUploaderId(uploaderId);
        document.setTitle(title);
        document.setMessage(message);
        return document;
    }
}
