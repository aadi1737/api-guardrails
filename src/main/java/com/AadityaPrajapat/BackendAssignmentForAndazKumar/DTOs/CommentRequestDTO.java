package com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs;


import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Enums.AuthorType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequestDTO {

    @NotBlank(message = "Content can't be null in Comment")
    private String content;

    @NotNull(message = "AuthorId can't be null.")
    private Long authorId;

    @NotBlank(message = "Author Type can't be blank.Specify Author Type(bot/human).")
    private AuthorType authorType;

    @NotNull(message = "Depth Level can't be null.(For new Comment Send : depthLevel=1)")
    private int depthLevel;
}
