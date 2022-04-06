package Game.View.ControlPanel.ElementsPanels;

import Game.View.ControlPanel.GameControlPanel;
import Game.View.ControlPanel.Panels.DescriptionPanel;
import Game.View.ControlPanel.Panels.StatsPanel;
import Game.View.ControlPanel.Panels.ThumbnailPanel;

import javax.swing.*;
import java.awt.*;

public class MeteoritePanel extends JPanel {
    private String[] description= {"Meteorites fallen from the sky.","They are contain a certain amount rocks.", "Mine them to get rocks.", "Get all the rocks as FAST as possible."};

    public MeteoritePanel(GameControlPanel gameControlPanel){
        this.setLayout(new GridLayout(3,1));
        this.add(new ThumbnailPanel("Meteorite"));
        this.add(new StatsPanel(gameControlPanel));
        this.add(new DescriptionPanel(description));
    }
}
