package common.net;

import java.util.Arrays;

public class Request {
    private String action;
    private String data="";

    public Request() {
    }

    public Request(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
