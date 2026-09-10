package com.yosoymikael.service;

import com.yosoymikael.exception.ResourceNotFoundException;
import com.yosoymikael.model.Document;
import com.yosoymikael.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    @Transactional(readOnly = true)
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Document getDocumentById(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Documento no encontrado con ID: " + id));
    }

    @Transactional
    public Document createDocument(Document document) {
        return documentRepository.save(document);
    }

    @Transactional
    public Document updateDocument(Long id, Document details) {
        Document existing = getDocumentById(id);
        existing.setDocumentPDF(details.getDocumentPDF());
        existing.setDocumentDescription(details.getDocumentDescription());
        existing.setDocumentAvailableDownloadPlatforms(details.getDocumentAvailableDownloadPlatforms());
        return documentRepository.save(existing);
    }

    @Transactional
    public void deleteDocument(Long id) {
        Document existing = getDocumentById(id);
        documentRepository.delete(existing);
    }
}
