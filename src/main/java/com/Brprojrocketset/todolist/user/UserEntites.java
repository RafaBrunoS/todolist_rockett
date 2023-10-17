package com.Brprojrocketset.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_user")// Criação da tabela
public class UserEntites {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique= true)
    private String username;
        
    private String name;
    private String senha;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
