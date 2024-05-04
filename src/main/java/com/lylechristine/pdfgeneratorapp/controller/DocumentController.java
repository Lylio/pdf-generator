package com.lylechristine.pdfgeneratorapp.controller;

import com.lylechristine.pdfgeneratorapp.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/generate-pdf")
    public ResponseEntity<String> generatePdf() {
        Map<String, String> replacements = new HashMap<>();
        replacements.put("{{placeholder}}", "actual data");
        documentService.generatePdfFromDocx("path/to/template.docx", "path/to/output.pdf", replacements);
        return ResponseEntity.ok("PDF generated successfully");
    }
}
