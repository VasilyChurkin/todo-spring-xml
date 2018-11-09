package ru.churkin.todo.optionalexample;

import org.springframework.stereotype.Component;

/**
 * Denis, 09.11.2018
 */
@Component
public class ConnectionPool {

    public void getConnection() {
        System.out.println("Received connection!");
    }
}
