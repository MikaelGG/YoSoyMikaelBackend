package com.yosoymikael.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "blog_comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_comment_id")
    private Long blogCommentId;

    @Column(name = "reviewers_full_name", nullable = false)
    private String reviewersfullName;

    @Column(name = "reviewers_mail")
    private String reviewersMail;

    @Column(name = "reviewers_comment", columnDefinition = "TEXT", nullable = false)
    private String reviewersComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id", nullable = false)
    @JsonBackReference
    private Blog blog;
}
