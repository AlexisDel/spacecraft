package Launcher;

import Controller.BoardController;
import Model.GameConstants;
import Model.GameEngine;
import View.GameView;
import View.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LauncherPanel extends JPanel {

    public LauncherPanel(JFrame window) {
        this.setPreferredSize(new Dimension(320, 480));
        this.setLayout(new GridLayout(3,1));

        this.add(new JLabel(new ImageIcon(ImageManager.launcherLogo)));

        SettingsPanel settingsPanel = new SettingsPanel();
        this.add(settingsPanel);

        JButton start = new JButton(new ImageIcon(ImageManager.startButton));
        start.setBorderPainted(false);
        start.setContentAreaFilled(false);
        start.setFocusPainted(false);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameConstants(settingsPanel.getNbSpaceMarines(), settingsPanel.getNbAliens(), settingsPanel.getNbMeteorites(), settingsPanel.getNbMountains(), settingsPanel.getPourcentMountain());
                GameEngine gameEngine = new GameEngine();
                GameView gameView = new GameView(gameEngine);
                BoardController boardController = new BoardController(gameView);
                gameView.setBoardController(boardController);
                window.dispose();
            }
        });
        this.add(start);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ImageManager.launcherBackground, 0, 0, null);

    }
}
