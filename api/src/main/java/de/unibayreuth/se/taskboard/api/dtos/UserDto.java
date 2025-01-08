package de.unibayreuth.se.taskboard.api.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

//Done: Add DTO for users.
@Data
public class UserDto {
    @Nullable
    private final UUID id; // user id is null when creating or update a new user
    @Nullable
    private final LocalDateTime createdAt; // is null when using DTO to create or update a new user
    @NotNull
    @NotBlank
    @Size(max = 255, message = "Name can be at most 255 characters long.")
    private final String name;
}
