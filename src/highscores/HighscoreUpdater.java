package highscores;

import entity.Entity;
import main.GamePanel;

import java.sql.*;

public class HighscoreUpdater {

    public static String username = "";
    public static int score = Entity.score;

    public static long lastTimeSubmit;
    public static long cooldownIterator = 1000 * 60 * 2;
    public static long targetTime =  lastTimeSubmit + cooldownIterator;

    public static void updateHighscore() {
        UsernamePrompt.usernamePrompt();

        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/highscores";
        String username = "root";
        String password = "h083ngjlloi4ehg2";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            String insertQuery = "INSERT INTO highscore (Score, PlayerName) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, score);
            preparedStatement.setString(2, username);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Highscore updated successfully!");
            } else {
                System.out.println("Failed to update highscore.");
            }

            preparedStatement.close();
            connection.close();

            lastTimeSubmit = System.currentTimeMillis();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
