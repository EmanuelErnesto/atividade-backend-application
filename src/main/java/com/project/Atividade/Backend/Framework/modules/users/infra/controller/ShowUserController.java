package com.project.Atividade.Backend.Framework.modules.users.infra.controller;

import com.project.Atividade.Backend.Framework.modules.users.domain.dto.response.UserResponseDto;
import com.project.Atividade.Backend.Framework.modules.users.service.ShowUserService;
import com.project.Atividade.Backend.Framework.shared.exceptions.ApplicationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@Tag(name = "users")
@RestController
@RequestMapping("/api/v1/users")
public class ShowUserController {

    @Autowired
    private ShowUserService showUserService;

    @Operation(summary = "Show a existent user", description = "Resource that return a existent user",
    responses = {
            @ApiResponse(responseCode = "200", description = "User returned successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApplicationException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApplicationException.class))
            )
    })
    @GetMapping("/{id}")
    @Cacheable(cacheNames = "users")
    public UserResponseDto execute(
            @PathVariable("id")
            @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$")
            String id) {

        return showUserService.execute(id);
    }

}
