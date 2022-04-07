package Game.View.ControlPanel.ElementsPanels;

import Game.View.ControlPanel.GameControlPanel;
import Game.View.ControlPanel.Panels.DescriptionPanel;
import Game.View.ControlPanel.Panels.StatsPanel;
import Game.View.ControlPanel.Panels.ThumbnailPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Cette classe est le Panel de Control du SpaceShip
 */
public class SpaceshipPanel extends JPanel {
    /** Variable */
    private static String[] description={"Coolest and fastest SpaceShip out there.","","",""};
            //"Get half of the rocks", "to activate"};

    /**
     * Constructeur
     * @param gameControlPanel le Control Panel principal
     */
    public SpaceshipPanel(GameControlPanel gameControlPanel) {
        this.setLayout(new GridLayout(3,1));
        this.add(new ThumbnailPanel("Spaceship"));
        this.add(new StatsPanel(gameControlPanel));
        //On ajoute la description du spaceship dans la variable statique
        this.add(new DescriptionPanel(description));
    }
}
