package Game.View.ControlPanel.Panels;

import javax.swing.*;
import java.awt.*;

import static Game.View.RessourceManager.ImageManager.*;
/** Méthode */
public class ThumbnailPanel extends JPanel {
    /**Attribut*/
    String name;
    /** Constructeur*/
    public ThumbnailPanel(String name) {
        this.name = name;
        this.setPreferredSize(new Dimension(300,256));
        this.setDoubleBuffered(true);
    }
    /** Cette méthode dessine l'image dans le panneau*/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getThumbnail(name), this.getWidth()/2-THUMBNAIL_SIZE/2,this.getHeight()/2-THUMBNAIL_SIZE/2,null);
        g.drawImage(thumbnailBorder, this.getWidth()/2-BORDER_SIZE/2, this.getHeight()/2-BORDER_SIZE/2, null);

    }
}
