package core;
//用来存储该项目的TodoList及其子TodoItem
public class Repository {
    public static TodoList todolist = new TodoList();
    //mock
    public static void buildItems(){
        todolist.setTitle("Todo");
        todolist.add(new TodoItem("HTML",0));
        todolist.add(new TodoItem("Git",1));
        todolist.add(new TodoItem("ZZ",2));
    }
}
