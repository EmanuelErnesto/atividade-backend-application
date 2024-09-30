package com.project.Atividade.Backend.Framework.modules.users.domain.dto.response;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class UserResponseDto implements Serializable {
    private UUID id;
    private String name;
    private String age;
    private String cpf;
}
