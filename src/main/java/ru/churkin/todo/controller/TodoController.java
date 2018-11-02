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

import java.util.List;

@Controller
public class TodoController {

    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public String showAll(Model model) {
        model.addAttribute("allTodos", todoService.getAll());
        return "todo-list";
    }

    @RequestMapping(value = "/todos", method = RequestMethod.POST)
    public String add(@RequestParam String todoName, @RequestParam String todoDescription, Model model) {

        todoService.add(new Todo(todoName, todoDescription));
        model.addAttribute("allTodos", todoService.getAll());
        return "redirect:/todos";
    }

    @RequestMapping(value = "/todos/{id}", method = RequestMethod.GET)
    public String getTodo(@PathVariable Long id, Model model) {

        Todo todo = todoService.findById(id);
        model.addAttribute("todoForJsp", todo);
        return "todo-with-description";
    }

    @RequestMapping(value = "/updateTodo", method = RequestMethod.POST)
    public String update(@RequestParam Long id, @RequestParam String description) {

//        String description = request.getParameter("description");
        Todo todo = todoService.findById(id);
        todo.setDescription(description);
        todoService.update(todo);

        return String.format("redirect:/todos/%d", id);
    }

    @RequestMapping(value = "/deleteTodo/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {

        todoService.deleteById(id);

        return "redirect:/todos";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam String find, Model model) {
        List<Todo> foundedTodos = todoService.search(find);

        model.addAttribute("allTodos", foundedTodos);

        return "todo-list";
    }

}
