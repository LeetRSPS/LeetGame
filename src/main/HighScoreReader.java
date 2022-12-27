package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HighScoreReader {

    public static int firstPlaceValue = 0;
    public static int secondPlaceValue = 0;
    public static int thirdPlaceValue = 0;

    public static int[] readHighScore() {
        int[] highScores = new int[3];

        try {
            FileReader reader = new FileReader("HighScores.txt");
            BufferedReader br = new BufferedReader(reader);
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                highScores[lineNumber] = Integer.parseInt(line);
                lineNumber++;

                if (lineNumber == 1) {
                    firstPlaceValue = Integer.parseInt(line);
                }
                if (lineNumber == 2) {
                    secondPlaceValue = Integer.parseInt(line);
                }
                if (lineNumber == 3) {
                    thirdPlaceValue = Integer.parseInt(line);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highScores;
    }
}
