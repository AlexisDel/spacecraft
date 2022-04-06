package Game.View.ControlPanel.ElementsPanels;

import Game.View.ControlPanel.Panels.GameInstructionPanel;
import Game.View.ControlPanel.Panels.GamePlayPanel;
import Game.View.ControlPanel.Panels.GoalPanel;
import Game.View.RessourceManager;

import javax.swing.*;
import java.awt.*;

public class DefaultPanel extends JPanel {

    public DefaultPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel(new ImageIcon(RessourceManager.ImageManager.launcherLogo));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(logo);
        this.add(new GoalPanel());
        this.add(new GameInstructionPanel());
        this.add(new GamePlayPanel());
    }
}
