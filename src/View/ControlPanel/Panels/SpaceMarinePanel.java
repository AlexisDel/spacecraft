package View.ControlPanel.Panels;

import View.ImageManager;

import javax.swing.*;
import java.awt.*;

import static View.ImageManager.THUMBNAIL_SIZE;

public class SpaceMarinePanel extends JPanel {

    @Override
    public void paintComponent(Graphics g){
        setBackground(Color.BLUE);
        g.drawImage(ImageManager.thumbnailSpaceMarine, this.getWidth()/2-THUMBNAIL_SIZE/2,this.getHeight()/2-THUMBNAIL_SIZE/2,null);
    }

}
