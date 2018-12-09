package server.net;

import common.net.Request;
import common.net.Response;
import io.zzax.jadeite.net.Connector;
import server.controller.TodoController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//接受客户端请求
public class RequestHandler {
    public void run(){
        //
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            Socket socket = serverSocket.accept();
            handle(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void handle(Socket socket){
        Connector connector = new Connector(socket);
        String action = connector.readLine();
        String data = connector.readLine();
        //构建回request
        Request request = new Request(action);
        request.setData(data);
        //下一句话执行完 收到json格式的string
        Response response = route(request);
        connector.writeLine(response.getStatus());
        connector.writeLine(response.getData());
    }
    //解读request
    private Response route(Request request){
        if(request.getAction().equals("todo get")){
            return new TodoController().get(request);
        }
        return new Response(Response.statusActionNotFound);
    }
}
