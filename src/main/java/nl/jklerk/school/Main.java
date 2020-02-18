package nl.jklerk.school;

import com.sun.net.httpserver.HttpServer;
import io.github.cdimascio.dotenv.Dotenv;
import nl.jklerk.school.routes.web;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    /**
     * Specifiy on which port the api should run
     */
    public static void main(String[] args) throws IOException {
        initServer(8001);
    }

    /**
     * Initiates the httpserver and listens to port
     */
    private static void initServer(int port) throws IOException{
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        initDB();

        web.setRouters(server);
        server.start();
        System.out.println("[Server] listening on: " + port);
    }

    /**
     * Makes a new database connection
     */
    private static void initDB(){
        Dotenv dotenv = Dotenv.load();

        String type = dotenv.get("DB_TYPE");
        String username = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");
        String url = dotenv.get("DB_DOMAIN");
        String port = dotenv.get("DB_PORT");
        String database = dotenv.get("DB_NAME");

        new Database().connect(type, username, password, url, port, database);
    }
}