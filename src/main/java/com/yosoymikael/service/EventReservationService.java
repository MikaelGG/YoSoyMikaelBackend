package com.yosoymikael.service;

import com.yosoymikael.exception.ResourceNotFoundException;
import com.yosoymikael.model.Event;
import com.yosoymikael.model.EventReservation;
import com.yosoymikael.repository.EventRepository;
import com.yosoymikael.repository.EventReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventReservationService {

    private final EventReservationRepository reservationRepository;
    private final EventRepository eventRepository;

    @Transactional(readOnly = true)
    public List<EventReservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public EventReservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva de evento no encontrada con ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<EventReservation> getReservationsByEventId(Long eventId) {
        return reservationRepository.findByEvent_EventId(eventId);
    }

    @Transactional
    public EventReservation createReservation(Long eventId, EventReservation reservation) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado con ID: " + eventId));
        
        event.addReservation(reservation);
        return reservation;
    }

    @Transactional
    public EventReservation updateReservation(Long id, EventReservation details) {
        EventReservation existing = getReservationById(id);
        existing.setReservatorFullName(details.getReservatorFullName());
        existing.setReservatorPhone(details.getReservatorPhone());
        existing.setReservatorMail(details.getReservatorMail());
        existing.setReservatorAddress(details.getReservatorAddress());
        return existing;
    }

    @Transactional
    public void deleteReservation(Long id) {
        EventReservation existing = getReservationById(id);
        Event event = existing.getEvent();
        if (event != null) {
            event.removeReservation(existing);
        } else {
            reservationRepository.delete(existing);
        }
    }
}
