package nl.jklerk.school;


import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;


public class Response {
    private int code;
    private String body;

    /**
     * Sets the default response to 404
     */
    public Response() {
        this.code = 404;
        this.body = "";
    }

    /**
     * Sets the code and body
     */
    public Response(int code, String body) {
        this.code = code;
        this.body = body;
    }

    /**
     * Sends the data with headers back to front end
     */
    public boolean sendBody(HttpExchange exc) throws IOException {

        Headers headers = exc.getResponseHeaders();
        headers.set("Access-Control-Allow-Headers","*");
        headers.set("Access-Control-Allow-Origin","*");

        exc.sendResponseHeaders(this.code, this.body.length());
        OutputStream os = exc.getResponseBody();
        os.write(this.body.getBytes());
        os.close();
        return false;
    }

}