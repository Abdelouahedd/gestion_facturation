package com.ae.gestion.facture.document.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix = "file")
@Data
@AllArgsConstructor
public class FileStorageProperties {
    private String uploadDir;
}
