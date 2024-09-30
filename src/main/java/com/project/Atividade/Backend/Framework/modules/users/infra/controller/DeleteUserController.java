package com.project.Atividade.Backend.Framework.modules.users.infra.controller;

import com.project.Atividade.Backend.Framework.modules.users.service.DeleteUserService;
import com.project.Atividade.Backend.Framework.shared.exceptions.ApplicationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "users")
@RestController
@RequestMapping("/api/v1/users")
public class DeleteUserController {

    @Autowired
    private DeleteUserService deleteUserService;

    @Operation(summary = "Delete a existent user", description = "Resource that delete a existent user",
    responses = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApplicationException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid id inserted",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApplicationException.class)))
    })
    @DeleteMapping("/{id}")
    @CacheEvict(cacheNames = "users", allEntries = true)
    public ResponseEntity<Void> execute(
            @PathVariable("id")
            @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$")
            String id) {

        deleteUserService.execute(id);

        return ResponseEntity.noContent().build();
    }
}
