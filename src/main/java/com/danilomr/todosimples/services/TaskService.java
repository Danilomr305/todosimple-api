package com.danilomr.todosimples.services;

import com.danilomr.todosimples.models.Task;
import com.danilomr.todosimples.models.User;
import com.danilomr.todosimples.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    // Buscar as tarefas pelo Id
    public Task findByIdTarefa(Long id) {
        Optional<Task> task = this.taskRepository.findById(id);

        return task.orElseThrow(() -> new RuntimeException(
                "Tarefa não encontrada na Lista" + id + ", Tipo: " + Task.class.getName()
        ));
    }

    //Cria Tarefa
    @Transactional
    public Task createTarefa(Task tarefa) {
        // Verificando se o usuario existe
        User user = this.userService.findByIdUsuario(tarefa.getUser().getId());
        tarefa.setId(null);
        tarefa.setUser(user);
        tarefa = this.taskRepository.save(tarefa);
        return tarefa;
    }

    public Task updateTarefa(Task tarefa) {
        Task newTarefa = findByIdTarefa(tarefa.getId());
        newTarefa.setDescription(tarefa.getDescription());
        return  this.taskRepository.save(newTarefa);
    }

    public Task deletTarefa(Long id) {
        findByIdTarefa(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir! Pois há entidades relacionadas");
        }
        return  null;
    }

    //Função para retorna todas as TAkS do User
    public List<Task> findAllByUserIdTarefa(Long userId) {
        List<Task> tasks = this.taskRepository.findByUserId(userId);
        return tasks;
    }
}
