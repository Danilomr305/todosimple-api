package com.danilomr.todosimples.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    //Para ter o controller de Criação de Usuario
    public interface CreateUser{}

    //Para ter o controller de Atualizaão de Usuario
    public interface UpdateUser{}

    public static final String TABLE_NAME = "users";

    //Maneira padrão de como configura o ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    //Maneira padrão de como configura o Username
    @Column(name = "username", length = 100, nullable = false, unique = true)
    //@NotBlank(groups = CreateUser.class)
    @NotNull(groups = CreateUser.class)
    @NotEmpty (groups = CreateUser.class)//Server para o usuario nao deixa o campo vazio
    @Size(groups = CreateUser.class, min = 2, max = 100) //Serve ja para voce deixa o minimo e maximo de caractere quee o user deve coloca
    private String username;

    //Maneira padrão de como configura o Password
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Carante que a senha nunca retorne para o front-Ent
    //@NotBlank(groups = {CreateUser.class, UpdateUser.class})
    @Column(name = "password", length = 60, nullable = false)
    @NotNull(groups = {CreateUser.class, UpdateUser.class})
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class}) //Server para o usuario nao deixa o campo vazio
    @Size(groups = {CreateUser.class, UpdateUser.class}, min = 8, max = 60) //Serve ja para voce deixa o minimo e maximo de caractere quee o user deve coloca
    private String password;

    //MODELS TASK
    @OneToMany(mappedBy = "user")// Um usuario pode ter varios dados
    private List<Task> tasks = new ArrayList<Task>();

    public  User(){

    }

    public User(String password, String username, Long id) {
        this.password = password;
        this.username = username;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }


    //Sempre que vou fazer usar esse como exemplo
    @Override
    public boolean equals(Object obj) {

        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof  User))
            return false;
        User other = (User) obj;
        if (this.id == null)
            if (other.id != null)
                return false;
            else if (!this.id.equals(other.id))
                return false;
        return Objects.equals(this.id, other.id) && Objects.equals(this.username, other.username)
                && Objects.equals(this.password, other.password);
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
