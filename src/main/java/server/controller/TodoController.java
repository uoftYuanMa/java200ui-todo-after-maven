package server.controller;

import client.service.TodoService;
import common.net.Request;

// decoding unmarshalling
public class TodoController {
    //request handler 导向controller对应代码进行decoding
    public void get(Request request){
        TodoService todoService = new TodoService();
        todoService.get();
    }
}
