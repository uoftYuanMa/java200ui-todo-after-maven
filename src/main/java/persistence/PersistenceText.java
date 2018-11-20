package persistence;

import core.TodoItem;
import core.TodoList;

import java.util.List;

public class PersistenceText implements Persistence {
    @Override
    public void save(TodoList todoList) {
        StringBuilder builder = new StringBuilder();
        builder.append(todoList.getTitle() + "\n");

        List<TodoItem> items = todoList.getItems();
        for (TodoItem item : items) {
            builder.append(item.getText() + "\n");
        }

        System.out.println(builder.toString());
    }

    @Override
    public TodoList read() {
        return null;
    }
}
