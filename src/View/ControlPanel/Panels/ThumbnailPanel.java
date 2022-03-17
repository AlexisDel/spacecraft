package View.ControlPanel.Panels;

import javax.swing.*;
import java.awt.*;

import static View.ImageManager.*;

public class ThumbnailPanel extends JPanel {

    String name;

    public ThumbnailPanel(String name) {
        this.name = name;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(controlPanelBGTop, 0, 0, null);;
        g.drawImage(getThumbnail(name), this.getWidth()/2-100/2,this.getHeight()/2-THUMBNAIL_SIZE/2,null);
        g.drawImage(thumbnailBorder, this.getWidth()/2-BORDER_SIZE/2, this.getHeight()/2-BORDER_SIZE/2, null);

    }
}
