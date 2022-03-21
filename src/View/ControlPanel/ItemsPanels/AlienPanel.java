package View.ControlPanel.ItemsPanels;

import View.ControlPanel.ControlPanel;
import View.ControlPanel.Panels.DescriptionPanel;
import View.ControlPanel.Panels.StatsPanel;
import View.ControlPanel.Panels.ThumbnailPanel;

import javax.swing.*;
import java.awt.*;

public class AlienPanel extends JPanel {
    private String[] description = {"Evil creatures they are.", "Eating rocks they do.", "ADVICE:", "Deadly force on them you should use."};

    public AlienPanel(ControlPanel controlPanel) {
        this.setLayout(new GridLayout(3,1));
        this.add(new ThumbnailPanel("Alien"));

        JPanel statsActions = new JPanel(new BorderLayout());
        statsActions.add(new StatsPanel(controlPanel),BorderLayout.NORTH);

        this.add(statsActions);
        this.add(new DescriptionPanel(description));
    }
}
