package View.ControlPanel;

import Model.GameBoard;
import Model.GameEngine;
import Model.Layer1.Entities.Entity;
import Model.Layer1.Entities.SpaceMarine;
import Model.Layer1.Structures.Meteorite;
import Model.Layer1.Structures.Structure;

import javax.swing.*;
import java.awt.*;

import static View.ImageManager.rock;
import static View.ViewConstants.SCORE_PANEL_HEIGHT;
import static View.ViewConstants.SCORE_PANEL_WIDTH;


public class ScorePanel extends JPanel {

    private GameBoard gameBoard;
    private int rocksSM;
    private int initialRocks;

    public ScorePanel(GameEngine gameEngine){
        this.setDoubleBuffered(true);
        this.gameBoard= gameEngine.getGameBoard();

        this.setBackground(Color.RED);
        this.setPreferredSize(new Dimension(SCORE_PANEL_WIDTH,SCORE_PANEL_HEIGHT));
        initCounters();
    }

    public void initCounters(){
        for(Structure structure: gameBoard.getStructures()){
            if(structure instanceof Meteorite){
                this.initialRocks+= ((Meteorite) structure).getRocks();
            }
        }
    }

    public void updateScore(){
        int counter=0;
        for(Entity entity: gameBoard.getEntities()){
            if(entity instanceof SpaceMarine){
                counter+=  entity.getRocks();
            }
        }
        this.rocksSM=counter;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        updateScore();
        g.drawString(this.rocksSM+" / "+this.initialRocks,100,this.getHeight()/2);
        g.drawString("Mine rocks from Meteorites or by killing Aliens !", 0, 10);
        g.drawImage(rock,160,this.getHeight()/2-30,this);
    }

}
