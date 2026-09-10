package com.yosoymikael.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "music")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "music_id")
    private Long musicId;

    @Column(name = "music_name_song", nullable = false)
    private String musicNameSong;

    @Column(name = "music_description", columnDefinition = "TEXT")
    private String musicDescription;

    @Column(name = "music_video_url")
    private String musicVideoUrl;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "music_available_platforms", columnDefinition = "jsonb")
    @Builder.Default
    private Map<String, String> musicAvailablePlatforms = new HashMap<>();
}
