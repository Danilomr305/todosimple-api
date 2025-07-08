package com.danilomr.todosimples.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Colocar todos esse para que assim o lombok comece a funcionar
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
// @Data Se eu implementa o Data e ja poderia subtitle todos a cima e ainda ele implentando outro para future Bugs
//
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Task> tasks = new ArrayList<Task>();

}
