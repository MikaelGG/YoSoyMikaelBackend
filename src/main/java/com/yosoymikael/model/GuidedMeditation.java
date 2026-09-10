package com.yosoymikael.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "guided_meditations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuidedMeditation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guided_meditation_id")
    private Long guidedMeditationId;

    @Column(name = "meditation_name", nullable = false)
    private String meditationName;

    @Column(name = "meditation_url_video")
    private String meditationUrlVideo;

    @Column(name = "meditation_description", columnDefinition = "TEXT")
    private String meditationDescription;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "meditation_available_platforms", columnDefinition = "jsonb")
    @Builder.Default
    private Map<String, String> meditationAvailablePlatforms = new HashMap<>();
}
