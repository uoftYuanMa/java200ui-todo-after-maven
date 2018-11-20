import com.zzpublic.zwing.ViewFlow;
import com.zzpublic.zwing.Window;
import core.Repository;

import ui.MainView;
public class Driver {
    public static void main(String[] args) {
        Repository.buildItems();
        MainView view = new MainView();
        ViewFlow viewFlow = new ViewFlow();
        viewFlow.push(view);
        Window window = new Window(viewFlow);
        window.setVisible(true);
        window.setResizable(true);
    }
}
