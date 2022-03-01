package View.ControlPanel.Panels;

import View.ControlPanel.ControlPanel;
import View.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static View.ImageManager.THUMBNAIL_SIZE;

public class SpaceMarinePanel extends JPanel implements ActionListener {
    private ControlPanel controlPanel;

    public SpaceMarinePanel(ControlPanel cp){
        this.controlPanel=cp;
        createButton();
    }
    public void createButton(){
        JButton move= new JButton("Move");
        move.setFocusable(false);
        move.addActionListener(this);
        this.add(move);
    }
    @Override
    public void paintComponent(Graphics g){
        setBackground(Color.BLUE);
        g.drawImage(ImageManager.thumbnailSpaceMarine, this.getWidth()/2-THUMBNAIL_SIZE/2,this.getWidth()/2-THUMBNAIL_SIZE/2,null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Move":
                this.controlPanel.setWaitingForCoord(true);
                //this.actionWaitingForCoordinates= Action.MOVE;
        }


    }
}
