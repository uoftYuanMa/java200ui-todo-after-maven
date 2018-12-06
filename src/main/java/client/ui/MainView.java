package client.ui;

import com.zzpublic.zwing.Button;
import com.zzpublic.zwing.Label;
import com.zzpublic.zwing.TextField;
import com.zzpublic.zwing.View;
import common.model.TodoItem;
import common.model.TodoList;
import client.service.TodoService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
// “////”代表deprecated
//mainview 调用 client service
public class MainView extends View {
    //list
    private TodoList list;
    //显示之间的间距
    private final static int paddingNormal = 10;
    private final static int paddingSmall = 5;
    //每个显示的height
    private final static int cellHeight = 30;
    private final static int panelHeight = 40;
    //我们可以创建多个View（托盘），作为多个托盘
    private View containerView;
    //按钮
    private Button addButton;
    //可输入模块
    private TextField textField;
    //显示页面总标题
    private Label titleLabel;

    //private Persistence server.persistence = new PersistenceJson();
    private TodoService service;
    int id = 0;

    @Override
    protected void init() {
        ////页面总高，宽，背景
        super.init();
        ////调用静态方法，list里面现在有一个加载好的有1个标题，及3个TodoItem
        ////list = Repository.todolist;
        //底下的两行调用了client的service的todoservice
        service  = new TodoService();
        list = service.get();
//        if(list.getItems() == null){
//            id = 0;
//        }else{
//            List<TodoItem> items = list.getItems();
//             id = items.get(items.size() - 1).getId()+1;
//        }
    }

    @Override
    protected void initSubviews() {
        super.initSubviews();
        //标题参数
        titleLabel = new Label();
        //左上角那一点的位置
        titleLabel.setLocation(paddingNormal, paddingNormal);
        //大小
        titleLabel.setSize(this.getWidth() - 2 * paddingNormal, cellHeight);
        //水平对齐
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //让MainView知道该页面加入了此组件
        this.add(titleLabel);
        //托盘
        View inputView = new View();
        inputView.setSize(this.getWidth(), panelHeight);
        inputView.setLocation(0, this.getHeight() - inputView.getHeight() - 38);
        //将托盘加入到根页面
        this.add(inputView);
        //将Button放到托盘上
        addButton = new Button("ADD");
        addButton.setSize(100, cellHeight);
        addButton.setLocation(this.getWidth() - paddingSmall - addButton.getWidth(), paddingSmall);
        inputView.add(addButton);
        //将支持输入的textField放到托盘上
        textField = new TextField();
        textField.setSize(addButton.getX() - 2 * paddingSmall, cellHeight);
        textField.setLocation(paddingSmall, paddingSmall);
        inputView.add(textField);
    }


    @Override
    protected void initEvents() {
        super.initEvents();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //  update core
                String text = textField.getText();
                TodoItem todoItem = new TodoItem();
                todoItem.setText(text);
                if (list.getItems() == null) {
                    id = 0;
                } else {
                    List<TodoItem> items = list.getItems();
                    id = items.get(items.size() - 1).getId() + 1;
                }
                todoItem.setId(id);
                service.add(todoItem);
                //list.add(todoItem);
                //  sync ui
                //  delete 3 label
                MainView.this.remove(containerView);
                //  add 4 label
                dataToView();
                //  clean ui
                textField.setText("");
                //  push data to disk
                //server.persistence.save(list);
                id++;
            }
        });

    }

    @Override
    protected void viewDidDisplay() {
        super.viewDidDisplay();
        dataToView();
    }

    //刷界面
    public void dataToView() {
        //你想显示的内容
        titleLabel.setText(list.getTitle());

        if (containerView != null) {
            this.remove(containerView);
        }

        containerView = new View();
        containerView.setLocation(0, titleLabel.getY() + titleLabel.getHeight() + paddingNormal);
        containerView.setSize(this.getWidth(), this.getHeight() - containerView.getY() - paddingNormal - panelHeight);
        this.add(containerView);

        int y = 0;
        for (TodoItem todoItem : list.getItems()) {
            Label label = new Label();
            label.setText(todoItem.getText());
            label.setLocation(paddingNormal, y);
            label.setSize(this.getWidth() - 2 * paddingNormal - 120, cellHeight);
            containerView.add(label);
//            y += label.getHeight() + paddingNormal;
            //button
            Button button = new Button("Delete");
            //与core建立联系
            button.setSubtitle(String.valueOf(todoItem.getId()));
            button.setLocation(2 * paddingNormal + label.getWidth(), y);
            button.setSize(80, cellHeight);
            y += label.getHeight() + paddingNormal;
            containerView.add(button);
            //设置监听
            setDeleteButtonListener(button);
        }
    }

    private void setDeleteButtonListener(Button button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int todoItemId = Integer.parseInt(button.getSubtitle());
                for (int i = 0; i < list.getItems().size(); i++) {
                    if (list.getItems().get(i).getId() == todoItemId) {
                        //list.getItems().remove(i);
                        service.remove(i);
                        MainView.this.remove(containerView);
                        dataToView();
                        //server.persistence.save(list);
                        break;
                    }
                }
            }
        });
    }
}