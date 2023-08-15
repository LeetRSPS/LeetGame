package highscores;

import main.GamePanel;

import javax.swing.*;

public class UsernamePrompt {
    public static void usernamePrompt() {
        SwingUtilities.invokeLater(() -> {
            String username = JOptionPane.showInputDialog(null, "Enter your username:", "Username Input", JOptionPane.PLAIN_MESSAGE);

            if (username != null && !username.isEmpty()) {
                HighscoreUpdater.username = username;
            } else {
                System.out.println("Username is null");
            }
        });
    }
}
