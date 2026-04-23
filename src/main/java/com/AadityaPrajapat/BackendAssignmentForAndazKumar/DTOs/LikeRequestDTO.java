package com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs;

import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Enums.AuthorType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeRequestDTO {

    @NotNull(message = "Author ID can't be null ,while liking a Post.")
    private Long authorId;

    @NotNull(message = "Author Type can't be null(Human/Bot)")
    private AuthorType authorType;
}
