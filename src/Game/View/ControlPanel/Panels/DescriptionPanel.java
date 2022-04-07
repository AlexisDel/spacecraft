package Game.View.ControlPanel.Panels;

import Game.View.RessourceManager;

import javax.swing.*;
import java.awt.*;

/**
 * C'est le panneau qui contient les descriptions textuelles des objets cliqués
 */
public class DescriptionPanel extends JPanel {
    /**Attribut*/
    private String[] description;

    /**
     * Constructeur
     * @param description la description textuelle de l'objet sélectionné
     */
    public DescriptionPanel(String[] description) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(128, 64));
        //On initialise l'attribut
        this.description=description;
    }

    /**
     * Méthode qui dessine sur le panneau DescriptionPanel
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(20, 10, this.getWidth()-20, 10);
        //On choisi la couleur et la police d'écriture
        g.setColor(Color.BLACK);
        g.setFont(RessourceManager.FontManager.Dune2000_15.deriveFont(16));
        //On affiche la description dans 4 lignes de texte
        g.drawString(description[0], 20, 30);
        g.drawString(description[1], 20, 46);
        g.drawString(description[2], 20, 62);
        g.drawString(description[3], 20, 78);
    }
}
//"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"