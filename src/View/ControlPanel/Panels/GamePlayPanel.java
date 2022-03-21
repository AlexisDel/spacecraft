package View.ControlPanel.Panels;

import View.FontManager;

import javax.swing.*;
import java.awt.*;

public class GamePlayPanel extends JPanel {

    public GamePlayPanel() throws HeadlessException {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Gameplay");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(FontManager.Dune2000.deriveFont(20));
        this.add(title);

        // Separator
        this.add(Box.createRigidArea(new Dimension(1, 10)));

        JTextArea text = new JTextArea(
                "3. To move a space marine click on it , click on move, and then click on an empty space in the map where you want to go \n" +
                "4. To mine a meteorite, place a space marine in a space adjacent to the Meteorite and then click on his mine button\n" +
                "5. To kill an alien you must place a space marine near enough from an alien and then click on his attack button");
        text.setFont(FontManager.Dune2000.deriveFont(13));
        text.setOpaque(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(text);
    }
}
