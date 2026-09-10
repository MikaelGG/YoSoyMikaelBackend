package com.yosoymikael.service;

import com.yosoymikael.exception.ResourceNotFoundException;
import com.yosoymikael.model.Music;
import com.yosoymikael.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicService {

    private final MusicRepository musicRepository;

    @Transactional(readOnly = true)
    public List<Music> getAllMusic() {
        return musicRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Music getMusicById(Long id) {
        return musicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Canción no encontrada con ID: " + id));
    }

    @Transactional
    public Music createMusic(Music music) {
        return musicRepository.save(music);
    }

    @Transactional
    public Music updateMusic(Long id, Music details) {
        Music existing = getMusicById(id);
        existing.setMusicNameSong(details.getMusicNameSong());
        existing.setMusicDescription(details.getMusicDescription());
        existing.setMusicVideoUrl(details.getMusicVideoUrl());
        existing.setMusicAvailablePlatforms(details.getMusicAvailablePlatforms());
        return musicRepository.save(existing);
    }

    @Transactional
    public void deleteMusic(Long id) {
        Music existing = getMusicById(id);
        musicRepository.delete(existing);
    }
}
