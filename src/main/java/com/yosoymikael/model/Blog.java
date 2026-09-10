package com.yosoymikael.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "blogs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private Long blogId;

    @Column(name = "blog_name", nullable = false)
    private String blogName;

    @Column(name = "blog_image")
    private String blogImage;

    @Column(name = "blog_text", columnDefinition = "TEXT")
    private String blogText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_topic_id")
    @JsonBackReference
    private BlogTopic blogTopic;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Builder.Default
    private List<BlogComment> comments = new ArrayList<>();

    public void addComment(BlogComment comment) {
        comments.add(comment);
        comment.setBlog(this);
    }

    public void removeComment(BlogComment comment) {
        comments.remove(comment);
        comment.setBlog(null);
    }
}
