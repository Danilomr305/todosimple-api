package com.danilomr.todosimples.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

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

    // CONSTRUTOR PADRÃO SEM ARGUMENTOS - ADICIONE ESTE!
    public Task() {
    }

    public Task(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Sempre que vou fazer usar esse como exemplo
    @Override
    public boolean equals(Object obj) {

        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof  Task))
            return false;
        Task other = (Task) obj;
        if (this.id == null)
            if (other.id != null)
                return false;
            else if (!this.id.equals(other.id))
                return false;
        return Objects.equals(this.id, other.id) && Objects.equals(this.user, other.user)
                && Objects.equals(this.description, other.description);
    }

    //Sempre que vou fazer usar esse como exemplo
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0: this.id.hashCode());
        return result;
    }
}
