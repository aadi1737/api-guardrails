package com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRequestDTO {

    @NotNull(message = "author_id can't be null")
    private Long authorId;

    @NotBlank(message = "Author Type can't be null")
    private String authorType;

    @NotBlank(message = "Post content can't be null")
    private String content;
}
