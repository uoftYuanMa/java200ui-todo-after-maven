package common.net;

public class Response {
    private String status;
    //json格式字符串
    private String data;

    public static final String statusOk = "ok";
    public static final String statusActionNotFound = "actionNotFound";

    public Response(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
