package nl.jklerk.school;

import com.sun.net.httpserver.HttpExchange;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Request {

    /**
     * Returns a string with the data in the body of the request
     */
    public static String getRequestBody(HttpExchange exc) throws IOException {
        return new String(exc.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
    }
}
