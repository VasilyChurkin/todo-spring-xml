package ru.churkin.todo.service;

import org.springframework.stereotype.Service;
import ru.churkin.todo.dao.TodoDao;
import ru.churkin.todo.exception.TodoNotFoundException;
import ru.churkin.todo.model.Todo;

import java.util.Collection;
import java.util.List;

@Service
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
        if (todoFromDb == null) throw new TodoNotFoundException("Todo does not exist.");
        return todoFromDb;
    }

    @Override
    public Collection<Todo> getAll() {
        return todoDao.getAll();
    }

    @Override
    public void update(Todo todo) {
        if (!todoDao.update(todo)) throw new TodoNotFoundException("Todo with id=" + todo.getId() + " doesn't exist.");
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
