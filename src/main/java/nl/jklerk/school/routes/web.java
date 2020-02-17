package nl.jklerk.school.routes;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import nl.jklerk.school.Controllers.ArtistController;
import nl.jklerk.school.Controllers.StageController;

public class web {
    private static HttpServer httpServer;

    public static void setRouters(HttpServer server){
        httpServer = server;

        // Get routing
        route("/api/getartists", ArtistController::getArtists);
        route("/api/getstages", StageController::getStages);

        // Post routing
        route("/api/addartist", ArtistController::postArtist);
        route("/api/editartist", ArtistController::editArtist);
    }

    //    Sets route with controller
    public static void route(String path, HttpHandler controller){
        httpServer.createContext(path).setHandler(controller);
    }

}
