package com.AadityaPrajapat.BackendAssignmentForAndazKumar.Controller;

import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.PostRequestDTO;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.PostResponseDTO;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/api/posts")
    public ResponseEntity<?> addPost(@Valid @RequestBody PostRequestDTO postRequestDTO){
        //No need to check RequestBody cause @Valid

        PostResponseDTO responseDTO = postService.addPost(postRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
