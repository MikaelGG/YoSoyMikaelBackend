package com.yosoymikael.service;

import com.yosoymikael.exception.ResourceNotFoundException;
import com.yosoymikael.model.Journey;
import com.yosoymikael.model.JourneyReservation;
import com.yosoymikael.repository.JourneyRepository;
import com.yosoymikael.repository.JourneyReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JourneyReservationService {

    private final JourneyReservationRepository reservationRepository;
    private final JourneyRepository journeyRepository;

    @Transactional(readOnly = true)
    public List<JourneyReservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public JourneyReservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva de viaje no encontrada con ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<JourneyReservation> getReservationsByJourneyId(Long journeyId) {
        return reservationRepository.findByJourney_JourneyId(journeyId);
    }

    @Transactional
    public JourneyReservation createReservation(Long journeyId, JourneyReservation reservation) {
        Journey journey = journeyRepository.findById(journeyId)
                .orElseThrow(() -> new ResourceNotFoundException("Viaje no encontrado con ID: " + journeyId));
        
        journey.addReservation(reservation);
        return reservation;
    }

    @Transactional
    public JourneyReservation updateReservation(Long id, JourneyReservation details) {
        JourneyReservation existing = getReservationById(id);
        existing.setTravelersFullName(details.getTravelersFullName());
        existing.setTravelersPhone(details.getTravelersPhone());
        existing.setTravelersMail(details.getTravelersMail());
        existing.setTravelersAddress(details.getTravelersAddress());
        return existing;
    }

    @Transactional
    public void deleteReservation(Long id) {
        JourneyReservation existing = getReservationById(id);
        Journey journey = existing.getJourney();
        if (journey != null) {
            journey.removeReservation(existing);
        } else {
            reservationRepository.delete(existing);
        }
    }
}
