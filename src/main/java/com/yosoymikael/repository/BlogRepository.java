package com.yosoymikael.repository;

import com.yosoymikael.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByBlogTopic_BlogTopicId(Long blogTopicId);

    List<Blog> findByBlogNameContainingIgnoreCase(String blogName);
}
