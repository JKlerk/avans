package nl.jklerk.school.Controllers;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.sun.net.httpserver.HttpExchange;
import nl.jklerk.school.Database;
import nl.jklerk.school.Request;
import nl.jklerk.school.Response;
import org.json.simple.JSONArray;

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

        var name = new JsonMapper().readTree(data).get("name").asText();
        var desc = new JsonMapper().readTree(data).get("desc").asText();

        try {
            String sql = "INSERT INTO artists (name, description) VALUES (?,?)";
            PreparedStatement preparedStatement = Database.connect().prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, desc);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        var res = new Response(200, "Success");
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
