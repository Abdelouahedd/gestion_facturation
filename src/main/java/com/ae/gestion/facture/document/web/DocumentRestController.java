package com.ae.gestion.facture.document.web;

import com.ae.gestion.facture.document.domaine.Document;
import com.ae.gestion.facture.document.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api")
public class DocumentRestController {
    private final DocumentService documentService;

    @PostMapping("/document")
    public ResponseEntity<Document> uploadFile(@RequestParam("document") MultipartFile document) throws Exception {
        try {
            return ResponseEntity.ok(documentService.storeFile(document));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Problem lors de la creation du document");
        }
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws Exception {
        // Load file as Resource
        Resource resource = this.documentService.loadFileAsResource(fileName);
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.err.println("Could not determine file type.");
        }
        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


}
