package com.cmlcz.projects.its_backend.issue.dto.IssuePriority;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record IssuePriorityRequestDTO(

        @NotBlank()
        @Size(max = 50, message = "Name must be at most 50 characters")
        String name,

        @NotBlank()
        @Size(max = 255, message = "Description must be at most 255 characters")
        String description,

        @NotBlank()
        String color
) {
}
