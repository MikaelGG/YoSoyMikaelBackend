package com.yosoymikael.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "book_name", nullable = false)
    private String bookName;

    @Column(name = "book_image")
    private String bookImage;

    @Column(name = "book_description", columnDefinition = "TEXT")
    private String bookDescription;

    @Column(name = "autor")
    private String autor;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "book_available_shop_platforms", columnDefinition = "jsonb")
    @Builder.Default
    private Map<String, String> bookAvailableShopPlatforms = new HashMap<>();
}
