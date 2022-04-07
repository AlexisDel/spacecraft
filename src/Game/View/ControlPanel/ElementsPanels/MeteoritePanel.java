package Game.View.ControlPanel.ElementsPanels;

import Game.View.ControlPanel.GameControlPanel;
import Game.View.ControlPanel.Panels.DescriptionPanel;
import Game.View.ControlPanel.Panels.StatsPanel;
import Game.View.ControlPanel.Panels.ThumbnailPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Cette classe décrit le Control Panel des météorites
 */
public class MeteoritePanel extends JPanel {
    /** Variable */
    private static String[] description= {"Meteorites fallen from the sky.","They contain a certain amount rocks.", "Mine them to get rocks.", "Get all the rocks as FAST as possible."};

    /**
     * Constructeur
     * @param gameControlPanel
     */
    public MeteoritePanel(GameControlPanel gameControlPanel){
        this.setLayout(new GridLayout(3,1));
        this.add(new ThumbnailPanel("Meteorite"));
        this.add(new StatsPanel(gameControlPanel));
        this.add(new DescriptionPanel(description));
    }
}
