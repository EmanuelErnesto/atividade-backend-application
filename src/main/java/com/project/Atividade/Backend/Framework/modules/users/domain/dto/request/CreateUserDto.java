package com.project.Atividade.Backend.Framework.modules.users.domain.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

    @NotBlank
    @Size(min = 3, max = 150)
    private String name;

    @NotNull
    @Size(min = 11, max = 11)
    @Pattern(regexp = "^\\d{11}$", message = "O CPF deve conter exatamente 11 dígitos numéricos")
    private String cpf;

    @NotNull
    @Size(min = 1, max = 3)
    @Pattern(regexp = "^\\d{1,3}$", message = "A idade deve conter entre 1 e 3 dígitos numéricos")
    private String age;
}
