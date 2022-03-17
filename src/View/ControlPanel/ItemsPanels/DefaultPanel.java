package View.ControlPanel.ItemsPanels;

import View.ImageManager;

import javax.swing.*;
import java.awt.*;

import static View.ImageManager.THUMBNAIL_SIZE;

public class DefaultPanel extends JPanel {
    public DefaultPanel() {
        //TODO clean this mess up
        this.setLayout(new BorderLayout());
        Label welcome= new Label("                 WELCOME TO SPACECRAFT");
        this.add(welcome, BorderLayout.NORTH);
        JPanel col = new JPanel();
        GridLayout lay =new GridLayout(11, 0);
        lay.setVgap(25);
        col.setLayout(lay);
        Label camera= new Label("      To move the camera: DRAG AND DROP");
        Label zoom=   new Label("          To zoom: UP & DOWN ARROW KEYS");
        Label click=  new Label("       Click on entities to interact with them");

        col.add(camera);
        col.add(zoom);
        col.add(click);

        this.add(col, BorderLayout.SOUTH);

    }

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(ImageManager.thumbnailMars, this.getWidth()/2-THUMBNAIL_SIZE/2,this.getHeight()/8-THUMBNAIL_SIZE/2+20,null);
    }
}
