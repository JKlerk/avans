package nl.jklerk.school;

import com.sun.net.httpserver.HttpServer;
import nl.jklerk.school.routes.web;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException {
        initServer(8001);
    }

    private static void initServer(int port) throws IOException{
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        initDB();

        web.setRouters(server);
        server.start();
        System.out.println("[Server] running on port: " + port);
    }
    private static void initDB(){
        String type = "mysql";
        String username = "root";
        String password = "password";
        String url = "localhost";
        String port = "3306";
        String database = "avans";

        new Database().connect(type, username, password, url, port, database);
    }
}