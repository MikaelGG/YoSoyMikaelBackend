package com.yosoymikael.service;

import com.yosoymikael.exception.ResourceNotFoundException;
import com.yosoymikael.model.Blog;
import com.yosoymikael.model.BlogComment;
import com.yosoymikael.repository.BlogRepository;
import com.yosoymikael.repository.BlogCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogCommentService {

    private final BlogCommentRepository blogCommentRepository;
    private final BlogRepository blogRepository;

    @Transactional(readOnly = true)
    public List<BlogComment> getAllComments() {
        return blogCommentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public BlogComment getCommentById(Long id) {
        return blogCommentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario de blog no encontrado con ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<BlogComment> getCommentsByBlogId(Long blogId) {
        return blogCommentRepository.findByBlog_BlogId(blogId);
    }

    @Transactional
    public BlogComment createComment(Long blogId, BlogComment comment) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new ResourceNotFoundException("Artículo de blog no encontrado con ID: " + blogId));

        blog.addComment(comment);
        return comment;
    }

    @Transactional
    public void deleteComment(Long id) {
        BlogComment existing = getCommentById(id);
        Blog blog = existing.getBlog();
        if (blog != null) {
            blog.removeComment(existing);
        } else {
            blogCommentRepository.delete(existing);
        }
    }
}
