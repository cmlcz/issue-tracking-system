package com.cmlcz.projects.its_backend.user.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserDTO(
        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
        String username,

        @NotBlank(message = "Full name is required")
        @Size(max = 40, message = "Full name must be at most 40 characters")
        String fullName,

        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
        String password,

        @NotNull(message = "Role ID is required")
        Long roleId
){

}
