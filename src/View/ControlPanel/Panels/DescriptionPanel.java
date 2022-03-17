package View.ControlPanel.Panels;

import javax.swing.*;
import java.awt.*;

public class DescriptionPanel extends JPanel {

    public DescriptionPanel() {
        this.setPreferredSize(new Dimension(128, 64));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(20, 10, this.getWidth()-20, 10);

        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 16));

        g.drawString("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 20, 30);
        g.drawString("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 20, 46);
        g.drawString("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 20, 62);
        g.drawString("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 20, 78);
    }
}
