package com.yosoymikael.controller;

import com.yosoymikael.model.BlogComment;
import com.yosoymikael.service.BlogCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blog-comments")
@RequiredArgsConstructor
public class BlogCommentController {

    private final BlogCommentService commentService;

    @GetMapping
    public ResponseEntity<List<BlogComment>> getAll() {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogComment> getById(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
