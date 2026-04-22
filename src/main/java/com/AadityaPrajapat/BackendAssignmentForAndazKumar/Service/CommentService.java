package com.AadityaPrajapat.BackendAssignmentForAndazKumar.Service;

import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.CommentRequestDTO;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.CommentResponseDTO;

public interface CommentService {
    public CommentResponseDTO addComment(CommentRequestDTO dto, Long postId);
}
