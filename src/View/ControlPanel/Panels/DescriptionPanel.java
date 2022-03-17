package View.ControlPanel.Panels;

import View.ImageManager;

import javax.swing.*;
import java.awt.*;

public class DescriptionPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ImageManager.controlPanelBGBot, 0, 0, null);
    }
}
