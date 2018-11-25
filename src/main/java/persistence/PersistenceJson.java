package persistence;

import core.TodoList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import core.TodoItem;
import core.TodoList;

import java.util.List;

public class PersistenceJson implements Persistence {
    @Override
    //以下构建Json
    /*
    {
        "title":"todo",
        "items":[
                  {"text"::""},
                  {"text"::""},
                  {"text"::""},
                ]
    }
    */

    public void save(TodoList todoList) {
        JsonObject listJson = new JsonObject();
        listJson.addProperty("title", todoList.getTitle());
        JsonArray itemsJson = new JsonArray();
        listJson.add("items", itemsJson);
        List<TodoItem> items = todoList.getItems();
        for (TodoItem item : items) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("text", item.getText());
            itemsJson.add(jsonObject);
        }
        String jsonString = new Gson().toJson(listJson);
    }

    @Override
    public TodoList read() {
        return null;
    }
}
