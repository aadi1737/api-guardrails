package com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDTO {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private Long authorId;
    private String authorType;
}
