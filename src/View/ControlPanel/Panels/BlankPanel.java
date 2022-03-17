package View.ControlPanel.Panels;

import View.ImageManager;

import javax.swing.*;
import java.awt.*;

public class BlankPanel extends JPanel {

    String backgroundPanel;

    public BlankPanel(String ressourceName) {
        this.backgroundPanel = ressourceName;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(ImageManager.getControlPanelBG(backgroundPanel), 0, 0, null);
    }
}
