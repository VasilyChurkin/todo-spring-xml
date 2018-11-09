package ru.churkin.todo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.churkin.todo.model.Todo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Denis, 09.11.2018
 */
@Repository
public class SpringJdbcTodoDaoImpl implements TodoDao {

    private TodoRowMapper todoRowMapper = new TodoRowMapper();

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SpringJdbcTodoDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(Todo todo) {
        jdbcTemplate.update("INSERT INTO todo (name, description) values (?, ?)", todo.getName(), todo.getDescription());
    }

    @Override
    public Todo findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM todo WHERE id=?", new Object[] {id}, todoRowMapper);
    }

    @Override
    public Collection<Todo> getAll() {
        return jdbcTemplate.query("SELECT * FROM todo", todoRowMapper);
    }

    @Override
    public boolean update(Todo todo) {
        return jdbcTemplate.update("UPDATE todo SET name=?, description=? WHERE id=?",
                todo.getName(), todo.getDescription(), todo.getId()) == 1;
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM todo WHERE id=?", id);
    }

    @Override
    public List<Todo> search(String searchString) {
        return jdbcTemplate.query("SELECT * FROM todo WHERE name LIKE UPPER(?)", todoRowMapper, "%" + searchString.toUpperCase() + "%");
    }

    private class TodoRowMapper implements RowMapper<Todo> {

        @Override
        public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Todo(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("description")
            );
        }
    }
}
