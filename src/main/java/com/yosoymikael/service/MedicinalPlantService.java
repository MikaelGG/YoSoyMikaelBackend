package com.yosoymikael.service;

import com.yosoymikael.exception.ResourceNotFoundException;
import com.yosoymikael.model.MedicinalPlant;
import com.yosoymikael.repository.MedicinalPlantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicinalPlantService {

    private final MedicinalPlantRepository plantRepository;

    @Transactional(readOnly = true)
    public List<MedicinalPlant> getAllPlants() {
        return plantRepository.findAll();
    }

    @Transactional(readOnly = true)
    public MedicinalPlant getPlantById(Long id) {
        return plantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Planta medicinal no encontrada con ID: " + id));
    }

    @Transactional
    public MedicinalPlant createPlant(MedicinalPlant plant) {
        return plantRepository.save(plant);
    }

    @Transactional
    public MedicinalPlant updatePlant(Long id, MedicinalPlant details) {
        MedicinalPlant existing = getPlantById(id);
        existing.setMedicinalName(details.getMedicinalName());
        existing.setMedicinalImage(details.getMedicinalImage());
        existing.setMedicinalDescription(details.getMedicinalDescription());
        existing.setMedicinalPrice(details.getMedicinalPrice());
        existing.setMedicinalSpots(details.getMedicinalSpots());
        return plantRepository.save(existing);
    }

    @Transactional
    public void deletePlant(Long id) {
        MedicinalPlant existing = getPlantById(id);
        plantRepository.delete(existing);
    }
}
