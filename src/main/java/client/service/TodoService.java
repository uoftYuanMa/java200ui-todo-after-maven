package client.service;

import client.net.RequestSender;
import com.google.gson.Gson;
import common.model.TodoItem;
import common.model.TodoList;
import common.net.Request;
import common.net.Response;

// service 生成 request 发到net的 requestSender上, service 包 隔离了UI加载和 网络请求
public class TodoService {
    public TodoList get() {
        Request request = new Request("todo get");
        RequestSender requestSender = new RequestSender();
        Response response = requestSender.send(request);
        if (!response.getStatus().equals(Response.statusOk)) {
            return null;
        }

        String json = response.getData();
        TodoList todoList = new Gson().fromJson(json, TodoList.class);

        return todoList;
    }

    public void add(TodoItem item) {

    }

    public void remove(int id){

    }
}
