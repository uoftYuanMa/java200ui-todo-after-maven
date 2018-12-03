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
        //Json对象，Json对象用一个大括号存储-> 即Json Map（Map中的值都应符合Json键值对）
        JsonObject listJson = new JsonObject();
        //该Json对象的属性，为键值对。 属性名->Json key 属性值->Json Value
        listJson.addProperty("title", todoList.getTitle());
        //JsonArray用中括号表示
        JsonArray itemsJson = new JsonArray();
        //Json对象添加属性，属性名是items，属性值用JsonArray存储
        listJson.add("items", itemsJson);
        List<TodoItem> items = todoList.getItems();
        for (TodoItem item : items) {
            //JsonArray中的元素用JsonMap表示
            JsonObject jsonObject = new JsonObject();
            //每一个map都有若干键值对组成
            jsonObject.addProperty("text", item.getText());
            jsonObject.addProperty("id", item.getId());
            //将该map添加到JsonArray中
            itemsJson.add(jsonObject);
        }
        //将构建好的Json转化为字符串
        String jsonString = new Gson().toJson(listJson);
        // string -> byte[]
        // memory -> disk
        // 自己手工创建一个 data 的文件夹，这条指令并不会创建文件夹
        File file = new File("data/todo.json");// new Path
        try {
            FileUtils.writeStringToFile(file, jsonString, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //初始化时候调用read
    //直接从指定文件目录读取数据生成TodoList
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
        //把Json中的items的 “值”以JsonArray读回来
        JsonArray itemsJson = jsonObject.getAsJsonArray("items");
        //JsonArray JsonObject 都继承JsonElement
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
