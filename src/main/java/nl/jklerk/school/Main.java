package nl.jklerk.school;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        final var server = new Server(80);
        server.addEndpoint("/api/poep", Main::getPoep);
        server.start();
    }

    public static Response getPoep() {
        return new Response().withCode(200).withBody("{\"test\": \"works\"}");
    }
}