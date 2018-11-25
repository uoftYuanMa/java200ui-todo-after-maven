import com.zzpublic.zwing.ViewFlow;
import com.zzpublic.zwing.Window;
import core.Repository;

import core.TodoList;
import persistence.Persistence;
import persistence.PersistenceText;
import ui.MainView;
public class Driver {
    public static void main(String[] args) {
        //Repository.buildItems();
        PersistenceText persistence = new PersistenceText();
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
