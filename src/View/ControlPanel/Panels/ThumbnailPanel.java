package View.ControlPanel.Panels;

import View.ImageManager;

import javax.swing.*;
import java.awt.*;

import static View.ImageManager.THUMBNAIL_SIZE;

public class ThumbnailPanel extends JPanel {

    String name;

    public ThumbnailPanel(String name) {
        this.name = name;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ImageManager.getThumbnail(name), this.getWidth()/2-THUMBNAIL_SIZE/2,this.getHeight()/2-THUMBNAIL_SIZE/2,null);

    }
}
