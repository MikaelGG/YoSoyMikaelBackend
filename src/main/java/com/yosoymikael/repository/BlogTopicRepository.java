package com.yosoymikael.repository;

import com.yosoymikael.model.BlogTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogTopicRepository extends JpaRepository<BlogTopic, Long> {
    Optional<BlogTopic> findByTopicName(String topicName);
}
