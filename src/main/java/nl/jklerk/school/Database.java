package nl.jklerk.school;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private Connection connection;

    public Database() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/avans?user=root&password=password");
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public String query(String sql) {
        try {
            ResultSet result = connection.createStatement().executeQuery(sql);
            return String.valueOf(getJsonFromResultSet(result));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static JSONArray getJsonFromResultSet(ResultSet resultSet) {
        JSONArray array = new JSONArray();
        try {
            if (!resultSet.isBeforeFirst()) {
                return array;
            }
            while (resultSet.next()) {
                JSONObject jsonObject = new JSONObject();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    jsonObject.put(resultSet.getMetaData().getColumnName(i), resultSet.getObject(i));
                }
                array.add(jsonObject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return array;
    }

    /*public List<Artist> getArtists() {
        try {
            final var result = connection.createStatement().executeQuery("SELECT * FROM artists");

            var artists = new ArrayList<Artist>();
            while (result.next()) {
                artists.add(new Artist(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getInt("band")
                ));
            }

            return artists;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }*/
}
