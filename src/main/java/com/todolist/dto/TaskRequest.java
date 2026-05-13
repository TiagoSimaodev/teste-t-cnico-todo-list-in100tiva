package com.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para requisições de criação e atualização de tarefas.
 * Contém validações para garantir a integridade dos dados.
 */
public class TaskRequest {

    /**
     * Título da tarefa.
     * Campo obrigatório.
     */
    @NotBlank(message = "O título é obrigatório")
    @Size(max = 100, message = "O título deve ter no máximo 100 caracteres")
    private String title;

    /**
     * Descrição da tarefa.
     * Campo opcional.
     */
    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String description;

    /**
     * Status de conclusão da tarefa.
     * Campo opcional, padrão false.
     */
    private Boolean completed;

    // Construtores

    public TaskRequest() {}

    public TaskRequest(String title, String description, Boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    // Getters e Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}