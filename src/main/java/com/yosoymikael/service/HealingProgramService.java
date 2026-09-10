package com.yosoymikael.service;

import com.yosoymikael.exception.ResourceNotFoundException;
import com.yosoymikael.model.HealingProgram;
import com.yosoymikael.repository.HealingProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HealingProgramService {

    private final HealingProgramRepository healingProgramRepository;

    @Transactional(readOnly = true)
    public List<HealingProgram> getAllPrograms() {
        return healingProgramRepository.findAll();
    }

    @Transactional(readOnly = true)
    public HealingProgram getProgramById(Long id) {
        return healingProgramRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Programa de sanación no encontrado con ID: " + id));
    }

    @Transactional
    public HealingProgram createProgram(HealingProgram program) {
        return healingProgramRepository.save(program);
    }

    @Transactional
    public HealingProgram updateProgram(Long id, HealingProgram details) {
        HealingProgram existing = getProgramById(id);
        existing.setHealingImage(details.getHealingImage());
        existing.setHealingDescription(details.getHealingDescription());
        return healingProgramRepository.save(existing);
    }

    @Transactional
    public void deleteProgram(Long id) {
        HealingProgram existing = getProgramById(id);
        healingProgramRepository.delete(existing);
    }
}
