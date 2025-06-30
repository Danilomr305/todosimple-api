package com.danilomr.todosimples.services;

import com.danilomr.todosimples.models.User;
import com.danilomr.todosimples.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    //Busca pelo Id
    public User findByIdUsuario(Long id) {
        //OPTIONAL => Ajuda para se caso chama user existe mostra o user e se chama um nao existente so restorna uma String vazia
        //THIS -> foi coloca para que chamar so os atributos da class userRepository
        Optional<User> user = this.userRepository.findById(id);

        //Uma maneira de como fazer um thorw no JAVA
        return  user.orElseThrow(() -> new RuntimeException(
                "Usuário não encontrado! Id:" + id + ", Tipo: " + User.class.getName()
        ));
    }

    //2Criar um novo Usuario
    //TRANSACTIONAL -> usar sempre que for salvar algo no banco, ajudar para que seja salva tudo no banco e nao pela metade
    //TRANSACTIONAL -> Não usar em tudo
    @Transactional
    public  User createUsuario(User usuario) {
        usuario.setId(null); // Isso garante que vai zera o ID se caso existi e salva um novo, garantindo de evitar um problema futuro
        usuario = this.userRepository.save(usuario);
        return usuario;
    }

    //Atualização do Usuario
    @Transactional // -> Usar quando quando criar algo ou atualizar
    public  User updateUsuario(User usuario) {
        User newUsuario = findByIdUsuario(usuario.getId()); //Garanntido que o usuario que vai atualizar é existente
        newUsuario.setPassword(usuario.getPassword()); //Deixando que o usuario atualizer so uma senha e não podendo atuliza seu userName
        return this.userRepository.save(newUsuario);
    }

    //Ðeleta o Usurio
    public  User deleteUsuario(Long id) {
        findByIdUsuario(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir! Pois há entidades relacionadas");
        }
        return null;
    }
}
