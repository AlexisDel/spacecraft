package View.ControlPanel.ItemsPanels;

import View.ControlPanel.Panels.GameInstructionPanel;
import View.ControlPanel.Panels.GamePlayPanel;
import View.ControlPanel.Panels.GoalPanel;
import View.FontManager;
import View.ImageManager;

import javax.swing.*;
import java.awt.*;

import static View.ImageManager.THUMBNAIL_SIZE;

public class DefaultPanel extends JPanel {

    public DefaultPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel(new ImageIcon(ImageManager.launcherLogo));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(logo);
        this.add(new GoalPanel());
        this.add(new GameInstructionPanel());
        this.add(new GamePlayPanel());
    }
}
