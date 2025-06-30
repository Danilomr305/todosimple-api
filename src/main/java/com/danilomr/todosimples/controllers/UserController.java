package com.danilomr.todosimples.controllers;

import com.danilomr.todosimples.models.User;
import com.danilomr.todosimples.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    // localhost:8080/user/ coloque o ID do usuario
    @GetMapping("/{id}")
    // RESPONSEENTITY -> Utilizar quando quiser trazer a reposta pro usuario
    public ResponseEntity<User> findByIdUsuario(@PathVariable Long id) {
        User user = this.userService.findByIdUsuario(id);
        return ResponseEntity.ok().body(user);
    }

    //ResponseEntity<VOID> pois so ta querendo cria algo e nao quer que retorne
    //RequestBody-> So pode ser usando no create e no update
    @PostMapping
    @Validated(User.CreateUser.class) // isso vai valida o User que chama do model Usere
    public ResponseEntity<Void> createUsuario(@Valid @RequestBody User user) {
        this.userService.createUsuario(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri(); //serve para mostrar a url de encontrar esse usuario
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated(User.UpdateUser.class)
    public ResponseEntity<Void> updateUsuario(@Valid @RequestBody User user, @PathVariable Long id) {
        user.setId(id);
        this.userService.updateUsuario(user);
        return  ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        this.userService.deleteUsuario(id);
        return  ResponseEntity.noContent().build();

    }
}
