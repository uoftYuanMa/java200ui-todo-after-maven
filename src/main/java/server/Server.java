package server;

import server.net.RequestHandler;

public class Server {
    public static void main(String[] args) {
        new RequestHandler().run();
    }
}
