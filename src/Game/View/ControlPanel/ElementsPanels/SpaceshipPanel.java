package Game.View.ControlPanel.ElementsPanels;

import Game.View.ControlPanel.GameControlPanel;
import Game.View.ControlPanel.Panels.DescriptionPanel;
import Game.View.ControlPanel.Panels.StatsPanel;
import Game.View.ControlPanel.Panels.ThumbnailPanel;

import javax.swing.*;
import java.awt.*;

public class SpaceshipPanel extends JPanel {
    private String[] description={"Coolest and fastest SpaceShip out there.","","",""};
            //"Get half of the rocks", "to activate"};

    public SpaceshipPanel(GameControlPanel gameControlPanel) {
        this.setLayout(new GridLayout(3,1));
        this.add(new ThumbnailPanel("Spaceship"));
        this.add(new StatsPanel(gameControlPanel));
        this.add(new DescriptionPanel(description));
    }
}
