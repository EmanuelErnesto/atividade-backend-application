package com.project.Atividade.Backend.Framework.modules.users.service;

import com.project.Atividade.Backend.Framework.modules.users.domain.dto.request.CreateUserDto;
import com.project.Atividade.Backend.Framework.modules.users.domain.dto.response.UserResponseDto;
import com.project.Atividade.Backend.Framework.modules.users.domain.mapper.UserMapper;
import com.project.Atividade.Backend.Framework.modules.users.infra.database.entity.User;
import com.project.Atividade.Backend.Framework.modules.users.infra.database.repository.UserRepository;
import com.project.Atividade.Backend.Framework.shared.consts.UserErrorMessages;
import com.project.Atividade.Backend.Framework.shared.exceptions.HttpBadRequestException;
import com.project.Atividade.Backend.Framework.shared.exceptions.HttpNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UpdateUserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserResponseDto execute(String id, CreateUserDto userData) {

        User userExists = userRepository.findById(UUID.fromString(id))
                .orElseThrow(() ->
                        new HttpNotFoundException(UserErrorMessages.USER_NOT_FOUND));


        User userCpfExists = userRepository.findByCpf(userData.getCpf()).orElse(null);

        if(userCpfExists != null && userCpfExists.getId() != userExists.getId()) {
            throw new HttpBadRequestException(UserErrorMessages.CPF_ALREADY_USED);
        }

        UserMapper.mappingFromUpdateUserToUserEntity(userData, userExists);

        return UserMapper.mappingToUserResponse(userExists);

    }

}
