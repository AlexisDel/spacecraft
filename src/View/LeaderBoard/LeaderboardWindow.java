package View.LeaderBoard;

import javax.swing.*;
import java.awt.*;

public class LeaderboardWindow extends JFrame {

    public LeaderboardWindow(LeaderBoard leaderboard){
        this.setTitle("SpaceCraft View.LeaderBoard");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(new Dimension(320, 480));

        LeaderBoardPanel lbPanel= new LeaderBoardPanel(leaderboard);
        this.add(lbPanel);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
