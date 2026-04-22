package com.AadityaPrajapat.BackendAssignmentForAndazKumar.Controller;

import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.CommentRequestDTO;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.CommentResponseDTO;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<?> addComment(@Valid @RequestBody CommentRequestDTO dto, @PathVariable Long postId){
        CommentResponseDTO responseDTO = commentService.addComment(dto, postId);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
}
