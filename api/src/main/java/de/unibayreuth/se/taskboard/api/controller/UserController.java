package de.unibayreuth.se.taskboard.api.controller;

import de.unibayreuth.se.taskboard.api.dtos.TaskDto;
import de.unibayreuth.se.taskboard.api.dtos.UserDto;
import de.unibayreuth.se.taskboard.api.mapper.UserDtoMapper;
import de.unibayreuth.se.taskboard.business.domain.User;
import de.unibayreuth.se.taskboard.business.exceptions.DuplicateNameException;
import de.unibayreuth.se.taskboard.business.exceptions.MalformedRequestException;
import de.unibayreuth.se.taskboard.business.exceptions.UserNotFoundException;
import de.unibayreuth.se.taskboard.business.ports.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@OpenAPIDefinition(
        info = @Info(
                title = "TaskBoard",
                version = "0.0.1"
        )
)
@Tag(name = "Users")
@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

//      TODO: (base) sebastian@bt-nac-g124 ase24-assignment05 %  curl --header "Content-Type: application/json" --request POST --data '{"name": "Denise"}' http://localhost:8080/api/users
//      {"timestamp":"2025-01-09T16:55:38.417+00:00","status":500,"error":"Internal Server Error","message":"createdAt is marked non-null but is null","path":"/api/users"}%

    private final UserService userService;
    private final UserDtoMapper userDtoMapper;
    // Done: Add GET /api/users endpoint to retrieve all users.
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getAll().stream()
                .map(userDtoMapper::fromBusiness)
                .toList());
    }

    // Done: Add GET /api/users/{id} endpoint to retrieve a user by ID.
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(
                    userDtoMapper.fromBusiness(userService.getById(id)));
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    // Done: Add POST /api/users endpoint to create a new user based on a provided user DTO.
    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto userDto) {
        try {
            return ResponseEntity.ok(
                    userDtoMapper.fromBusiness(
                            userService.create(
                                    userDtoMapper.toBusiness(userDto)
                            )
                    )
            );
        } catch (MalformedRequestException | DuplicateNameException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
