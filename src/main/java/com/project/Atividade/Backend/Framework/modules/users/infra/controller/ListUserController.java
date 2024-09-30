    package com.project.Atividade.Backend.Framework.modules.users.infra.controller;

    import com.project.Atividade.Backend.Framework.modules.users.domain.dto.response.UserResponseDto;
    import com.project.Atividade.Backend.Framework.modules.users.service.ListUserService;
    import io.swagger.v3.oas.annotations.Operation;
    import io.swagger.v3.oas.annotations.media.ArraySchema;
    import io.swagger.v3.oas.annotations.media.Content;
    import io.swagger.v3.oas.annotations.media.Schema;
    import io.swagger.v3.oas.annotations.responses.ApiResponse;
    import io.swagger.v3.oas.annotations.tags.Tag;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.cache.annotation.CacheConfig;
    import org.springframework.cache.annotation.Cacheable;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.RestController;

    import java.util.Map;

    @Tag(name = "users")
    @RestController
    @RequestMapping("/api/v1/users")
    @CacheConfig(cacheNames = "users")
    public class ListUserController {

        @Autowired
        private ListUserService listUserService;

        @Operation(summary = "Return a list of paginated users", description = "Resource that return a list of users",
        responses = {
                @ApiResponse(responseCode = "200", description = "Users returned successfully",
                        content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class))))
        })
        @Cacheable
        @GetMapping
        public Map<String, Object> execute(
                @RequestParam(defaultValue = "0") final Integer pageNumber,
                @RequestParam(defaultValue = "5") final Integer size
        ) {

          return listUserService.execute(pageNumber, size);

        }



    }
