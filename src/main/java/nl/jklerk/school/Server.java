package nl.jklerk.school;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Server {

    public enum RequestMethod {
        GET("GET"),
        POST("POST"),
        DELETE("DELETE"),
        UPDATE("UPDATE");

        private final String method;

        RequestMethod(final String method) {
            this.method = method;
        }

        public String getMethod() {
            return method;
        }
    }

    private final HttpServer server;
    private Map<String, Supplier<Response>> endpoints = new HashMap<>();

    public Server(int port) throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/").setHandler(this::callback);
    }

    private void callback(HttpExchange exchange) {
        final var response = endpoints.getOrDefault(exchange.getRequestMethod() + exchange.getRequestURI().toString(), () -> new Response(404, "", Collections.emptyMap())).get();

        try {
            exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "*");
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.getBody().getBytes().length);

            final var os = exchange.getResponseBody();
            os.write(response.getBody().getBytes());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        server.start();
    }

    public void addEndpoint(String path, RequestMethod method, Supplier<Response> handler) {
        endpoints.put(method.getMethod() + path, handler);
    }
}
