import com.zzpublic.zwing.ViewFlow;
import com.zzpublic.zwing.Window;
import core.Repository;

import core.TodoItem;
import core.TodoList;
import persistence.Persistence;
import persistence.PersistenceJson;
import persistence.PersistenceText;
import ui.MainView;

import java.util.List;

public class Driver {

    public static void main(String[] args) {
        //Repository.buildItems();
        Persistence persistence = new PersistenceJson();
        //拿到json文件里的字符串
        TodoList todoList = persistence.read();
        if(todoList ==null){
            todoList = new TodoList();
            todoList.setTitle("todo");
        }
        Repository.todolist = todoList;
        MainView view = new MainView();
        ViewFlow viewFlow = new ViewFlow();
        viewFlow.push(view);
        Window window = new Window(viewFlow);
        window.setVisible(true);
        window.setResizable(true);
    }
}
