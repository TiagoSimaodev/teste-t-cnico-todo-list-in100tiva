package com.todolist.exception;

/**
 * Exceção customizada lançada quando uma tarefa não é encontrada.
 * Extende RuntimeException para ser uma exceção não verificada.
 */
public class TaskNotFoundException extends RuntimeException {

    /**
     * Construtor que recebe a mensagem de erro.
     * @param message Mensagem descritiva do erro.
     */
    public TaskNotFoundException(String message) {
        super(message);
    }

    /**
     * Construtor que recebe a mensagem e a causa do erro.
     * @param message Mensagem descritiva do erro.
     * @param cause Causa raiz do erro.
     */
    public TaskNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}