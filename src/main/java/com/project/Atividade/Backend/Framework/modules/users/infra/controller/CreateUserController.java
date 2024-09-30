    package com.project.Atividade.Backend.Framework.modules.users.infra.controller;

    import com.project.Atividade.Backend.Framework.modules.users.domain.dto.request.CreateUserDto;
    import com.project.Atividade.Backend.Framework.modules.users.domain.dto.response.UserResponseDto;
    import com.project.Atividade.Backend.Framework.modules.users.service.CreateUserService;
    import com.project.Atividade.Backend.Framework.shared.exceptions.ApplicationException;
    import io.swagger.v3.oas.annotations.Operation;
    import io.swagger.v3.oas.annotations.media.Content;
    import io.swagger.v3.oas.annotations.media.Schema;
    import io.swagger.v3.oas.annotations.responses.ApiResponse;
    import io.swagger.v3.oas.annotations.tags.Tag;
    import jakarta.validation.Valid;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.cache.annotation.CacheEvict;
    import org.springframework.http.HttpStatus;
    import org.springframework.web.bind.annotation.*;

    @Tag(name = "users", description = "this endpoint can create, read, update and delete a user")
    @RestController
    @RequestMapping("/api/v1/users")
    public class CreateUserController {

        @Autowired
        private CreateUserService createUserService;

        @Operation(summary = "create a new user", description = "Resource for create a new user",
        responses = {
                @ApiResponse(responseCode = "201", description = "User create successfully.",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
                @ApiResponse(responseCode = "400", description = "Invalid field inserted.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApplicationException.class))),
                @ApiResponse(responseCode = "400", description = "Cpf already used",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApplicationException.class))),
        })
        @PostMapping
        @CacheEvict(cacheNames = "users", allEntries = true)
        @ResponseStatus(HttpStatus.CREATED)
        public UserResponseDto execute(@Valid @RequestBody CreateUserDto body) {
            return createUserService.execute(body);
        }
    }
