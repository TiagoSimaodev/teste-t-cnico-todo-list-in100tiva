package com.todolist.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Entidade Task representa uma tarefa na aplicação To-Do List.
 * Esta classe é mapeada para a tabela 'task' no banco de dados.
 */
@Entity
@Table(name = "task")
public class Task {

    /**
     * Identificador único da tarefa.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Título da tarefa.
     * Campo obrigatório, não pode ser nulo ou vazio.
     * Máximo de 100 caracteres.
     */
    @NotBlank(message = "O título é obrigatório")
    @Size(max = 100, message = "O título deve ter no máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String title;

    /**
     * Descrição da tarefa.
     * Campo opcional.
     * Máximo de 500 caracteres.
     */
    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    @Column(length = 500)
    private String description;

    /**
     * Status de conclusão da tarefa.
     * Valor padrão é false (não concluída).
     */
    @Column(nullable = false)
    private Boolean completed = false;

    // Construtores

    /**
     * Construtor padrão necessário para JPA.
     */
    public Task() {}

    /**
     * Construtor com parâmetros para facilitar a criação de objetos Task.
     */
    public Task(String title, String description, Boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed != null ? completed : false;
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

    // toString, equals e hashCode (opcional, mas recomendado)

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                '}';
    }
}