package View.ControlPanel.Panels;

import View.FontManager;

import javax.swing.*;
import java.awt.*;

public class GoalPanel extends JPanel {

    public GoalPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(this.getPreferredSize());

        JLabel title = new JLabel("Goals");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(FontManager.Dune2000.deriveFont(20));
        this.add(title);

        // Separator
        this.add(Box.createRigidArea(new Dimension(1, 10)));

        JTextArea text = new JTextArea("Capture all the rocks in the map by mining the meteorites in the surface of Mars or killing the aliens that have captured rocks AS SOON AS POSSIBLE. The fastest player will be placed at the top of the leaderboard (visible after the game ends)");
        text.setFont(FontManager.Dune2000.deriveFont(13));
        text.setOpaque(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(text);
    }
}
