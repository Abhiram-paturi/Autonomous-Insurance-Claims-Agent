package com.fnol.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentParser {

    public String extractText(MultipartFile file) throws Exception {

        String name = file.getOriginalFilename().toLowerCase();

        if (name.endsWith(".txt")) {
            return new String(file.getBytes());
        }

        if (name.endsWith(".pdf")) {
            PDDocument doc = PDDocument.load(file.getInputStream());
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(doc);
            doc.close();
            return text;
        }

        throw new RuntimeException("Unsupported file type");
    }
}
