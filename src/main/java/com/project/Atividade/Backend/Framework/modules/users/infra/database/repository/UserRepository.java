package com.project.Atividade.Backend.Framework.modules.users.infra.database.repository;

import com.project.Atividade.Backend.Framework.modules.users.infra.database.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value = "SELECT u from User u")
    Page<User> findAllWithPagination(Pageable pageable);
    Optional<User>  findByCpf(String cpf);
}
