package ru.churkin.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.churkin.todo.model.Todo;
import ru.churkin.todo.service.TodoService;

@Controller
public class TodoController {

    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(value = "/todos")
    public String showAll(Model model) {
        model.addAttribute("allTodos", todoService.getAll());
        return "todo-list";
    }

    @RequestMapping(value = "/todo", method = RequestMethod.POST)
    public String add(@RequestParam String todoName, @RequestParam String todoDescription, Model model) {

        todoService.add(new Todo(todoName, todoDescription));
        model.addAttribute("allTodos", todoService.getAll());
        return "redirect:/todos";
    }

    @RequestMapping(value = "/todos/{id}")
    public String getTodo(@PathVariable Long id, Model model) {

        Todo todo = todoService.findById(id);
        model.addAttribute("todoForJsp", todo);
        return "todo-with-description";
    }



}
