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
import java.sql.Statement;

public class ArtistController{

    /**
     * Methods to get all artists with a SQL query
     */
    public static boolean getArtists(HttpExchange exc) throws IOException {

        JSONArray data = new JSONArray();
        try {
            String sql = "SELECT * FROM artists";
            PreparedStatement preparedStatement = Database.connect().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            data = Database.createJson(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Response(200, data.toString()).sendBody(exc);

    }

    /**
     * Method to insert new data from the request body
     */
    public static void postArtist(HttpExchange exc) throws IOException {
        String data = Request.getRequestBody(exc);

        var name = new JsonMapper().readTree(data).get("name").asText();
        var desc = new JsonMapper().readTree(data).get("description").asText();

        try {
            String sql = "INSERT INTO artists (name, description) VALUES (?,?)";
            PreparedStatement preparedStatement = Database.connect().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, desc);
            preparedStatement.getGeneratedKeys();
            preparedStatement.executeUpdate();

            var res = new Response(200, Database.createJson(preparedStatement.getGeneratedKeys()).toString());
            res.sendBody(exc);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        var res = new Response(404, "Something went wrong");
        res.sendBody(exc);
    }

    /**
     * Method to update artist based on data from request body
     */
    public static void editArtist(HttpExchange exc) throws IOException {
        String data = Request.getRequestBody(exc);
        var id = new JsonMapper().readTree(data).get("id").asText();
        var name = new JsonMapper().readTree(data).get("name").asText();
        var desc = new JsonMapper().readTree(data).get("description").asText();

        try {
            String sql = "UPDATE artists SET name = ?, description = ? WHERE id=?;";
            PreparedStatement preparedStatement = Database.connect().prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, desc);
            preparedStatement.setString(3, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        var res = new Response(200, "Success");
        res.sendBody(exc);

    }

    /**
     * Method to delete artist based on id
     */
    public static void deleteArtist(HttpExchange exc) throws IOException {
        String data = Request.getRequestBody(exc);
        var id = new JsonMapper().readTree(data).get("id").asText();

        try {
            String sql = "DELETE FROM artists WHERE id=?;";
            PreparedStatement preparedStatement = Database.connect().prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        var res = new Response(200, "Success");
        res.sendBody(exc);

    }
}