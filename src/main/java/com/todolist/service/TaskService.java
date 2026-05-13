package com.todolist.service;

import com.todolist.dto.TaskRequest;
import com.todolist.dto.TaskResponse;
import com.todolist.entity.Task;
import com.todolist.exception.TaskNotFoundException;
import com.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço para lógica de negócio relacionada às tarefas.
 * Contém as regras de negócio e coordena as operações entre controller e repository.
 */
@Service
public class TaskService {

    /**
     * Repositório para acesso aos dados das tarefas.
     */
    @Autowired
    private TaskRepository taskRepository;

    /**
     * Cria uma nova tarefa.
     * @param request DTO com os dados da tarefa a ser criada.
     * @return TaskResponse com os dados da tarefa criada.
     */
    public TaskResponse createTask(TaskRequest request) {
        // Converte o DTO para entidade
        Task task = new Task(request.getTitle(), request.getDescription(), request.getCompleted());

        // Salva no banco de dados
        Task savedTask = taskRepository.save(task);

        // Converte a entidade salva para DTO de resposta
        return convertToResponse(savedTask);
    }

    /**
     * Retorna todas as tarefas.
     * @return Lista de TaskResponse com todas as tarefas.
     */
    public List<TaskResponse> getAllTasks() {
        // Busca todas as tarefas no banco
        List<Task> tasks = taskRepository.findAll();

        // Converte cada entidade para DTO
        return tasks.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Retorna uma tarefa pelo ID.
     * @param id Identificador da tarefa.
     * @return TaskResponse com os dados da tarefa.
     * @throws TaskNotFoundException se a tarefa não for encontrada.
     */
    public TaskResponse getTaskById(Long id) {
        // Busca a tarefa pelo ID, lança exceção se não encontrar
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Tarefa não encontrada com ID: " + id));

        return convertToResponse(task);
    }

    /**
     * Atualiza uma tarefa existente.
     * @param id Identificador da tarefa a ser atualizada.
     * @param request DTO com os novos dados da tarefa.
     * @return TaskResponse com os dados atualizados.
     * @throws TaskNotFoundException se a tarefa não for encontrada.
     */
    public TaskResponse updateTask(Long id, TaskRequest request) {
        // Verifica se a tarefa existe
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Tarefa não encontrada com ID: " + id));

        // Atualiza os campos da tarefa existente
        existingTask.setTitle(request.getTitle());
        existingTask.setDescription(request.getDescription());
        existingTask.setCompleted(request.getCompleted() != null ? request.getCompleted() : existingTask.getCompleted());

        // Salva as alterações
        Task updatedTask = taskRepository.save(existingTask);

        return convertToResponse(updatedTask);
    }

    /**
     * Exclui uma tarefa pelo ID.
     * @param id Identificador da tarefa a ser excluída.
     * @throws TaskNotFoundException se a tarefa não for encontrada.
     */
    public void deleteTask(Long id) {
        // Verifica se a tarefa existe antes de excluir
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Tarefa não encontrada com ID: " + id);
        }

        // Exclui a tarefa
        taskRepository.deleteById(id);
    }

    /**
     * Método auxiliar para converter Task para TaskResponse.
     * @param task Entidade Task.
     * @return TaskResponse correspondente.
     */
    private TaskResponse convertToResponse(Task task) {
        return new TaskResponse(task.getId(), task.getTitle(), task.getDescription(), task.getCompleted());
    }
}