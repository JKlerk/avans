package nl.jklerk.school;

import nl.jklerk.school.Controllers.ArtistController;
import nl.jklerk.school.Controllers.StageController;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        final var server = new Server(8001);
        server.addEndpoint("/api/artists", Server.RequestMethod.GET, ArtistController::getArtists);
        server.addEndpoint("/api/stages", Server.RequestMethod.GET, StageController::getStages);
        server.start();
    }
}