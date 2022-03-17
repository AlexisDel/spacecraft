package View.ControlPanel.ItemsPanels;

import View.ControlPanel.ControlPanel;
import View.ControlPanel.Panels.ActionPanel;
import View.ControlPanel.Panels.DescriptionPanel;
import View.ControlPanel.Panels.StatsPanel;
import View.ControlPanel.Panels.ThumbnailPanel;

import javax.swing.*;
import java.awt.*;

public class SpaceMarinePanel extends JPanel {

    public SpaceMarinePanel(ControlPanel controlPanel){
        this.setLayout(new GridLayout(4,1));
        this.add(new ThumbnailPanel("SpaceMarine"));
        this.add(new StatsPanel(controlPanel));
        this.add(new ActionPanel(controlPanel));
        this.add(new DescriptionPanel());
    }
}
