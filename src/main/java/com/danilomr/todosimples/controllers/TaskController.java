package com.danilomr.todosimples.controllers;

import com.danilomr.todosimples.models.Task;
import com.danilomr.todosimples.services.TaskService;
import com.danilomr.todosimples.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/task")
@Validated
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Task> findByIdTarefa(@PathVariable Long id){
        Task task = this.taskService.findByIdTarefa(id);
        return ResponseEntity.ok().body(task);
    }

    @PostMapping
    @Validated
    public  ResponseEntity<Void> createTarefa(@Valid @RequestBody Task task) {
        this.taskService.createTarefa(task);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(task.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> updateTarefa(@Valid@RequestBody Task task, @PathVariable Long id) {
        task.setId(id);
        this.taskService.updateTarefa(task);
        return  ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteTarefa(@PathVariable Long id) {
        this.taskService.deletTarefa(id);
        return  ResponseEntity.noContent().build();
    }


    //Função feita para retorna todas as tasks
    @GetMapping("user/{userId}")
    public  ResponseEntity<List<Task>> findAllByUserIdTarefa (@PathVariable Long userId) {
        userService.findByIdUsuario(userId); //serve para retorna a mensagem, se caso nao existi nenhum user
        List<Task> tasksId = this.taskService.findAllByUserIdTarefa(userId);
        return ResponseEntity.ok().body(tasksId);
    }
}
