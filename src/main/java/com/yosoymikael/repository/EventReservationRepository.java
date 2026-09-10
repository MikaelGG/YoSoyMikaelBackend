package com.yosoymikael.repository;

import com.yosoymikael.model.EventReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventReservationRepository extends JpaRepository<EventReservation, Long> {
    List<EventReservation> findByEvent_EventId(Long eventId);
    List<EventReservation> findByReservatorMail(String reservatorMail);
}
