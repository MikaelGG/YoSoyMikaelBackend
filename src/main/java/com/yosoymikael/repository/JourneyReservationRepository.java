package com.yosoymikael.repository;

import com.yosoymikael.model.JourneyReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JourneyReservationRepository extends JpaRepository<JourneyReservation, Long> {
    List<JourneyReservation> findByJourney_JourneyId(Long journeyId);
    List<JourneyReservation> findByTravelersMail(String travelersMail);
}
