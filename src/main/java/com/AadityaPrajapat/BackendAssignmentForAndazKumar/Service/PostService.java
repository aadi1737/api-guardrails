package com.AadityaPrajapat.BackendAssignmentForAndazKumar.Service;

import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.PostRequestDTO;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.PostResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface PostService {

    public PostResponseDTO addPost(PostRequestDTO dto);
}
