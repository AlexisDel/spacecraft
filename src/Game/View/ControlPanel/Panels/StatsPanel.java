package Game.View.ControlPanel.Panels;

import Game.Model.GameElements.Layer1.Entities.Entity;
import Game.View.ControlPanel.GameControlPanel;
import Game.View.RessourceManager;

import javax.swing.*;
import java.awt.*;
/** Cette classe affiche les données de chaque entité dans son panneau */
public class StatsPanel extends JPanel {
    /**Attribut */
    GameControlPanel gameControlPanel;
    /** Contructeur */
    public StatsPanel(GameControlPanel gameControlPanel) {
        this.setDoubleBuffered(true);
        this.gameControlPanel = gameControlPanel;
        this.setPreferredSize(new Dimension(300, 64));
    }

    /**
     * Cette méthode va dessiner les données de l'objet sélectionné
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(20, 10, this.getWidth()-20, 10);
        //On prépare la couleur et la font du texte
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        //On affiche les points de vie
        g.drawString("HP : ", 20, 30);
        int nbHeart = gameControlPanel.getSelectedItem().getHealthPoints() / 50;
        for(int i = 0; i < nbHeart; i++){
            //Pour chaque 50 points de vie de l'objet on affiche l'image d'un coeur
            g.drawImage(RessourceManager.ImageManager.getControlPanelAssets("Heart"), (55+i*16), 18, null);
        }
        //Dans le cas d'une entité on affiche les dégats
        if (gameControlPanel.getSelectedItem() instanceof Entity){
            Entity entity = (Entity) gameControlPanel.getSelectedItem();
            //Pour chaque 10 points de dégats on affiche l'image d'une épée
            g.drawString("AD : ", 20, 50);
            int nbSword = entity.getAttackDamage() / 10;
            for(int i = 0; i < nbSword; i++){
                //affichage de l'épée
                g.drawImage(RessourceManager.ImageManager.getControlPanelAssets("Sword"), (55+i*16), 38, null);
            }
            //On affiche une roche suivi du nombre de roches dans l'inventaire de l'entité
            g.drawImage(RessourceManager.ImageManager.getControlPanelAssets("Rock").getScaledInstance(20, 20, Image.SCALE_SMOOTH), 20, 55, null);
            g.drawString(": "+entity.getRocks(), 46, 70);

        }
    }
}
