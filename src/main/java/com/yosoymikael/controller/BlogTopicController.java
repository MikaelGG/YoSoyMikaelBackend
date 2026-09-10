package com.yosoymikael.controller;

import com.yosoymikael.model.BlogTopic;
import com.yosoymikael.service.BlogTopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blog-topics")
@RequiredArgsConstructor
public class BlogTopicController {

    private final BlogTopicService blogTopicService;

    @GetMapping
    public ResponseEntity<List<BlogTopic>> getAll() {
        return ResponseEntity.ok(blogTopicService.getAllTopics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogTopic> getById(@PathVariable Long id) {
        return ResponseEntity.ok(blogTopicService.getTopicById(id));
    }

    @PostMapping
    public ResponseEntity<BlogTopic> create(@Valid @RequestBody BlogTopic topic) {
        return ResponseEntity.status(HttpStatus.CREATED).body(blogTopicService.createTopic(topic));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogTopic> update(@PathVariable Long id, @Valid @RequestBody BlogTopic topic) {
        return ResponseEntity.ok(blogTopicService.updateTopic(id, topic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        blogTopicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}
