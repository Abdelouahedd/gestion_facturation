package com.ae.gestion.facture.document.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {
    String storeFile(MultipartFile file) throws Exception;
    Resource loadFileAsResource(String fileName) throws Exception;
}
