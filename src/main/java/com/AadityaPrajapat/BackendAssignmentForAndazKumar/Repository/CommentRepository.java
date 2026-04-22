package com.AadityaPrajapat.BackendAssignmentForAndazKumar.Repository;

import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
//    Optional<Comment> findByPostId(Long postId);
}
