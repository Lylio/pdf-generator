package com.lylechristine.pdfgeneratorapp.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;

@Service
public class DocumentService {

    public void generatePdfFromDocx(String templatePath, String outputPath, Map<String, String> replacements) {
        try (FileInputStream fis = new FileInputStream(templatePath);
             FileOutputStream out = new FileOutputStream(outputPath)) {
            // Load the document from the template
            XWPFDocument doc = new XWPFDocument(fis);
            // Replace text placeholders in the document
            for (XWPFParagraph p : doc.getParagraphs()) {
                for (XWPFRun r : p.getRuns()) {
                    String text = r.getText(0);
                    for (Map.Entry<String, String> entry : replacements.entrySet()) {
                        text = text.replace(entry.getKey(), entry.getValue());
                    }
                    r.setText(text, 0);
                }
            }
            doc.write(out);
            // Convert the updated document to PDF
            convertToPdf(outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void convertToPdf(String docPath) {
        try (PDDocument pdfDoc = new PDDocument()) {
            // Logic to convert DOCX to PDF using PDFBox
            // This is a simplification; more detailed conversion logic might be needed
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
