package com.AadityaPrajapat.BackendAssignmentForAndazKumar.Repository;

import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
