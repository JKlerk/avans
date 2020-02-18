package nl.jklerk.school.routes;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import nl.jklerk.school.Controllers.ArtistController;
import nl.jklerk.school.Controllers.PerformanceController;
import nl.jklerk.school.Controllers.StageController;

public class web {
    private static HttpServer httpServer;

    public static void setRouters(HttpServer server){
        httpServer = server;

        // Artist routing
        route("/api/getartists", ArtistController::getArtists);
        route("/api/addartist", ArtistController::postArtist);
        route("/api/editartist", ArtistController::editArtist);
        route("/api/deleteartist", ArtistController::deleteArtist);

        // Stage routing
        route("/api/getstages", StageController::getStages);
        route("/api/addstage", StageController::postStage);
        route("/api/editstage", StageController::editStage);
        route("/api/deletestage", StageController::deleteStage);


        // Performances routing
        route("/api/getperformances", PerformanceController::getPerformances);
        route("/api/addperformance", PerformanceController::postPerformance);
        route("/api/editperformance", PerformanceController::editPerformance);
        route("/api/deleteperformance", PerformanceController::deletePerformance);
    }

    //    Sets route with controller
    public static void route(String path, HttpHandler controller){
        httpServer.createContext(path).setHandler(controller);
    }

}
