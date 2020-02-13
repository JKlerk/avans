package nl.jklerk.school.Controllers;

import nl.jklerk.school.Database;
import nl.jklerk.school.Response;

public class StageController {
    //Setup with database
    private static final Database database = new Database();

    public static Response getStages() {
        Response response = new Response();
        return response.withBody(database.query("SELECT * FROM stages"));
    }
}
