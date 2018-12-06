package server.persistence;

import common.model.TodoList;

public interface Persistence {
    public void save(TodoList todoList);
    public TodoList read();
}
