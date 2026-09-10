package com.yosoymikael.service;

import com.yosoymikael.exception.ResourceNotFoundException;
import com.yosoymikael.model.BlogTopic;
import com.yosoymikael.repository.BlogTopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogTopicService {

    private final BlogTopicRepository blogTopicRepository;

    @Transactional(readOnly = true)
    public List<BlogTopic> getAllTopics() {
        return blogTopicRepository.findAll();
    }

    @Transactional(readOnly = true)
    public BlogTopic getTopicById(Long id) {
        return blogTopicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tema de blog no encontrado con ID: " + id));
    }

    @Transactional
    public BlogTopic createTopic(BlogTopic topic) {
        return blogTopicRepository.save(topic);
    }

    @Transactional
    public BlogTopic updateTopic(Long id, BlogTopic details) {
        BlogTopic existing = getTopicById(id);
        existing.setTopicName(details.getTopicName());
        return blogTopicRepository.save(existing);
    }

    @Transactional
    public void deleteTopic(Long id) {
        BlogTopic existing = getTopicById(id);
        blogTopicRepository.delete(existing);
    }
}
