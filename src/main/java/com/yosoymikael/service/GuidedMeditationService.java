package com.yosoymikael.service;

import com.yosoymikael.exception.ResourceNotFoundException;
import com.yosoymikael.model.GuidedMeditation;
import com.yosoymikael.repository.GuidedMeditationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuidedMeditationService {

    private final GuidedMeditationRepository guidedMeditationRepository;

    @Transactional(readOnly = true)
    public List<GuidedMeditation> getAllMeditations() {
        return guidedMeditationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public GuidedMeditation getMeditationById(Long id) {
        return guidedMeditationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meditación guiada no encontrada con ID: " + id));
    }

    @Transactional
    public GuidedMeditation createMeditation(GuidedMeditation meditation) {
        return guidedMeditationRepository.save(meditation);
    }

    @Transactional
    public GuidedMeditation updateMeditation(Long id, GuidedMeditation details) {
        GuidedMeditation existing = getMeditationById(id);
        existing.setMeditationName(details.getMeditationName());
        existing.setMeditationUrlVideo(details.getMeditationUrlVideo());
        existing.setMeditationDescription(details.getMeditationDescription());
        existing.setMeditationAvailablePlatforms(details.getMeditationAvailablePlatforms());
        return guidedMeditationRepository.save(existing);
    }

    @Transactional
    public void deleteMeditation(Long id) {
        GuidedMeditation existing = getMeditationById(id);
        guidedMeditationRepository.delete(existing);
    }
}
