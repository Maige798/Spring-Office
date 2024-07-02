package com.springoffice.document.service;

import com.springoffice.document.entity.Document;
import com.springoffice.global.util.DataResult;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {
    DataResult<Document> uploadDocument(MultipartFile file, Document document);
}
