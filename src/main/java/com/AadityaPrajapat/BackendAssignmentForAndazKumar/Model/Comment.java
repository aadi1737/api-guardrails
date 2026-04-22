package com.AadityaPrajapat.BackendAssignmentForAndazKumar.Model;

import java.time.LocalDateTime;

import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Enums.AuthorType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "author_id")
    private Long authorId;

    private String content;

    @Enumerated(EnumType.STRING)
    private AuthorType authorType;

    @Column(name = "depth_level")
    private Integer depthLevel;


    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

}
