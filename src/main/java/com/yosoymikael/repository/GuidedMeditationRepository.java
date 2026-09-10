package com.yosoymikael.repository;

import com.yosoymikael.model.GuidedMeditation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuidedMeditationRepository extends JpaRepository<GuidedMeditation, Long> {
    List<GuidedMeditation> findByMeditationNameContainingIgnoreCase(String meditationName);
}
