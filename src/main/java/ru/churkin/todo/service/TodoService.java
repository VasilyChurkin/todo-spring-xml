package ru.churkin.todo.service;

import ru.churkin.todo.model.Todo;

import java.util.Collection;
import java.util.List;

public interface TodoService {

    void add(Todo todo);

    Todo findById(Long id);

    Collection<Todo> getAll();

    void update(Todo todo);

    void deleteById(Long id);

    List<Todo> search(String searchString);
}
