package com.project.Atividade.Backend.Framework.modules.users.infra.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "cpf", unique = true, length = 11)
    private String cpf;

    @Column(name = "age", length = 3)
    private String age;

    public User(String name, String cpf, String age){
        this.name = name;
        this.cpf = cpf;
        this.age = age;
    }
}
