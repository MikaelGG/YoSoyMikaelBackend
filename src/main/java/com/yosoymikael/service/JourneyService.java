package com.yosoymikael.service;

import com.yosoymikael.exception.ResourceNotFoundException;
import com.yosoymikael.model.Journey;
import com.yosoymikael.repository.JourneyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JourneyService {

    private final JourneyRepository journeyRepository;

    @Transactional(readOnly = true)
    public List<Journey> getAllJourneys() {
        return journeyRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Journey getJourneyById(Long id) {
        return journeyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viaje no encontrado con ID: " + id));
    }

    @Transactional
    public Journey createJourney(Journey journey) {
        return journeyRepository.save(journey);
    }

    @Transactional
    public Journey updateJourney(Long id, Journey details) {
        Journey existing = getJourneyById(id);
        existing.setJourneyName(details.getJourneyName());
        existing.setJourneyImage(details.getJourneyImage());
        existing.setJourneyDescription(details.getJourneyDescription());
        existing.setJourneyDate(details.getJourneyDate());
        existing.setJourneyPrice(details.getJourneyPrice());
        return existing; // Dirty checking de Hibernate lanza el UPDATE automaticamente
    }

    @Transactional
    public void deleteJourney(Long id) {
        Journey existing = getJourneyById(id);
        journeyRepository.delete(existing);
    }
}
