package GameOver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LeaderboardWindow extends JFrame {

    public LeaderboardWindow(LeaderBoard leaderboard) throws IOException {
        this.setTitle("SpaceCraft LeaderBoard");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(new Dimension(320, 480));
        this.setIconImage(ImageIO.read(new File("resources/logo_spacemarines.png")));
        LeaderBoardPanel lbPanel= new LeaderBoardPanel(leaderboard);
        this.add(lbPanel);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
