package highscores;

import entity.Entity;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HighScoreWriter {

    public static boolean scoreWritten = false;
    public static int oldFirstPlaceScore = HighScoreReader.firstPlaceValue;
    public static int oldSecondPlaceScore = HighScoreReader.secondPlaceValue;
    public static int oldThirdPlaceScore = HighScoreReader.thirdPlaceValue;

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
            System.out.println("3rd if");
        }
        else if (score > secondPlaceScore && score < firstPlaceScore) {
            thirdPlaceScore = secondPlaceScore;
            scores.set(2, thirdPlaceScore);
            secondPlaceScore = score;
            scores.set(1, secondPlaceScore);
            System.out.println("2nd if");
        }
        else if (score > thirdPlaceScore && score <= secondPlaceScore) {
            thirdPlaceScore = score;
            scores.set(2, thirdPlaceScore);
            System.out.println("1st if");
        }
        else {
            //no scores to update
        }

        return scores;

    }


    public static int getFirstPlaceScore() {
        int firstPlaceScore = HighScoreReader.firstPlaceValue;
        int score = Entity.score;

        if (score > firstPlaceScore) {
            firstPlaceScore = score;
        }
        return firstPlaceScore;
    }

    public static int getSecondPlaceScore() {
        int firstPlaceScore = HighScoreReader.firstPlaceValue;
        int secondPlaceScore = HighScoreReader.secondPlaceValue;
        int thirdPlaceScore = HighScoreReader.thirdPlaceValue;
        int score = Entity.score;

        if(secondPlaceScore > firstPlaceScore) {
            secondPlaceScore = firstPlaceScore;
        }
        if (score >= secondPlaceScore && score < firstPlaceScore) {
            secondPlaceScore = score;
        }

        return secondPlaceScore;
    }

    public static int getThirdPlaceScore() {
        int firstPlaceScore = HighScoreReader.firstPlaceValue;
        int secondPlaceScore = HighScoreReader.secondPlaceValue;
        int thirdPlaceScore = HighScoreReader.thirdPlaceValue;
        int score = Entity.score;

        if(thirdPlaceScore > secondPlaceScore) {
            thirdPlaceScore = secondPlaceScore;
        }
        if (score > thirdPlaceScore && score < secondPlaceScore) {
            thirdPlaceScore = score;
        }
        return thirdPlaceScore;
    }


    /*public static int getFirstPlaceScore() {

        int firstPlaceScore = HighScoreReader.firstPlaceValue;
        int secondPlaceScore = HighScoreReader.secondPlaceValue;
        int thirdPlaceScore = HighScoreReader.thirdPlaceValue;
        int score = Entity.score;

        if(score > firstPlaceScore && score > secondPlaceScore && score > thirdPlaceScore)
            firstPlaceScore = score;
        if(firstPlaceScore < oldFirstPlaceScore && firstPlaceScore < score)
            firstPlaceScore = oldFirstPlaceScore;
        if(firstPlaceScore == 0)
            firstPlaceScore = score;

        return firstPlaceScore;
    }

    public static int getSecondPlaceScore() {
        int firstPlaceScore = HighScoreReader.firstPlaceValue;
        int secondPlaceScore = HighScoreReader.secondPlaceValue;
        int score = Entity.score;

        if(score > secondPlaceScore && score < firstPlaceScore)
            secondPlaceScore = score;
        if(secondPlaceScore < oldFirstPlaceScore)
            secondPlaceScore = oldFirstPlaceScore;
        if(secondPlaceScore == 0)
            secondPlaceScore = score;

        return secondPlaceScore;
    }

    public static int getThirdPlaceScore() {
        int secondPlaceScore = HighScoreReader.secondPlaceValue;
        int thirdPlaceScore = HighScoreReader.thirdPlaceValue;
        int score = Entity.score;

        if(score > thirdPlaceScore && score < secondPlaceScore)
            thirdPlaceScore = score;
        if(thirdPlaceScore < oldFirstPlaceScore && thirdPlaceScore < score)
            thirdPlaceScore = oldSecondPlaceScore;
        if(thirdPlaceScore == 0)
            thirdPlaceScore = score;

        return thirdPlaceScore;
    }*/
}