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

public class StageController {

    /**
     * Methods to get all stages with a SQL query
     */
    public static void getStages(HttpExchange exc) throws IOException {
        JSONArray data = new JSONArray();
        try {
            String sql = "SELECT * FROM stages";
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
     * Method to insert a new stage
     */
    public static void postStage(HttpExchange exc) throws IOException {
        String data = Request.getRequestBody(exc);
        var name = new JsonMapper().readTree(data).get("name").asText();
        var desc = new JsonMapper().readTree(data).get("description").asText();

        try {
            String sql = "INSERT INTO stages (name, description) VALUES (?,?)";
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
        var res = new Response(200, "Success");
        res.sendBody(exc);

    }

    /**
     * Method to update an existing stage based on the data in the requestbody
     */
    public static void editStage(HttpExchange exc) throws IOException {
        String data = Request.getRequestBody(exc);
        System.out.println(data);
        var id = new JsonMapper().readTree(data).get("id").asText();
        var name = new JsonMapper().readTree(data).get("name").asText();
        var desc = new JsonMapper().readTree(data).get("description").asText();

        try {
            String sql = "UPDATE stages SET name = ?, description = ? WHERE id=?;";
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
     * Method to delete a stage based on id
     */
    public static void deleteStage(HttpExchange exc) throws IOException {
        String data = Request.getRequestBody(exc);
        var id = new JsonMapper().readTree(data).get("id").asText();

        try {
            String sql = "DELETE FROM stages WHERE id=?;";
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
