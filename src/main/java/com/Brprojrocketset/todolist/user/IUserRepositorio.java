package com.Brprojrocketset.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

// qual a classe a interface esta representando? UserEntites Qual tipo de ID da classe
public interface IUserRepositorio extends JpaRepository<UserEntites, UUID> {

    UserEntites findByUsername(String username);
}
