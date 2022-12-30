package highscores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HighScoreReader {

    public static int firstPlaceValue = 0;
    public static int secondPlaceValue = 0;
    public static int thirdPlaceValue = 0;

    public static void readHighScore() {

        try {
            FileReader reader = new FileReader("highscores.txt");
            BufferedReader br = new BufferedReader(reader);
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (lineNumber == 1) {
                    firstPlaceValue = Integer.parseInt(line);
                } else if (lineNumber == 2) {
                    secondPlaceValue = Integer.parseInt(line);
                } else {
                    thirdPlaceValue = Integer.parseInt(line);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
