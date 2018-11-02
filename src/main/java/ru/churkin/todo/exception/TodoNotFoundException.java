package ru.churkin.todo.exception;

/**
 * Denis, 02.11.2018
 */
public class TodoNotFoundException extends RuntimeException {

    public TodoNotFoundException(String message) {
        super(message);
    }
}
