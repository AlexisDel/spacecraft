package View.ControlPanel.ItemsPanels;

import View.ControlPanel.ControlPanel;
import View.ControlPanel.Panels.DescriptionPanel;
import View.ControlPanel.Panels.StatsPanel;
import View.ControlPanel.Panels.ThumbnailPanel;

import javax.swing.*;
import java.awt.*;

public class MeteoritePanel extends JPanel {

    public MeteoritePanel(ControlPanel controlPanel){
        this.setLayout(new GridLayout(3,1));
        this.add(new ThumbnailPanel("Meteorite"));

        JPanel statsActions = new JPanel(new BorderLayout());
        statsActions.add(new StatsPanel(controlPanel),BorderLayout.NORTH);

        this.add(statsActions);
        this.add(new DescriptionPanel());
    }
}
