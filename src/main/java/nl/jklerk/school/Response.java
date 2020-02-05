package nl.jklerk.school;

import java.util.Collections;
import java.util.Map;

public class Response {
    private final int code;
    private final String body;
    private final Map<String, String> headers;

    public Response() {
        this.code = 404;
        this.body = "";
        this.headers = Collections.emptyMap();
    }

    public Response(int code, String body, Map<String, String> headers) {
        this.code = code;
        this.body = body;
        this.headers = headers;
    }

    public Response withCode(int code) {
        return new Response(code, this.body, this.headers);
    }

    public Response withBody(String body) {
        return new Response(this.code, body, this.headers);
    }

    public Response addHeader(String key, String value) {
        this.headers.putIfAbsent(key, value);
        return new Response(this.code, this.body, this.headers);
    }

    public int getCode() {
        return code;
    }

    public String getBody() {
        return body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
