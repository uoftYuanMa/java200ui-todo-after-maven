package core;
//该project的子显示全为TodoItem类
public class TodoItem {
    private String text;
    private int id;
    public TodoItem() {
    }

    public TodoItem(String text, int id) {
        this.text = text;
        this.id = id;
    }

    public TodoItem(String text) {
        this.text = text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
