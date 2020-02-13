package nl.jklerk.school;

public class ArtistController {

    private static final Database database = new Database();

    public static Response getArtists() {
        Response response = new Response();
        return response.withBody(database.query("SELECT * FROM artists"));
    }
}
