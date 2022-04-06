package Game.View.ControlPanel.Panels;

import Game.Model.GameElements.Layer1.Entities.Entity;
import Game.View.ControlPanel.GameControlPanel;
import Game.View.RessourceManager;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {

    GameControlPanel gameControlPanel;

    public StatsPanel(GameControlPanel gameControlPanel) {
        this.setDoubleBuffered(true);
        this.gameControlPanel = gameControlPanel;
        this.setPreferredSize(new Dimension(300, 64));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(20, 10, this.getWidth()-20, 10);

        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 16));

        g.drawString("HP : ", 20, 30);
        int nbHeart = gameControlPanel.getSelectedItem().getHealthPoints() / 50;
        for(int i = 0; i < nbHeart; i++){
            g.drawImage(RessourceManager.ImageManager.getControlPanelAssets("Heart"), (55+i*16), 18, null);
        }

        if (gameControlPanel.getSelectedItem() instanceof Entity){
            Entity entity = (Entity) gameControlPanel.getSelectedItem();

            g.drawString("AD : ", 20, 50);
            int nbSword = entity.getAttackDamage() / 10;
            for(int i = 0; i < nbSword; i++){
                g.drawImage(RessourceManager.ImageManager.getControlPanelAssets("Sword"), (55+i*16), 38, null);
            }

            g.drawImage(RessourceManager.ImageManager.getControlPanelAssets("Rock").getScaledInstance(20, 20, Image.SCALE_SMOOTH), 20, 55, null);
            g.drawString(": "+entity.getRocks(), 46, 70);

        }
    }
}
