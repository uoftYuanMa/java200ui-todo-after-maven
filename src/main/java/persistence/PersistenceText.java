package persistence;

import core.TodoItem;
import core.TodoList;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
//用自己写框架的方式实现持久化
//自己写格式
public class PersistenceText implements Persistence {
    @Override
    public void save(TodoList todoList) {
        //把传进来的TodeoList类传变成String
        StringBuilder builder = new StringBuilder();
        builder.append(todoList.getTitle() + "\n");

        List<TodoItem> items = todoList.getItems();
        for (TodoItem item : items) {
            builder.append(item.getText() + "\n");
        }
        //String 转 byte[] 并 由memory -> disc
        //此步并未创建新的文件夹，只是把路径添加进来
        File file = new File("data/todo.txt");
        try {
            //把builder里的东西存到file里
            FileUtils.writeStringToFile(file, builder.toString(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TodoList read() {
        //把disc上的byte[] 转换为 memory上的 String
        File file = new File("data/todo.txt");

        if(!file.exists()){
            return null;
        }
        String data = "";
        try {
            data = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] parts = data.split("\n");
        String title = parts[0];
        TodoList todoList = new TodoList();
        todoList.setTitle(title);
        for (int i = 1; i < parts.length; i++) {
            String todoText = parts[i];
            TodoItem todoItem = new TodoItem();
            todoItem.setText(todoText);
            todoList.add(todoItem);
        }
        return todoList;
    }
}
