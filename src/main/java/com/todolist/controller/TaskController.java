package com.todolist.controller;

import com.todolist.dto.TaskRequest;
import com.todolist.dto.TaskResponse;
import com.todolist.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para operações com tarefas.
 * Define os endpoints da API para CRUD de tarefas.
 */
@RestController
@RequestMapping("/tasks")
@Tag(name = "Tasks", description = "API para gerenciamento de tarefas")
public class TaskController {

    /**
     * Serviço para lógica de negócio das tarefas.
     */
    @Autowired
    private TaskService taskService;

    /**
     * Cria uma nova tarefa.
     * @param request DTO com os dados da tarefa.
     * @return ResponseEntity com a tarefa criada e status 201.
     */
    @PostMapping
    @Operation(summary = "Criar tarefa", description = "Cria uma nova tarefa com os dados fornecidos")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest request) {
        TaskResponse response = taskService.createTask(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Retorna todas as tarefas.
     * @return ResponseEntity com lista de tarefas e status 200.
     */
    @GetMapping
    @Operation(summary = "Listar tarefas", description = "Retorna todas as tarefas cadastradas")
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<TaskResponse> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    /**
     * Retorna uma tarefa pelo ID.
     * @param id Identificador da tarefa.
     * @return ResponseEntity com a tarefa encontrada e status 200.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar tarefa por ID", description = "Retorna uma tarefa específica pelo seu ID")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        TaskResponse task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    /**
     * Atualiza uma tarefa existente.
     * @param id Identificador da tarefa.
     * @param request DTO com os novos dados.
     * @return ResponseEntity com a tarefa atualizada e status 200.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar tarefa", description = "Atualiza os dados de uma tarefa existente")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequest request) {
        TaskResponse updatedTask = taskService.updateTask(id, request);
        return ResponseEntity.ok(updatedTask);
    }

    /**
     * Exclui uma tarefa pelo ID.
     * @param id Identificador da tarefa.
     * @return ResponseEntity com status 204 (No Content).
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir tarefa", description = "Exclui uma tarefa pelo seu ID")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}