package com.yosoymikael.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long documentId;

    @Column(name = "document_pdf", nullable = false)
    private String documentPDF;

    @Column(name = "document_description", columnDefinition = "TEXT")
    private String documentDescription;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "document_available_download_platforms", columnDefinition = "jsonb")
    @Builder.Default
    private Map<String, String> documentAvailableDownloadPlatforms = new HashMap<>();
}
