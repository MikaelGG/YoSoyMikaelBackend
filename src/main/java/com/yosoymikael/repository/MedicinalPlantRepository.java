package com.yosoymikael.repository;

import com.yosoymikael.model.MedicinalPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicinalPlantRepository extends JpaRepository<MedicinalPlant, Long> {
    List<MedicinalPlant> findByMedicinalNameContainingIgnoreCase(String medicinalName);
}
