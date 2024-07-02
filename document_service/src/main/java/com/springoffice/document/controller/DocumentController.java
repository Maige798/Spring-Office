package com.springoffice.document.controller;


import com.springoffice.global.util.DataResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/document")
public class DocumentController {
    @Value("${file.upload.dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public DataResult<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        System.out.println(file);
        if (file.isEmpty()) {
            return DataResult.error("Please send a file");
        }
        try {
            File uploadedFile = new File(uploadDir + file.getOriginalFilename());
            file.transferTo(uploadedFile);
            return DataResult.ok("File uploaded successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return DataResult.error("File upload failed!");
        }
    }
}
