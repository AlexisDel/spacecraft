package GameOver;

import Game.View.RessourceManager;

import javax.swing.*;
import java.awt.*;

/**
 * Cette classe correpond au JPanel du leaderboard
 */
public class LeaderBoardPanel extends JPanel {
    private LeaderBoard leaderBoard;

    /**
     * Constructeur
     * @param leaderBoard
     */
    public LeaderBoardPanel(LeaderBoard leaderBoard){
    this.leaderBoard= leaderBoard;
    }

    /**
     * Cette méthode affiche les top 5 joueurs dans le panneau
     * @param g
     */
    public void drawTop5(Graphics g){
        int y=0;
        //On dessine le titre de chaque colonne
        g.setColor(Color.WHITE);
        g.setFont(RessourceManager.FontManager.Dune2000_20);
        g.drawString("USER",50,50);
        g.drawString("TIME",200,50);

        //On décode le temps donné (il est donné en ms)
        for(BestScore score: leaderBoard.getTop5()){
            g.drawString(score.getUsername(), 50,100+y);
            long elapsedTime = score.getScore();
            long elapsedMs = elapsedTime%1000;
            long elapsedSeconds = elapsedTime / 1000;
            long secondsDisplay = elapsedSeconds % 60;
            long elapsedMinutes = elapsedSeconds / 60;
            //On affiche le temps en minutes, secondes et millisecondes
            String time= elapsedMinutes+":"+secondsDisplay+":"+elapsedMs;
            g.drawString(time, 200,100+y);

            y+=50;
        }
    }

    /**
     * Cette méthode dessine les différents composants du Panel.
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(RessourceManager.ImageManager.launcherBackground, 0, 0, null);
        drawTop5(g);

    }
}
