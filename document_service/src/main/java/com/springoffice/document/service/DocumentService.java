package com.springoffice.document.service;

import com.springoffice.document.entity.Document;
import com.springoffice.global.util.DataResult;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {
    ResponseEntity<InputStreamResource> downLoadFile(Integer id);

    DataResult<Document> uploadDocument(MultipartFile file, Document document);

    DataResult<Document> getDocumentById(Integer id);

    DataResult<Object> deleteDocument(Integer id);

    DataResult<List<Document>> getDocumentListByUserId(Integer userId);

    DataResult<Object> uploadToDepartment(Integer id, Integer deptId);

    DataResult<Object> removeFromDepartment(Integer id, Integer deptId);

    DataResult<List<Document>> getDepartmentDocumentList(Integer deptId);
}
