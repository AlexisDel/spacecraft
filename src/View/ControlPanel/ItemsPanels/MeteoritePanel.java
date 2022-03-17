package View.ControlPanel.ItemsPanels;

import View.ControlPanel.ControlPanel;
import View.ControlPanel.Panels.BlankPanel;
import View.ControlPanel.Panels.DescriptionPanel;
import View.ControlPanel.Panels.StatsPanel;
import View.ControlPanel.Panels.ThumbnailPanel;

import javax.swing.*;
import java.awt.*;

public class MeteoritePanel extends JPanel {

    public MeteoritePanel(ControlPanel controlPanel){
        this.setLayout(new GridLayout(4,1));
        this.add(new ThumbnailPanel("Meteorite"));
        this.add(new StatsPanel(controlPanel));
        this.add(new BlankPanel("Mid2"));
        this.add(new DescriptionPanel());
    }
}