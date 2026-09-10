package com.yosoymikael.repository;

import com.yosoymikael.model.MedicinalPlantReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicinalPlantReservationRepository extends JpaRepository<MedicinalPlantReservation, Long> {
    List<MedicinalPlantReservation> findByMedicinalPlant_MedicinalPlantId(Long medicinalPlantId);
    List<MedicinalPlantReservation> findByPatientsMail(String patientsMail);
}
