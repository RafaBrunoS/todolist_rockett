package com.Brprojrocketset.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Brprojrocketset.todolist.utils.utils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskControler {

    @Autowired
    private ITeskRepositorio iteskRepositorio; // para armazenar os metodos

    @PostMapping("/")
    public ResponseEntity creat(@RequestBody TaskEntites taskEntites, HttpServletRequest request) {
        System.out.println("Chegou na conroller");
        var idUser = request.getAttribute("idUser");
        taskEntites.setIdUser((UUID) idUser);

        var currentDate = LocalDateTime.now();
        // 10/11/2023 - current
        // 10/10/1023 - start
        if (currentDate.isAfter(taskEntites.getInicioAt()) && currentDate.isAfter(taskEntites.getFimAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Data de inicio/ data termino deve ser maior que a atual");
        }

        if (taskEntites.getInicioAt().isAfter(taskEntites.getFimAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data de inicio deve ser menor que a de termino");
        }

        var task = this.iteskRepositorio.save(taskEntites);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @GetMapping("/")
    public List<TaskEntites> list(HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        var tasks = this.iteskRepositorio.findByIdUser((UUID) idUser);
        return tasks;
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskEntites taskEntites, @PathVariable UUID id,
            HttpServletRequest request) {
                var task = this.iteskRepositorio.findById(id).orElse(null);
                if(task == null){
                     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarefa não encontrada");
                }

                 var idUser = request.getAttribute("idUser");
                 if(!task.getIdUser().equals(idUser)){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario não tem permissão para alterar task");
                 }

                utils.copyNonNullProperties(taskEntites, task);
                 var taskupdate = this.iteskRepositorio.save(task);
                return ResponseEntity.ok().body((taskupdate));
    }
}