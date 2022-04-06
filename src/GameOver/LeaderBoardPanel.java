package GameOver;

import Game.View.RessourceManager;

import javax.swing.*;
import java.awt.*;

public class LeaderBoardPanel extends JPanel {
    private LeaderBoard leaderBoard;

    public LeaderBoardPanel(LeaderBoard leaderBoard){
    this.leaderBoard= leaderBoard;
    }

    public void drawTop5(Graphics g){
        int y=0;
        g.setColor(Color.WHITE);
        g.setFont(RessourceManager.FontManager.Dune2000_20);
        g.drawString("USER",50,50);
        g.drawString("TIME",200,50);


        for(BestScore score: leaderBoard.getTop5()){
            g.drawString(score.getUsername(), 50,100+y);
            long elapsedTime = score.getScore();
            long elapsedMs = elapsedTime%1000;
            long elapsedSeconds = elapsedTime / 1000;
            long secondsDisplay = elapsedSeconds % 60;
            long elapsedMinutes = elapsedSeconds / 60;

            String time= elapsedMinutes+":"+secondsDisplay+":"+elapsedMs;
            g.drawString(time, 200,100+y);

            y+=50;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(RessourceManager.ImageManager.launcherBackground, 0, 0, null);
        drawTop5(g);

    }
}
