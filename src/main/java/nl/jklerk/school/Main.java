package nl.jklerk.school;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        final var server = new Server(8001);
        server.addEndpoint("/api/artists", Server.RequestMethod.GET, ArtistController::getArtists);
        server.start();
    }
}