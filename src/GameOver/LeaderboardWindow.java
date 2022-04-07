package GameOver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LeaderboardWindow extends JFrame {
    /**
     * Cette méthode est utilisé pour afficher la fenetre du leaderboard
     * @param leaderboard
     * @throws IOException
     */
    public LeaderboardWindow(LeaderBoard leaderboard) throws IOException {
        //On fixe la fenetre
        this.setTitle("SpaceCraft LeaderBoard");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(new Dimension(320, 480));
        //On ajoute le logo
        this.setIconImage(ImageIO.read(new File("resources/logo_spacemarines.png")));
        LeaderBoardPanel lbPanel= new LeaderBoardPanel(leaderboard);
        this.add(lbPanel);
        //On l'affiche
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
