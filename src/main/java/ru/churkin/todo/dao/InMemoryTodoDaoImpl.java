package ru.churkin.todo.dao;

import ru.churkin.todo.model.Todo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryTodoDaoImpl implements TodoDao {

    private static Map<Long, Todo> map = new ConcurrentHashMap<>();

    static {
        Todo todo1 = new Todo("1", "ok");
        todo1.setId(1L);
        Todo todo2 = new Todo("2", "okay");
        map.put(1L, todo1);
        todo2.setId(2L);
        map.put(2L, todo2);
    }

    private AtomicLong id = new AtomicLong(2);

    public void add(Todo todo) {
        todo.setId(id.incrementAndGet());
        map.put(todo.getId(), todo);
    }

    public Todo findById(Long id) {
        return map.get(id);
    }

    public Collection<Todo> getAll() {
        return map.values();
    }

    public boolean update(Todo todo) {
        Todo existingTodo = map.get(todo.getId());
        if (existingTodo == null) {
            return false;
        } else {
            map.put(todo.getId(), todo);
            return true;
        }
    }

    public void deleteById(Long id) {
        map.remove(id);
    }

    public List<Todo> search(String searchString) {
        List<Todo> result = new ArrayList<>();
        for (Map.Entry<Long, Todo> entry : map.entrySet()) {
            if (entry.getValue().getName().toLowerCase().contains(searchString.toLowerCase())) {
                result.add(entry.getValue());
            }
        }
        return result;
    }
}
