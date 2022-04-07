package GameOver;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeaderBoard {
    /** Attributs*/
    private List<BestScore> scores;

    /**
     * Constructeur principal
     * @param user nom de l'utilisateur
     * @param finalScore score obtenu par l utilisateur
     * @throws IOException
     */
    public LeaderBoard(String user, int finalScore) throws IOException{
        BestScore currentBestScore = new BestScore(user,finalScore);
        //On recupere les scores du fichier
        scores = retrieveScores();
        //On ajoute le score que le joueur viens d'obtenir
        addNewScore(currentBestScore, scores);
        //On réécrit les scores dans le fichier
        writeScores(scores);
    }

    /**
     * Cette méthode lit le fichier de texte et mets tous les scores dans une liste
     * @return une liste de scores
     * @throws IOException
     */
    private List<BestScore> retrieveScores() throws IOException {
        //On ouvre le fichier
        BufferedReader reader = new BufferedReader(new FileReader(new File("./resources/score.txt")));
        String scoreLine = reader.readLine(); // read line that contains scores
        List<String> scores = new ArrayList<>();
        List<BestScore> bestScores = new ArrayList<>();
        //On coupe le string contenu dans fichier à chaque virgule
        if (scoreLine != null) { // dans le cas d'un fichier vide, comme pour la premiere game
            String[] tempScore = scoreLine.split(", ");
            //C'est la liste des scores
            scores = new ArrayList<>(Arrays.asList(tempScore));
        }
        //On ferme le fichier
        reader.close();
        //On ajoute ces scores dans une liste de BestScores,
        // pour passer vers des BestScores: on split le string au niveau du underscore s"_"
        for(String s:scores){
            //split
            String[] temp= s.split("_");
            //On extrait l'entier
            BestScore bestScore= new BestScore(temp[0],Integer.parseInt(temp[1]));
            bestScores.add(bestScore);
        }
        return bestScores;
    }

    /**
     * On ajoute un score dans la liste de scores
     * @param currentBestScore
     * @param scores
     */
    private void addNewScore(BestScore currentBestScore, List<BestScore> scores) {
        boolean foundSpotForNewScore = false;
        int i = 0;
        //On cherche un endroit ou posser le nouveau score qui maintienne la liste triee
        while (!foundSpotForNewScore && i < scores.size()) {
            //On compare au score courant (on parle de temps en ms)
            if (currentBestScore.getScore() <= (scores.get(i)).getScore()) {
                foundSpotForNewScore = true;
            }
            else{
                i++;
            }
        }
        //On ajoute le score a la position trouvee
        scores.add(i, currentBestScore);
    }

    /**
     *  Écrit les scores de la List en parametre dans le score.txt
     * @param scores une ArrayList
     * @throws IOException dans le cas ou un fichier ne peut pas s'ouvrir
     */
    private void writeScores(List<BestScore> scores) throws IOException {
        //On ouvre le fichier
        FileWriter writer = new FileWriter(new File("./resources/score.txt"));
        String outputScores = scores.toString();
        //On efface les crochet de la liste
        outputScores = outputScores.replace("[", "");
        outputScores = outputScores.replace("]", "");
        //On écrit la liste
        writer.write(outputScores);
        writer.close();
    }

    /**
     * Cette classe donne les 5 meilleures scores parmi les scores dans le historique (score.txt)
     * @return ArrayList de BestScore
     */
    public List<BestScore> getTop5(){
        //On retourne une ArrayList
        List<BestScore> top5= new ArrayList<>();
        int i = 0;
        //On prends les 5 premiers
        while (i < 5 && i < scores.size()) {
            top5.add(scores.get(i));
            i++;
        }
        return top5;
    }

}
