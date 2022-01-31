package View;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    public GUI(String name) {
        this.pack();
        this.setTitle(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(ViewConstants.WINDOW_WIDTH, ViewConstants.WINDOW_HEIGHT));
        this.setResizable(false);
        this.setVisible(true);
    }
}
