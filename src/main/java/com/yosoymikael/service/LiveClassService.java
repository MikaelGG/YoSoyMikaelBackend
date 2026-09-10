package com.yosoymikael.service;

import com.yosoymikael.exception.ResourceNotFoundException;
import com.yosoymikael.model.LiveClass;
import com.yosoymikael.repository.LiveClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LiveClassService {

    private final LiveClassRepository liveClassRepository;

    @Transactional(readOnly = true)
    public List<LiveClass> getAllLiveClasses() {
        return liveClassRepository.findAll();
    }

    @Transactional(readOnly = true)
    public LiveClass getLiveClassById(Long id) {
        return liveClassRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clase en directo no encontrada con ID: " + id));
    }

    @Transactional
    public LiveClass createLiveClass(LiveClass liveClass) {
        return liveClassRepository.save(liveClass);
    }

    @Transactional
    public LiveClass updateLiveClass(Long id, LiveClass details) {
        LiveClass existing = getLiveClassById(id);
        existing.setLiveName(details.getLiveName());
        existing.setLiveUrl(details.getLiveUrl());
        return liveClassRepository.save(existing);
    }

    @Transactional
    public void deleteLiveClass(Long id) {
        LiveClass existing = getLiveClassById(id);
        liveClassRepository.delete(existing);
    }
}
