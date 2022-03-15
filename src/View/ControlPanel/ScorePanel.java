package View.ControlPanel;

import Model.GameBoard;
import Model.GameEngine;
import Model.Layer1.Entities.Entity;

import javax.swing.*;
import java.awt.*;

import static View.ViewConstants.SCORE_PANEL_HEIGHT;
import static View.ViewConstants.SCORE_PANEL_WIDTH;

public class ScorePanel extends JPanel {

    private GameBoard gameBoard;
    private int rocksSM;
    private int rocksAliens;
    private int rocksMars;

    public ScorePanel(GameEngine gameEngine){
        this.gameBoard= gameEngine.getGameBoard();
        getCounters();

        this.setLayout(new GridLayout(5,0));
        this.setPreferredSize(new Dimension(SCORE_PANEL_WIDTH, SCORE_PANEL_HEIGHT));
        this.setBackground(new Color(245,222,179));

        this.add(new JButton("SpaceCraft"));
        this.add(new JLabel(""));
        this.add(new JLabel("Rocks captured:"));
        this.add(new JLabel("Rocks captured by the Aliens:"));
        this.add(new JLabel("Rocks still in the Martian surface:"));

    }
    public void getCounters(){

    }
}
