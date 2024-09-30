package com.project.Atividade.Backend.Framework.modules.users.domain.mapper;

import com.project.Atividade.Backend.Framework.modules.users.domain.dto.request.CreateUserDto;
import com.project.Atividade.Backend.Framework.modules.users.domain.dto.response.UserResponseDto;
import com.project.Atividade.Backend.Framework.modules.users.infra.database.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserMapper {

    public static User mappingToUserEntity(CreateUserDto userDto) {
        return new User(
                userDto.getName(),
                userDto.getCpf(),
                userDto.getAge()
        );
    }

    public static UserResponseDto mappingToUserResponse(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getAge(),
                user.getCpf()
                );
    }

    public static void mappingFromUpdateUserToUserEntity(CreateUserDto userDto ,User user) {
        user.setName(userDto.getName());
        user.setCpf(userDto.getCpf());
        user.setAge(userDto.getAge());

    }

    public static Map<String, Object> mappingPaginatedUsersToUserResponse(final Page<User> pagedUsers, List<UserResponseDto> users) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("users", users);
        response.put("current-page", pagedUsers.getNumber());
        response.put("total-items", pagedUsers.getTotalElements());
        response.put("total-pages", pagedUsers.getTotalPages());

        return response;
    }

}
