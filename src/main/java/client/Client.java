package client;

import com.zzpublic.zwing.ViewFlow;
import com.zzpublic.zwing.Window;
//import core.Repository;
//
//import common.model.TodoList;
//import server.persistence.Persistence;
//import server.persistence.PersistenceJson;
import client.ui.MainView;

public class Client {

    public static void main(String[] args) {
        //Repository.buildItems();
//        Persistence persistence = new PersistenceJson();
//        //拿到json文件里的字符串
//        TodoList todoList = persistence.read();
//        if(todoList ==null){
//            todoList = new TodoList();
//            todoList.setTitle("todo");
//        }
        //Repository.todolist = todoList;
        MainView view = new MainView();
        ViewFlow viewFlow = new ViewFlow();
        viewFlow.push(view);
        Window window = new Window(viewFlow);
        window.setVisible(true);
        window.setResizable(true);
    }
}
