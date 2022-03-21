package View.ControlPanel.Panels;

import View.FontManager;

import javax.swing.*;
import java.awt.*;

public class GameInstructionPanel extends JPanel {

    public GameInstructionPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(this.getPreferredSize());

        JLabel title = new JLabel("Game Instructions");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(FontManager.Dune2000.deriveFont(20));
        this.add(title);

        // Separator
        this.add(Box.createRigidArea(new Dimension(1, 10)));

        JTextArea text = new JTextArea(
                "1. -Zoom instructions-\n" +
                "2. Click on items to  get information and execute instructions like move, attack or mine \n");
        text.setFont(FontManager.Dune2000.deriveFont(13));
        text.setOpaque(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(text);
    }
}
