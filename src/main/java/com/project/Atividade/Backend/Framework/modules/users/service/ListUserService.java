package com.project.Atividade.Backend.Framework.modules.users.service;

import com.project.Atividade.Backend.Framework.modules.users.domain.dto.response.UserResponseDto;
import com.project.Atividade.Backend.Framework.modules.users.domain.mapper.UserMapper;
import com.project.Atividade.Backend.Framework.modules.users.infra.database.entity.User;
import com.project.Atividade.Backend.Framework.modules.users.infra.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ListUserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Map<String, Object> execute(Integer pageNumber, Integer size) {
        Pageable pageable = PageRequest.of(pageNumber, size);

        Page<User> paginationData = userRepository.findAllWithPagination(pageable);

        List<UserResponseDto> usersFormatted = paginationData
                .getContent()
                .stream()
                .map(UserMapper::mappingToUserResponse)
                .toList();

        return UserMapper.mappingPaginatedUsersToUserResponse(paginationData, usersFormatted);

    }



}



