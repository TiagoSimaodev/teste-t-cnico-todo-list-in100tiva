package com.todolist.repository;

import com.todolist.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a entidade Task.
 * Extende JpaRepository para fornecer operações CRUD básicas.
 * JpaRepository<Task, Long> indica que trabalha com Task e chave primária Long.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Não há métodos customizados necessários neste momento.
    // JpaRepository já fornece: save, findById, findAll, deleteById, etc.

}