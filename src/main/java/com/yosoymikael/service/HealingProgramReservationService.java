package com.yosoymikael.service;

import com.yosoymikael.exception.ResourceNotFoundException;
import com.yosoymikael.model.HealingProgram;
import com.yosoymikael.model.HealingProgramReservation;
import com.yosoymikael.repository.HealingProgramRepository;
import com.yosoymikael.repository.HealingProgramReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HealingProgramReservationService {

    private final HealingProgramReservationRepository reservationRepository;
    private final HealingProgramRepository programRepository;

    @Transactional(readOnly = true)
    public List<HealingProgramReservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public HealingProgramReservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva de programa no encontrada con ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<HealingProgramReservation> getReservationsByProgramId(Long programId) {
        return reservationRepository.findByHealingProgram_HealingProgramId(programId);
    }

    @Transactional
    public HealingProgramReservation createReservation(Long programId, HealingProgramReservation reservation) {
        HealingProgram program = programRepository.findById(programId)
                .orElseThrow(() -> new ResourceNotFoundException("Programa de sanación no encontrado con ID: " + programId));
        
        // Al sincronizar con el helper y tener CascadeType.ALL, Hibernate persiste la reserva al finalizar la transaccion
        program.addReservation(reservation);
        return reservation;
    }

    @Transactional
    public HealingProgramReservation updateReservation(Long id, HealingProgramReservation details) {
        // En estado gestionado (managed entity), el Dirty Checking de Hibernate genera el UPDATE automaticamente
        HealingProgramReservation existing = getReservationById(id);
        existing.setPatientsFullName(details.getPatientsFullName());
        existing.setPatientsPhone(details.getPatientsPhone());
        existing.setPatientsMail(details.getPatientsMail());
        existing.setPatientsAddress(details.getPatientsAddress());
        return existing;
    }

    @Transactional
    public void deleteReservation(Long id) {
        HealingProgramReservation existing = getReservationById(id);
        HealingProgram program = existing.getHealingProgram();
        if (program != null) {
            // Al desvincular del padre con el helper, orphanRemoval = true ejecuta el DELETE en la BD
            program.removeReservation(existing);
        } else {
            reservationRepository.delete(existing);
        }
    }
}
