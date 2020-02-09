package nl.jklerk.school;

public class ArtistController {

    private static final Database database = new Database();

    public static Response getArtists() {

        return new Response().withCode(200).withBody(database.query("SELECT jsonb_agg(to_jsonb(artists)) json FROM artists"));
    }
}
