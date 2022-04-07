package Launcher;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LauncherWindow extends JFrame {

    public LauncherWindow() throws IOException {
        this.setTitle("SpaceCraft Launcher");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(new Dimension(320, 480));

        this.add(new LauncherPanel(this));
        this.setIconImage(ImageIO.read(new File("resources/logo_spacemarines.png")));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
