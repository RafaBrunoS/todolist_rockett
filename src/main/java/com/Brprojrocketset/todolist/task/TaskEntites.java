
/**
 * ID
 * Usuario (id_usuario)
 * Descrição
 * Titulo
 * Data inicio
 * Data termino
 * Prioridade
 */

package com.Brprojrocketset.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_task")
public class TaskEntites {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String descricao;

    @Column(length = 50)
    private String titulo;

    private LocalDateTime inicioAt;
    private LocalDateTime fimAt;
    private String prioridade;

    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime criacaoAt;

   
    public void setTitulo(String titulo) throws Exception{
        if(titulo.length()> 50){
          throw new Exception("O campo titulo deve ter no maximo 50 caracter");
        }
        this.titulo = titulo;
    }
}
