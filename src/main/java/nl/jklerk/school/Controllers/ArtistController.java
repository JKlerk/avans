package nl.jklerk.school.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import nl.jklerk.school.Database;
import nl.jklerk.school.Request;
import nl.jklerk.school.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtistController {

    public static void getArtists(HttpExchange exc) throws IOException {

        JSONArray data = new JSONArray();
        try {
            String sql = "SELECT * FROM artists";
            PreparedStatement preparedStatement = Database.connect().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            data = Database.createJson(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        var res = new Response(200, data.toString());

        res.sendBody(exc);

    }

    public static void postArtist(HttpExchange exc) throws IOException {
        String data = Request.getRequestBody(exc);

        System.out.println(data);
        JSONObject json = null;

        try {
            JSONParser parser = new JSONParser();

            json = (JSONObject) parser.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        String name = (String) json.get("name");
//
        var res = new Response(200, "name");
        res.sendBody(exc);
    }
}

//    private static final Database database = new Database();

//   public static JSONArray getArtists(HttpExchange exchange) {
//        ResultSet resultSet = null;
//        try {
//            String sql = "SELECT * FROM artists";
//            PreparedStatement preparedStatement = Database.connect().prepareStatement(sql);
//            resultSet = preparedStatement.executeQuery();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return (exchange, Database.createJson(resultSet));
//}
