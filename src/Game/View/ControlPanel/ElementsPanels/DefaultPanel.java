package Game.View.ControlPanel.ElementsPanels;

import Game.View.ControlPanel.Panels.GameInstructionPanel;
import Game.View.ControlPanel.Panels.GamePlayPanel;
import Game.View.ControlPanel.Panels.GoalPanel;
import Game.View.RessourceManager;

import javax.swing.*;
import java.awt.*;

/**
 * Control Panel par defaut, il donne les instructions à l'utilisateur
 */
public class DefaultPanel extends JPanel {
    /**
     * Constructeur
     */
    public DefaultPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //On ajoute le logo du jeu au milieu
        JLabel logo = new JLabel(new ImageIcon(RessourceManager.ImageManager.launcherLogo));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(logo);
        //On ajoute les subpanels avec le reste du texte
        this.add(new GoalPanel());
        this.add(new GameInstructionPanel());
        this.add(new GamePlayPanel());
    }
}
