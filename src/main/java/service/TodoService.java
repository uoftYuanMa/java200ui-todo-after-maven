package service;

import core.Repository;
import core.TodoItem;
import core.TodoList;
import persistence.PersistenceJson;

public class TodoService {
    public TodoList get() {
        return Repository.todolist;
    }

    public void add(TodoItem item) {
        //数据存在core里
        Repository.todolist.add(item);
        PersistenceJson persistenceJson = new PersistenceJson();
        //Json中的数据是靠core里的数据刷出来的
        persistenceJson.save(Repository.todolist);
    }

    public void remove(int i){
        //点击删除按钮的时候，首先更改core中数据
        Repository.todolist.getItems().remove(i);
        PersistenceJson persistenceJson = new PersistenceJson();
        //更新Json数据
        persistenceJson.save(Repository.todolist);
    }
}
