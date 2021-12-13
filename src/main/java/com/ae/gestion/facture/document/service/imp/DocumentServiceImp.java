package com.ae.gestion.facture.document.service.imp;

import com.ae.gestion.facture.document.domaine.Document;
import com.ae.gestion.facture.document.helper.FileStorageProperties;
import com.ae.gestion.facture.document.repository.DocumentRepository;
import com.ae.gestion.facture.document.service.DocumentService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
@Log4j2
@AllArgsConstructor
public class DocumentServiceImp implements DocumentService {
    private final Path fileStorageLocation;
    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentServiceImp(FileStorageProperties fileStorageProperties,DocumentRepository documentRepository) throws Exception {
        this.documentRepository = documentRepository;
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new Exception("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public Document storeFile(MultipartFile file) throws Exception {
        log.info("Store file in the service" + file.getName());
        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
            }
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("api/document/downloadFile/")
                    .path(fileName)
                    .toUriString();
            Document document = new Document();
            document.setDocument(fileDownloadUri);

            return this.documentRepository.saveAndFlush(document);
        } catch (Exception ex) {
            throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public Resource loadFileAsResource(String fileName) throws Exception {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new Exception("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new Exception("File not found " + fileName, ex);
        }
    }

  @Override
  public Document getDocument(Long id) {
    return this.documentRepository.getOne(id);
  }
}
