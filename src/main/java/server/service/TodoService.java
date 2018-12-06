package server.service;


import common.model.TodoItem;
import common.model.TodoList;
import server.persistence.Persistence;
import server.persistence.PersistenceJson;

public class TodoService {
    //调用persistence
    public TodoList get() {
        Persistence persistence = new PersistenceJson();
        //拿到json文件里的字符串
        TodoList todoList = persistence.read();
        if (todoList == null) {
            todoList = new TodoList();
            todoList.setTitle("todo");
        }
        return todoList;
    }

    public void add(TodoItem item) {
        ////数据存在core里
        ////Repository.todolist.add(item);
//        PersistenceJson persistenceJson = new PersistenceJson();
//        //Json中的数据是靠core里的数据刷出来的
//        persistenceJson.save(Repository.todolist);
    }

    public void remove(int i){
       // //点击删除按钮的时候，首先更改core中数据
      // // Repository.todolist.getItems().remove(i);
//        PersistenceJson persistenceJson = new PersistenceJson();
//        //更新Json数据
//        persistenceJson.save(Repository.todolist);
    }
}
