package highscores;

import entity.Entity;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HighScoreWriter {

    public static boolean scoreWritten = false;

    public static void writeFile() {

        if(scoreWritten) {
            return;
        }

        FileWriter writer = null;
        try {
            writer = new FileWriter("highscores.txt", false);
            int linesWritten = 0;
            while (linesWritten < 3) {
                ArrayList<Integer> scoresArray = scoresHandler();
                String line;
                if (linesWritten == 0) {
                    line = "" + scoresArray.get(0);
                } else if (linesWritten == 1) {
                    line = "" + scoresArray.get(1);
                } else {
                    line = "" + scoresArray.get(2);
                }
                writer.write(line + "\n");
                linesWritten++;
            }
            scoreWritten = true;
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ArrayList<Integer> scoresHandler(){
        int firstPlaceScore = HighScoreReader.firstPlaceValue;
        int secondPlaceScore = HighScoreReader.secondPlaceValue;
        int thirdPlaceScore = HighScoreReader.thirdPlaceValue;
        int score = Entity.score;
        ArrayList<Integer> scores = new ArrayList<Integer>();
        scores.add(firstPlaceScore);
        scores.add(secondPlaceScore);
        scores.add(thirdPlaceScore);

        if (score > firstPlaceScore) {
            thirdPlaceScore = secondPlaceScore;
            scores.set(2, thirdPlaceScore);
            secondPlaceScore = firstPlaceScore;
            scores.set(1, secondPlaceScore);
            firstPlaceScore = score;
            scores.set(0, firstPlaceScore);
        }
        else if (score > secondPlaceScore && score < firstPlaceScore) {
            thirdPlaceScore = secondPlaceScore;
            scores.set(2, thirdPlaceScore);
            secondPlaceScore = score;
            scores.set(1, secondPlaceScore);
        }
        else if (score > thirdPlaceScore && score <= secondPlaceScore) {
            thirdPlaceScore = score;
            scores.set(2, thirdPlaceScore);
        }

        return scores;

    }
}