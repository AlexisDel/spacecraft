package View.LeaderBoard;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeaderBoardPanel extends JPanel {

    public LeaderBoardPanel(String user, int finalScore) throws IOException{
        List<String> scores = retrieveScores();
        addNewScore(finalScore, scores);
        writeScores(scores);
        showLeaderBoard(scores);

        //drawPanel();

    }

    private List<String> retrieveScores() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("score.txt"));
        String scoreLine = reader.readLine(); // read line that contains scores
        List<String> scores = new ArrayList<>();
        if (scoreLine != null) { // in case of first game
            String[] tempScore = scoreLine.split(", ");
            scores = new ArrayList<>(Arrays.asList(tempScore));
        }
        reader.close();
        return scores;
    }

    private void addNewScore(int finalScore, List<String> scores) {
        boolean foundSpotForNewScore = false;
        int i = 0;
        while (!foundSpotForNewScore && i < scores.size()) {
            if (finalScore <= Integer.parseInt(scores.get(i))) {
                foundSpotForNewScore = true;
            }
            i++;
        }
        scores.add(i, String.valueOf(finalScore));
    }

    private void writeScores(List<String> scores) throws IOException {
        FileWriter writer = new FileWriter("score.txt");
        String outputScores = scores.toString();
        outputScores = outputScores.replace("[", "");
        outputScores = outputScores.replace("]", "");
        writer.write(outputScores);
        writer.close();
    }

    private void showLeaderBoard(List<String> scores) {
        System.out.println("*** TOP 5 LEADERBOARD ***");
        int i = 0;
        while (i < 5 && i < scores.size()) {
            System.out.println(scores.get(i));
            i++;
        }
        System.out.println("*** TOP 5 LEADERBOARD ***");
    }
}
