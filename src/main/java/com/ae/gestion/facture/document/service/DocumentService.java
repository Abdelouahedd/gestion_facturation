package com.ae.gestion.facture.document.service;

import com.ae.gestion.facture.document.domaine.Document;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {
    Document storeFile(MultipartFile file) throws Exception;
    Resource loadFileAsResource(String fileName) throws Exception;
}
