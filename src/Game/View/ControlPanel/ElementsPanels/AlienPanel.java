package Game.View.ControlPanel.ElementsPanels;

import Game.View.ControlPanel.GameControlPanel;
import Game.View.ControlPanel.Panels.DescriptionPanel;
import Game.View.ControlPanel.Panels.StatsPanel;
import Game.View.ControlPanel.Panels.ThumbnailPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Control Panel des Aliens
 */
public class AlienPanel extends JPanel {
    private String[] description = {"Evil creatures they are.", "Eating rocks they do.", "ADVICE:", "Deadly force on them you should use."};

    public AlienPanel(GameControlPanel gameControlPanel) {
        this.setLayout(new GridLayout(3,1));
        this.add(new ThumbnailPanel("Alien"));
        this.add(new StatsPanel(gameControlPanel));
        this.add(new DescriptionPanel(description));
    }
}
