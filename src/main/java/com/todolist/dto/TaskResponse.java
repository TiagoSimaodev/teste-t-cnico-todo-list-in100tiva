package com.todolist.dto;

/**
 * DTO para respostas das operações com tarefas.
 * Não contém validações, pois é usado apenas para retorno de dados.
 */
public class TaskResponse {

    /**
     * Identificador único da tarefa.
     */
    private Long id;

    /**
     * Título da tarefa.
     */
    private String title;

    /**
     * Descrição da tarefa.
     */
    private String description;

    /**
     * Status de conclusão da tarefa.
     */
    private Boolean completed;

    // Construtores

    public TaskResponse() {}

    public TaskResponse(Long id, String title, String description, Boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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