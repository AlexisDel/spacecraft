package View.ControlPanel.Panels;

import Model.Layer1.Entities.Actions.Mine;
import Model.Layer1.Entities.Entity;
import View.ControlPanel.ControlPanel;
import View.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Model.Layer1.Entities.Actions.Action.*;
import static View.ImageManager.THUMBNAIL_SIZE;

public class SpaceMarinePanel extends JPanel implements ActionListener {
    private ControlPanel controlPanel;

    public SpaceMarinePanel(ControlPanel cp){
        this.controlPanel=cp;
        createButton();
    }
    public void createButton(){
        JButton move= new JButton("Move");
        move.addActionListener(this);
        this.add(move);

        JButton mine= new JButton("Mine");
        mine.addActionListener(this);
        this.add(mine);
    }
    @Override
    public void paintComponent(Graphics g){
        setBackground(Color.BLUE);
        g.drawImage(ImageManager.thumbnailSpaceMarine, this.getWidth()/2-THUMBNAIL_SIZE/2,this.getWidth()/2-THUMBNAIL_SIZE/2,null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Move"->{
                this.controlPanel.setWaitingAction(MOVE);
            }
            case "Mine"->{//On mine le meteorite le plus proche
                Mine m= new Mine((Entity) controlPanel.getSelectedItem(), controlPanel.getGameEngine().getGameBoard());
                //lock mouvement button
                //proc progress bar
                }
        }
    }
}
