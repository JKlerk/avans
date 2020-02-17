package nl.jklerk.school;


import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class Response {
    private int code;
    private String body;

    public Response() {
        this.code = 404;
        this.body = "";
    }

    public Response(int code, String body) {
        this.code = code;
        this.body = body;
    }

    public void sendBody(HttpExchange exc) throws IOException {

        Headers headers = exc.getResponseHeaders();
        headers.set("Access-Control-Allow-Headers","*");
        headers.set("Access-Control-Allow-Origin","*");

        exc.sendResponseHeaders(this.code, this.body.length());
        OutputStream os = exc.getResponseBody();
        os.write(this.body.getBytes());
        os.close();
    }

}