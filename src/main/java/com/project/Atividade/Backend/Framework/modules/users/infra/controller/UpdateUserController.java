package com.project.Atividade.Backend.Framework.modules.users.infra.controller;

import com.project.Atividade.Backend.Framework.modules.users.domain.dto.request.CreateUserDto;
import com.project.Atividade.Backend.Framework.modules.users.domain.dto.response.UserResponseDto;
import com.project.Atividade.Backend.Framework.modules.users.service.UpdateUserService;
import com.project.Atividade.Backend.Framework.shared.exceptions.ApplicationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

@Tag(name = "users")
@RestController
@RequestMapping("/api/v1/users")
public class UpdateUserController {

    @Autowired
    private UpdateUserService updateUserService;

    @Operation(summary = "Update a existent user", description = "Resource that can update a existent user",
    responses = {
            @ApiResponse(responseCode = "200", description = "User updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApplicationException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid id",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApplicationException.class))),
            @ApiResponse(responseCode = "400", description = "Cpf already used",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApplicationException.class)))
    })
    @PutMapping("/{id}")
    @CacheEvict(cacheNames = "users", allEntries = true)
    public UserResponseDto execute(
            @PathVariable("id")
            @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$")
            String id, @Valid @RequestBody CreateUserDto body) {

        return updateUserService.execute(id, body);

    }
}
