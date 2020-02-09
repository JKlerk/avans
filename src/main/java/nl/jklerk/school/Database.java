package nl.jklerk.school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private Connection connection;

    public Database() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?user=postgres&password=12345");
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public String query(String sql) {
        try {
            final var result = connection.createStatement().executeQuery(sql);
            result.next();
            return result.getString("json");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
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
