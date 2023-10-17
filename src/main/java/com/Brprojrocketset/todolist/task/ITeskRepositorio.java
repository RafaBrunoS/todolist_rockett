package com.Brprojrocketset.todolist.task;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITeskRepositorio extends JpaRepository<TaskEntites, UUID> {
    List<TaskEntites>findByIdUser(UUID idUser);
    TaskEntites findByIdAndIdUser(UUID id, UUID idUser);
    
}
