package ru.churkin.todo.dao;

import ru.churkin.todo.model.Todo;

import java.util.Collection;
import java.util.List;

public interface TodoDao {

    void add(Todo todo);

    Todo findById(Long id);

    Collection<Todo> getAll();

    boolean update(Todo todo);

    void deleteById(Long id);

    List<Todo> search(String searchString);
}
