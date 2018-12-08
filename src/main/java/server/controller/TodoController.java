package server.controller;

import com.google.gson.Gson;
import common.model.TodoList;
import common.net.Response;
import server.service.TodoService;
import common.net.Request;

// decoding unmarshalling
public class TodoController {
    //request handler 导向controller对应代码进行decoding
    public Response get(Request request){
        TodoService todoService = new TodoService();
        //todoService.get();
        TodoList todoList = todoService.get();
        Response response = new Response(Response.statusOk);
        String json = new Gson().toJson(todoList);
        response.setData(json);
        return response;
    }
}
