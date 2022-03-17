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

        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(20, 10, this.getWidth()-20, 10);

        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 16));

        g.drawString("HP : ", 20, 30);
        int nbHeart = controlPanel.getSelectedItem().getHealthPoints() / 50;
        for(int i = 0; i < nbHeart; i++){
            g.drawImage(ImageManager.getControlPanelAssets("Heart"), (55+i*16), 18, null);
        }

        if (controlPanel.getSelectedItem() instanceof Entity){
            Entity entity = (Entity) controlPanel.getSelectedItem();

            g.drawString("AD : ", 20, 50);
            int nbSword = entity.getAttackDamage() / 10;
            for(int i = 0; i < nbSword; i++){
                g.drawImage(ImageManager.getControlPanelAssets("Sword"), (55+i*16), 38, null);
            }

        }
    }
}
