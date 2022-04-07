package Game.View.ControlPanel.ElementsPanels;

import Game.View.ControlPanel.GameControlPanel;
import Game.View.ControlPanel.Panels.ActionPanel;
import Game.View.ControlPanel.Panels.DescriptionPanel;
import Game.View.ControlPanel.Panels.StatsPanel;
import Game.View.ControlPanel.Panels.ThumbnailPanel;

import javax.swing.*;
import java.awt.*;

public class SpaceMarinePanel extends JPanel {
    /** Variable*/
    private static String[] description= {"A legendary intergalactic hero you control.", "SpaceMarines are world conquerers.","Their mission is to get all the rocks.","Achieve this goal by ANY means!"};

    public SpaceMarinePanel(GameControlPanel gameControlPanel){
        this.setLayout(new GridLayout(3,1));
        this.add(new ThumbnailPanel("SpaceMarine"));

        JPanel statsActions = new JPanel(new GridLayout(2,1));
        statsActions.add(new StatsPanel(gameControlPanel));
        statsActions.add(new ActionPanel(gameControlPanel));

        this.add(statsActions);
        this.add(new DescriptionPanel(description));
    }
}
