package com.yosoymikael.repository;

import com.yosoymikael.model.HealingProgramReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealingProgramReservationRepository extends JpaRepository<HealingProgramReservation, Long> {
    List<HealingProgramReservation> findByHealingProgram_HealingProgramId(Long healingProgramId);
    List<HealingProgramReservation> findByPatientsMail(String patientsMail);
}
