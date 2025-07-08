package com.danilomr.todosimples.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;
// Colocar todos esse para que assim o lombok comece a funcionar
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

//
@Entity
@Table(name = Task.TABLE_NAME)
public class Task {

    public static final String TABLE_NAME = "tasks";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    //Isso pode definir que varias tarefas pode ser de um unico usuario
    @ManyToOne
    // Serve para que um usuario não mexar em um dado de outro usuario
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;


    @Column(name = "description", length = 255, nullable = false)
    @NotBlank
    @Size(min = 1, max = 255)
    private String description;
}
