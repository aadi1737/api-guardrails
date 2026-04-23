package com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponseDTO {
    private Long postId;
    private int likeCount;
    private String message;
}
