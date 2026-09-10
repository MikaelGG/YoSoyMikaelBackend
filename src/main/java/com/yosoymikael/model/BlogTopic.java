package com.yosoymikael.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "blog_topics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_topic_id")
    private Long blogTopicId;

    @Column(name = "topic_name", nullable = false)
    private String topicName;

    @OneToMany(mappedBy = "blogTopic", cascade = CascadeType.ALL)
    @JsonIgnore
    @Builder.Default
    private List<Blog> blogs = new ArrayList<>();

    public void addBlog(Blog blog) {
        blogs.add(blog);
        blog.setBlogTopic(this);
    }

    public void removeBlog(Blog blog) {
        blogs.remove(blog);
        blog.setBlogTopic(null);
    }
}
