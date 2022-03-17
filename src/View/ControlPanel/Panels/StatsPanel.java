package View.ControlPanel.Panels;

import Model.Layer1.Entities.Entity;
import View.ControlPanel.ControlPanel;
import View.ImageManager;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {

    ControlPanel controlPanel;

    public StatsPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
        this.setPreferredSize(new Dimension(300, 64));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        g.drawString("Stats", 10, 20);

        g.drawString("HP : ", 10, 30);
        int nbHeart = controlPanel.getSelectedItem().getHealthPoints() / 20;
        for(int i = 0; i < nbHeart; i++){
            g.drawImage(ImageManager.getControlPanelAssets("Heart"), (40+i*16), 20, null);
        }

        if (controlPanel.getSelectedItem() instanceof Entity){
            Entity entity = (Entity) controlPanel.getSelectedItem();

            g.drawString("AD : ", 10, 40);
            int nbSword = entity.getAttackDamage() / 10;
            for(int i = 0; i < nbSword; i++){
                g.drawImage(ImageManager.getControlPanelAssets("Sword"), (40+i*16), 50, null);
            }

        }
    }
}
