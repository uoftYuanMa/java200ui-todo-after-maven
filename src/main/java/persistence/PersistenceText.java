package persistence;

import core.TodoItem;
import core.TodoList;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
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

        File file = new File("data/todo.txt");
        try {
            FileUtils.writeStringToFile(file,builder.toString(),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TodoList read() {
        return null;
    }
}
