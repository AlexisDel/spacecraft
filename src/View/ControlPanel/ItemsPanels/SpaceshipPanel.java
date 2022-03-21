package View.ControlPanel.ItemsPanels;

import View.ControlPanel.ControlPanel;
import View.ControlPanel.Panels.DescriptionPanel;
import View.ControlPanel.Panels.StatsPanel;
import View.ControlPanel.Panels.ThumbnailPanel;

import javax.swing.*;
import java.awt.*;

public class SpaceshipPanel extends JPanel {
    private String[] description={"Coolest and fastest SpaceShip out there.","","",""};
            //"Get half of the rocks", "to activate"};

    public SpaceshipPanel(ControlPanel controlPanel) {
        this.setLayout(new GridLayout(3,1));
        this.add(new ThumbnailPanel("Spaceship"));

        JPanel statsActions = new JPanel(new BorderLayout());
        statsActions.add(new StatsPanel(controlPanel),BorderLayout.NORTH);

        this.add(statsActions);
        this.add(new DescriptionPanel(description));
    }
}
