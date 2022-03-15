package View.ControlPanel.Panels;

import Model.Layer1.InteractiveItem;
import View.ControlPanel.ControlPanel;
import View.ImageManager;

import javax.swing.*;
import java.awt.*;

import static View.ImageManager.THUMBNAIL_SIZE;

public class MeteoritePanel extends JPanel {
    private ControlPanel controlPanel;

    public MeteoritePanel(ControlPanel cp){
        this.controlPanel=cp;
        displayStats();
    }

    public void displayStats(){
        JLabel label= new JLabel();
        label.setText("HP: "+String.valueOf(controlPanel.getSelectedItem().getHealthPoints()));
        this.add(label);
    }

    @Override
    public void paintComponent(Graphics g){
        setBackground(Color.ORANGE);
        g.drawImage(ImageManager.thumbnailMeteorite, this.getWidth()/2-THUMBNAIL_SIZE/2,this.getWidth()/2-THUMBNAIL_SIZE/2,null);
    }

}
