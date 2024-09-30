package com.project.Atividade.Backend.Framework.modules.users.service;

import com.project.Atividade.Backend.Framework.modules.users.infra.database.entity.User;
import com.project.Atividade.Backend.Framework.modules.users.infra.database.repository.UserRepository;
import com.project.Atividade.Backend.Framework.shared.consts.UserErrorMessages;
import com.project.Atividade.Backend.Framework.shared.exceptions.HttpNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DeleteUserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void execute(String id) {
        User user = userRepository.findById(UUID.fromString(id))
                .orElseThrow(() ->
                        new HttpNotFoundException(UserErrorMessages.USER_NOT_FOUND));

        userRepository.delete(user);

    }

}
