package common.model;

import common.model.TodoItem;

import java.util.LinkedList;
import java.util.List;
//项目列表TodoList
public class TodoList {
    //显示UI的顶级标题title
    private String title;
//    TodoList里面有一个属性 items 是 List<TodoItem>类的，说明用items承接所有子TodoItem
    private List<TodoItem> items = new LinkedList<>();

    public List<TodoItem> getItems() {
        return items;
    }

    public void add(TodoItem item){
        items.add(item);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
