package nl.jklerk.school;

import com.sun.net.httpserver.HttpExchange;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Request {
    public static String getRequestBody(@NotNull HttpExchange exc) throws IOException {
        return new String(exc.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
    }
}
