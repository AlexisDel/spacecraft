package View.ControlPanel.Panels;

import View.ImageManager;

import javax.swing.*;
import java.awt.*;

import static View.ImageManager.THUMBNAIL_SIZE;

public class AlienPanel extends JPanel {

    @Override
    public void paintComponent(Graphics g){
            g.drawImage(ImageManager.thumbnailAlien, this.getWidth()/2-THUMBNAIL_SIZE/2,this.getHeight()/2-THUMBNAIL_SIZE/2,null);
    }
}
