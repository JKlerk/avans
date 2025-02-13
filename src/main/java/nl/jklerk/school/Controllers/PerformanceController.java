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

public class PerformanceController {

    /**
     * Methods to get all performances with a sql query
     */
    public static void getPerformances(HttpExchange exc) throws IOException {

        JSONArray data = new JSONArray();
        try {
            String sql = "SELECT * FROM performances";
            PreparedStatement preparedStatement = Database.connect().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            data = Database.createJson(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        var res = new Response(200, data.toString());
        res.sendBody(exc);
    }

    /**
     * Method to insert data from request and returns latest id
     */
    public static void postPerformance(HttpExchange exc) throws IOException {
        String data = Request.getRequestBody(exc);

        String name = new JsonMapper().readTree(data).get("name").asText();
        String time = new JsonMapper().readTree(data).get("time").asText();
        String date = new JsonMapper().readTree(data).get("date").asText();
        int artist_id = new JsonMapper().readTree(data).get("artist_id").asInt();
        int stage_id = new JsonMapper().readTree(data).get("stage_id").asInt();

        try {
            String sql = "INSERT INTO performances (name, time, date, artist_id, stage_id) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = Database.connect().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, time);
            preparedStatement.setString(3, date);
            preparedStatement.setString(4, String.valueOf(artist_id));
            preparedStatement.setString(5, String.valueOf(stage_id));

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
     * Method to update performances based on id from request
     */
    public static void editPerformance(HttpExchange exc) throws IOException {
        String data = Request.getRequestBody(exc);

        int id = new JsonMapper().readTree(data).get("id").asInt();
        String name = new JsonMapper().readTree(data).get("name").asText();
        String time = new JsonMapper().readTree(data).get("time").asText();
        String date = new JsonMapper().readTree(data).get("date").asText();
        int artist_id = new JsonMapper().readTree(data).get("artist_id").asInt();
        int stage_id = new JsonMapper().readTree(data).get("stage_id").asInt();

        try {
            String sql = "UPDATE performances SET name = ?, time = ?, date = ?, artist_id = ?, stage_id = ? WHERE id=?;";
            PreparedStatement preparedStatement = Database.connect().prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, time);
            preparedStatement.setString(3, date);
            preparedStatement.setString(4, String.valueOf(artist_id));
            preparedStatement.setString(5, String.valueOf(stage_id));
            preparedStatement.setString(6, String.valueOf(id));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        var res = new Response(200, "Success");
        res.sendBody(exc);

    }

    /**
     * Method to remove a specific performance with id
     */
    public static void deletePerformance(HttpExchange exc) throws IOException {
        String data = Request.getRequestBody(exc);
        var id = new JsonMapper().readTree(data).get("id").asText();

        try {
            String sql = "DELETE FROM performances WHERE id=?;";
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
