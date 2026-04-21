package com.AadityaPrajapat.BackendAssignmentForAndazKumar.Model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "author_type")
    private String authorType;

    @Column(name = "depth_level")
    private int depthLevel;


    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
