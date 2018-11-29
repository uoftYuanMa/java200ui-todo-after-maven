package persistence;

import com.google.gson.JsonElement;
import core.TodoList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import core.TodoItem;
import core.TodoList;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PersistenceJson implements Persistence {
    @Override
    //以下构建Json
    /*
    {
        "title":"todo",
        "items":[
                  {"text":""},
                  {"text":""},
                  {"text":""}
                ]
    }
    */
    //save是在add添加按钮触发时调用
    public void save(TodoList todoList) {
        JsonObject listJson = new JsonObject();
        listJson.addProperty("title", todoList.getTitle());
        JsonArray itemsJson = new JsonArray();
        listJson.add("items", itemsJson);
        List<TodoItem> items = todoList.getItems();
        for (TodoItem item : items) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("text", item.getText());
            jsonObject.addProperty("id", item.getId());
            itemsJson.add(jsonObject);
        }
        //将构建好的Json转化为字符串
        String jsonString = new Gson().toJson(listJson);
        // string -> byte[]
        // memory -> disk
        // 自己手工创建一个 data 的文件夹
        File file = new File("data/todo.json");// new Path
        try {
            FileUtils.writeStringToFile(file, jsonString, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //初始化时候调用read
    @Override
    public TodoList read() {
        // byte[] @ disk -> string @ memory
        File file = new File("data/todo.json");// new Path

        if (!file.exists()) {
            return null;
        }
        String data = "";
        try {
            data = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // string -> object
        JsonObject jsonObject = new Gson().fromJson(data, JsonObject.class);
        TodoList todoList = new TodoList();
        todoList.setTitle(jsonObject.get("title").getAsString());

        JsonArray itemsJson = jsonObject.getAsJsonArray("items");

        for (JsonElement itemJson : itemsJson) {
            TodoItem item = new TodoItem();
            String text = itemJson.getAsJsonObject().get("text").getAsString();
            int id = itemJson.getAsJsonObject().get("id").getAsInt();
            item.setText(text);
            item.setId(id);
            todoList.add(item);
        }
        return todoList;
    }
}
