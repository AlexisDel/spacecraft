package View.ControlPanel.Panels;

import Model.Layer1.Entities.Actions.Mine;
import Model.Layer1.Entities.Entity;
import View.ControlPanel.ControlPanel;
import View.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Model.Layer1.Entities.Actions.Action.*;
import static View.ImageManager.THUMBNAIL_SIZE;

public class SpaceMarinePanel extends JPanel implements ActionListener {
    private ControlPanel controlPanel;
    private ArrayList<JButton> buttons;

    public SpaceMarinePanel(ControlPanel cp){
        this.controlPanel=cp;
        buttons= new ArrayList<>();
        createButton();
    }
    public void createButton(){
        JButton move= new JButton("Move");
        move.addActionListener(this);
        buttons.add(move);
        this.add(move);

        JButton mine= new JButton("Mine");
        mine.addActionListener(this);
        buttons.add(mine);
        this.add(mine);
    }

    public void disableButton(String stopButton){
        for(JButton button: this.buttons){
            if(stopButton==button.getText()){
                button.setEnabled(false);
            }
        }
    }

    public void enableButton(String stopButton){
        for(JButton button: this.buttons){
            if(stopButton==button.getText()){
                button.setEnabled(true);
            }
        }
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
            case "Mine"->{
                this.controlPanel.setExecutingAction(MINE);
                //On mine le meteorite le plus proche
                Mine m= new Mine((Entity) controlPanel.getSelectedItem(), controlPanel.getGameEngine().getGameBoard());
                //lock mouvement button
                //proc progress bar
                }
        }
    }
}
