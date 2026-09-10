package com.yosoymikael.service;

import com.yosoymikael.exception.ResourceNotFoundException;
import com.yosoymikael.model.MedicinalPlant;
import com.yosoymikael.model.MedicinalPlantReservation;
import com.yosoymikael.repository.MedicinalPlantRepository;
import com.yosoymikael.repository.MedicinalPlantReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicinalPlantReservationService {

    private final MedicinalPlantReservationRepository reservationRepository;
    private final MedicinalPlantRepository plantRepository;

    @Transactional(readOnly = true)
    public List<MedicinalPlantReservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public MedicinalPlantReservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva de planta medicinal no encontrada con ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<MedicinalPlantReservation> getReservationsByPlantId(Long plantId) {
        return reservationRepository.findByMedicinalPlant_MedicinalPlantId(plantId);
    }

    @Transactional
    public MedicinalPlantReservation createReservation(Long plantId, MedicinalPlantReservation reservation) {
        MedicinalPlant plant = plantRepository.findById(plantId)
                .orElseThrow(() -> new ResourceNotFoundException("Planta medicinal no encontrada con ID: " + plantId));
        
        plant.addReservation(reservation);
        return reservation;
    }

    @Transactional
    public MedicinalPlantReservation updateReservation(Long id, MedicinalPlantReservation details) {
        MedicinalPlantReservation existing = getReservationById(id);
        existing.setPatientsFullName(details.getPatientsFullName());
        existing.setPatientsPhone(details.getPatientsPhone());
        existing.setPatientsMail(details.getPatientsMail());
        existing.setPatientsAddress(details.getPatientsAddress());
        return existing;
    }

    @Transactional
    public void deleteReservation(Long id) {
        MedicinalPlantReservation existing = getReservationById(id);
        MedicinalPlant plant = existing.getMedicinalPlant();
        if (plant != null) {
            plant.removeReservation(existing);
        } else {
            reservationRepository.delete(existing);
        }
    }
}
