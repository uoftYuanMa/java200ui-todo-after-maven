package server.net;

import common.net.Request;
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
    }
    private void route(Request request){
        if(request.getAction().equals("todo get")){
            new TodoController().get(request);
        }
    }
}
