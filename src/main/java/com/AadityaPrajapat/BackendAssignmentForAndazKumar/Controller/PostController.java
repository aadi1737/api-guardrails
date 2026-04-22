package com.AadityaPrajapat.BackendAssignmentForAndazKumar.Controller;

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
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @GetMapping("/health-check-post")
    public String healthCheck(){
        return "Yes Everything is Working good in PostController";
    }

    @PostMapping("/posts")
    public ResponseEntity<?> addPost(@Valid @RequestBody PostRequestDTO postRequestDTO){
        //No need to check RequestBody cause @Valid

        PostResponseDTO responseDTO = postService.addPost(postRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }


    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDTO>> getAllPosts(){
        List<Post> postList = postRepository.findAll();
        List<PostResponseDTO> responseDTOList = new ArrayList<>();
        for(Post post:postList){
            responseDTOList.add(mapPost(post));
        }

        return ResponseEntity.ok(responseDTOList);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id){
        Post post = postRepository.findById(id).orElse(null);
        if (post == null){
            return  new ResponseEntity<>("Invalid Post ID", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(post,HttpStatus.OK);
        }
    }

    public static PostResponseDTO mapPost(Post post){
        return PostResponseDTO.builder()
                .authorId(post.getAuthorId())
                .content(post.getContent())
                .id(post.getId())
                .createdAt(post.getCreatedAt())
                .authorType(post.getAuthorType())
                .build();
    }


}
