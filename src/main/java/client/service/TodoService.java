package client.service;

import client.net.RequestSender;
import common.model.TodoItem;
import common.model.TodoList;
import common.net.Request;
import common.net.Response;
import server.persistence.PersistenceJson;
// service 生成 request 发到net的 requestsender上
public class TodoService {
    public TodoList get() {
        Request request = new Request("todo get");
        RequestSender requestSender = new RequestSender();
        Response response = requestSender.send(request);
        return null;
    }

    public void add(TodoItem item) {

    }

    public void remove(int i){

    }
}
