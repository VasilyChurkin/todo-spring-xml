package ru.churkin.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.churkin.todo.model.Todo;
import ru.churkin.todo.service.TodoService;

import java.util.Collection;

/**
 * Denis, 02.11.2018
 */
@RestController
@RequestMapping("/api")
public class TodoRestController {

    private TodoService todoService;

    @Autowired
    public TodoRestController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping(value = "/todos")
//    @ResponseBody
    public Collection<Todo> getAll() {
        return todoService.getAll();
    }

    @GetMapping("/todos/{id}")
    public Todo get(@PathVariable Long id) {
        return todoService.findById(id);
    }

    @PostMapping("/todos")
    public ResponseEntity<Todo> add(@RequestBody Todo todo) {
        todoService.add(todo);
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }

    @PutMapping("/todos")
    public Todo update(@RequestBody Todo todo) {
        todoService.update(todo);
        return todo;
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        todoService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
