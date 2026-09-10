package com.yosoymikael.repository;

import com.yosoymikael.model.Journey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Long> {
    List<Journey> findByJourneyNameContainingIgnoreCase(String journeyName);
}
