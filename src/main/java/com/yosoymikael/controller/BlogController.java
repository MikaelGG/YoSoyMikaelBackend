package com.yosoymikael.controller;

import com.yosoymikael.model.Blog;
import com.yosoymikael.model.BlogComment;
import com.yosoymikael.service.BlogService;
import com.yosoymikael.service.BlogCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;
    private final BlogCommentService commentService;

    @GetMapping
    public ResponseEntity<List<Blog>> getAll(@RequestParam(required = false) Long topicId) {
        if (topicId != null) {
            return ResponseEntity.ok(blogService.getBlogsByTopicId(topicId));
        }
        return ResponseEntity.ok(blogService.getAllBlogs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getById(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.getBlogById(id));
    }

    @PostMapping
    public ResponseEntity<Blog> create(@RequestParam(required = false) Long topicId, @Valid @RequestBody Blog blog) {
        return ResponseEntity.status(HttpStatus.CREATED).body(blogService.createBlog(topicId, blog));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> update(@PathVariable Long id, @RequestParam(required = false) Long topicId,
            @Valid @RequestBody Blog blog) {
        return ResponseEntity.ok(blogService.updateBlog(id, topicId, blog));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }

    // Comentarios del Blog
    @GetMapping("/{id}/comments")
    public ResponseEntity<List<BlogComment>> getComments(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getCommentsByBlogId(id));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<BlogComment> addComment(@PathVariable Long id, @Valid @RequestBody BlogComment comment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(id, comment));
    }
}
