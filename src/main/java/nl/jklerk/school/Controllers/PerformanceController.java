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
    private String name = null;
    private String time = null;
    private String date = null;
    private int artist_id = 0;
    private int stage_id = 0;


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

}
