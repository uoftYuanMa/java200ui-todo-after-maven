package client.net;

import common.net.Request;
import common.net.Response;
import io.zzax.jadeite.net.Connector;

import java.io.IOException;
import java.net.Socket;

//连接服务器并发送数据
public class RequestSender {
    public Response send(Request request) {
        try {
            //向服务器请求连接，把action和data都发出去
            Socket socket = new Socket("localhost", 9999);
            Connector connector = new Connector(socket);
            connector.writeLine(request.getAction());
            connector.writeLine(request.getData());

            String status = connector.readLine();
            String data = connector.readLine();
            Response response = new Response(status);
            response.setData(data);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
