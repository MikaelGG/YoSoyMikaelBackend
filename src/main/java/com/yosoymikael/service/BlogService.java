package com.yosoymikael.service;

import com.yosoymikael.exception.ResourceNotFoundException;
import com.yosoymikael.model.Blog;
import com.yosoymikael.model.BlogTopic;
import com.yosoymikael.repository.BlogRepository;
import com.yosoymikael.repository.BlogTopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final BlogTopicRepository blogTopicRepository;

    @Transactional(readOnly = true)
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Blog getBlogById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artículo de blog no encontrado con ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<Blog> getBlogsByTopicId(Long topicId) {
        return blogRepository.findByBlogTopic_BlogTopicId(topicId);
    }

    @Transactional
    public Blog createBlog(Long topicId, Blog blog) {
        if (topicId != null) {
            BlogTopic topic = blogTopicRepository.findById(topicId)
                    .orElseThrow(() -> new ResourceNotFoundException("Tema de blog no encontrado con ID: " + topicId));
            topic.addBlog(blog);
        } else {
            blogRepository.save(blog);
        }
        return blog;
    }

    @Transactional
    public Blog updateBlog(Long id, Long topicId, Blog details) {
        Blog existing = getBlogById(id);
        existing.setBlogName(details.getBlogName());
        existing.setBlogImage(details.getBlogImage());
        existing.setBlogText(details.getBlogText());

        if (topicId != null) {
            BlogTopic topic = blogTopicRepository.findById(topicId)
                    .orElseThrow(() -> new ResourceNotFoundException("Tema de blog no encontrado con ID: " + topicId));
            if (existing.getBlogTopic() != null) {
                existing.getBlogTopic().removeBlog(existing);
            }
            topic.addBlog(existing);
        }
        return existing;
    }

    @Transactional
    public void deleteBlog(Long id) {
        Blog existing = getBlogById(id);
        BlogTopic topic = existing.getBlogTopic();
        if (topic != null) {
            topic.removeBlog(existing);
        }
        blogRepository.delete(existing);
    }
}
