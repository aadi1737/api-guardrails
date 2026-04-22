package com.AadityaPrajapat.BackendAssignmentForAndazKumar.Service.Impl;

import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.PostRequestDTO;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.PostResponseDTO;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Model.Post;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Repository.PostRepository;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Service.PostService;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    public PostResponseDTO addPost(PostRequestDTO dto){
        Post post = new Post();
        post.setAuthorId(dto.getAuthorId());
        post.setAuthorType(dto.getAuthorType());
        post.setContent(dto.getContent());
        //post.setCreatedAt not required cause i have already added @CreatedTimeSTamp annotation on attribute
        Post saved ;
        try {
          saved = postRepository.save(post);
        }catch (Exception e){
            throw  new RuntimeException("Error While Saving Post in DB",e);
        }

        PostResponseDTO responseDTO = new PostResponseDTO();

        responseDTO.setId(saved.getId());
        responseDTO.setCreatedAt(saved.getCreatedAt());
        responseDTO.setContent(saved.getContent());
        responseDTO.setAuthorId(saved.getAuthorId());
        responseDTO.setAuthorType(saved.getAuthorType());

        return responseDTO;
    }
}
