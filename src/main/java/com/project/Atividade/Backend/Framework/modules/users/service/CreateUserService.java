package com.project.Atividade.Backend.Framework.modules.users.service;

import com.project.Atividade.Backend.Framework.modules.users.domain.dto.request.CreateUserDto;
import com.project.Atividade.Backend.Framework.modules.users.domain.dto.response.UserResponseDto;
import com.project.Atividade.Backend.Framework.modules.users.domain.mapper.UserMapper;
import com.project.Atividade.Backend.Framework.modules.users.infra.database.entity.User;
import com.project.Atividade.Backend.Framework.modules.users.infra.database.repository.UserRepository;
import com.project.Atividade.Backend.Framework.shared.consts.UserErrorMessages;
import com.project.Atividade.Backend.Framework.shared.exceptions.HttpBadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CreateUserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserResponseDto execute(CreateUserDto userDto) {
        Optional<User> userCpfExists = userRepository.findByCpf(userDto.getCpf());

        if(userCpfExists.isPresent()) {
            throw new HttpBadRequestException(UserErrorMessages.CPF_ALREADY_USED);
        }

        User user = UserMapper.mappingToUserEntity(userDto);

        userRepository.save(user);

        return UserMapper.mappingToUserResponse(user);

    }


}
