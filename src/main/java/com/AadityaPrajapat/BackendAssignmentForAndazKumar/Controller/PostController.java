package com.AadityaPrajapat.BackendAssignmentForAndazKumar.Controller;

import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.LikeRequestDTO;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.LikeResponseDTO;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.PostRequestDTO;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.PostResponseDTO;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Model.Post;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Repository.PostRepository;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Service.PostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "Yes Everything is Working good in PostController";
    }

    @PostMapping
    public ResponseEntity<?> addPost(@Valid @RequestBody PostRequestDTO postRequestDTO){
        //No need to check RequestBody cause @Valid
        PostResponseDTO responseDTO = postService.addPost(postRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<?> addLike(@Valid @RequestBody LikeRequestDTO dto,@PathVariable Long postId){
        LikeResponseDTO likeResponseDTO = postService.likeAPost(dto, postId);

        return ResponseEntity.ok(likeResponseDTO);
    }






}
