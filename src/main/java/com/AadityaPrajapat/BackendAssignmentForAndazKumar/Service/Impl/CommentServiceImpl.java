package com.AadityaPrajapat.BackendAssignmentForAndazKumar.Service.Impl;

import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.CommentRequestDTO;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.CommentResponseDTO;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Model.Comment;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Repository.CommentRepository;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentResponseDTO addComment(CommentRequestDTO dto, Long postId) {
        Comment comment = new Comment();
        comment.setAuthorId(dto.getAuthorId());
        comment.setContent(dto.getContent());
        comment.setAuthorType(dto.getAuthorType());
        comment.setPostId(postId);
        comment.setDepthLevel(dto.getDepthLevel());

        Comment saved = commentRepository.save(comment);

        return CommentResponseDTO.builder()
                .id(saved.getId())
                .createdAt(saved.getCreatedAt())
                .content(saved.getContent())
                .depthLevel(saved.getDepthLevel())
                .build();
    }
}
