package ru.churkin.todo.optionalexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Denis, 09.11.2018
 */
@Repository
public class UserRepository {

    private ConnectionPool connectionPool;

    @Autowired
    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>(Arrays.asList(
                new User("Vasya", "Churkin", 31),
                new User("Denis", "Bogdanov", 31)
        ));

        return users;
    }
}
