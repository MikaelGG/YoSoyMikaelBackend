package com.yosoymikael.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "live_classes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LiveClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "live_class_id")
    private Long liveClassId;

    @Column(name = "live_name", nullable = false)
    private String liveName;

    @Column(name = "live_url", nullable = false)
    private String liveUrl;
}
