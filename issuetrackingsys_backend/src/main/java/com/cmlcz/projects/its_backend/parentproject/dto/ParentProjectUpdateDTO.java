package com.cmlcz.projects.its_backend.parentproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ParentProjectUpdateDTO(
        @NotBlank(message = "Project name must not be blank")
        @Size(max=127, message = "Project name must be at most 127 characters")
        String projectName,
        @NotBlank(message = "Description must not be blank")
        @Size(max=255, message = "Description must be at most 255 characters")
        String description
){

}


