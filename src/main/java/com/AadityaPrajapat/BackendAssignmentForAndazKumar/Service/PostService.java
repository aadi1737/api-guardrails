package com.AadityaPrajapat.BackendAssignmentForAndazKumar.Service;

import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.LikeRequestDTO;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.LikeResponseDTO;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.PostRequestDTO;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.PostResponseDTO;
import org.springframework.stereotype.Service;


public interface PostService {

    public PostResponseDTO addPost(PostRequestDTO dto);
    public LikeResponseDTO likeAPost(LikeRequestDTO dto,Long postId);
}
