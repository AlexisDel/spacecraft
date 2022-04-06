package GameOver;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeaderBoard {
    private List<BestScore> scores;
    public LeaderBoard(String user, int finalScore) throws IOException{
        BestScore currentBestScore = new BestScore(user,finalScore);

        scores = retrieveScores();
        addNewScore(currentBestScore, scores);
        writeScores(scores);
    }


    private List<BestScore> retrieveScores() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("./resources/score.txt")));
        String scoreLine = reader.readLine(); // read line that contains scores
        List<String> scores = new ArrayList<>();
        List<BestScore> bestScores = new ArrayList<>();

        if (scoreLine != null) { // in case of first game
            String[] tempScore = scoreLine.split(", ");
            scores = new ArrayList<>(Arrays.asList(tempScore));
        }
        reader.close();
        for(String s:scores){
            String[] temp= s.split("_");
            BestScore bestScore= new BestScore(temp[0],Integer.parseInt(temp[1]));
            bestScores.add(bestScore);
        }
        return bestScores;
    }

    private void addNewScore(BestScore currentBestScore, List<BestScore> scores) {
        boolean foundSpotForNewScore = false;
        int i = 0;
        while (!foundSpotForNewScore && i < scores.size()) {
            if (currentBestScore.getScore() <= (scores.get(i)).getScore()) {
                foundSpotForNewScore = true;
            }
            else{
                i++;
            }
        }
        scores.add(i, currentBestScore);
    }

    private void writeScores(List<BestScore> scores) throws IOException {
        FileWriter writer = new FileWriter(new File("./resources/score.txt"));
        String outputScores = scores.toString();
        outputScores = outputScores.replace("[", "");
        outputScores = outputScores.replace("]", "");
        writer.write(outputScores);
        writer.close();
    }

    public List<BestScore> getTop5(){
        List<BestScore> top5= new ArrayList<>();
        int i = 0;
        while (i < 5 && i < scores.size()) {
            top5.add(scores.get(i));
            i++;
        }
        return top5;
    }

}
