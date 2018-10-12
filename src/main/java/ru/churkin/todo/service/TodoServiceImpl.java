package ru.churkin.todo.service;

import ru.churkin.todo.dao.TodoDao;
import ru.churkin.todo.model.Todo;

import java.util.Collection;
import java.util.List;

public class TodoServiceImpl implements TodoService {

    private TodoDao todoDao;

    public TodoServiceImpl(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    @Override
    public void add(Todo todo) {
        todoDao.add(todo);
    }

    @Override
    public Todo findById(Long id) {
        Todo todoFromDb = todoDao.findById(id);
        if (todoFromDb == null) throw new RuntimeException("Todo does not exist.");
        return todoFromDb;
    }

    @Override
    public Collection<Todo> getAll() {
        return todoDao.getAll();
    }

    @Override
    public void update(Todo todo) {
        todoDao.update(todo);
    }

    @Override
    public void deleteById(Long id) {
        todoDao.deleteById(id);
    }

    @Override
    public List<Todo> search(String searchString) {
        return todoDao.search(searchString);
    }
}
