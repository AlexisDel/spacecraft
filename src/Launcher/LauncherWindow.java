package Launcher;

import javax.swing.*;
import java.awt.*;

public class LauncherWindow extends JFrame {

    public LauncherWindow() {
        this.setTitle("SpaceCraft Launcher");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(new Dimension(320, 480));

        this.add(new LauncherPanel(this));

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
