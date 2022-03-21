package View.LeaderBoard;

import View.FontManager;
import View.ImageManager;

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
        g.setFont(FontManager.Dune2000_20);
        g.drawString("USER",50,50);
        g.drawString("TIME",200,50);


        for(BestScore score: leaderBoard.getTop5()){
            g.drawString(score.getUsername(), 50,100+y);
            String time= ((score.getScore()/1000)%60)/60+":"+(score.getScore()/1000)%60+":"+score.getScore()%1000;
            g.drawString(time, 200,100+y);

            y+=50;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ImageManager.launcherBackground, 0, 0, null);
        drawTop5(g);

    }
}
