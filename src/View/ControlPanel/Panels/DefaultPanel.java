package View.ControlPanel.Panels;

import View.ImageManager;

import javax.swing.*;
import java.awt.*;

import static View.ImageManager.THUMBNAIL_SIZE;

public class DefaultPanel extends JPanel {

    @Override
    public void paintComponent(Graphics g){
        setBackground(Color.BLUE);
        g.drawImage(ImageManager.thumbnailMars, this.getWidth()/2-THUMBNAIL_SIZE/2,this.getHeight()/2-THUMBNAIL_SIZE/2,null);
    }
}
