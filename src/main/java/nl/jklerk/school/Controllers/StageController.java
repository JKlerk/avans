package nl.jklerk.school.Controllers;

import com.sun.net.httpserver.HttpExchange;
import nl.jklerk.school.Database;
import nl.jklerk.school.Response;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StageController {

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
}
