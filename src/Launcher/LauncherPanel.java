package Launcher;

import Game.Controller.BoardController;
import Game.Model.GameConstants;
import Game.Model.GameEngine;
import Game.View.GameView;
import Game.View.RessourceManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LauncherPanel extends JPanel {

    public LauncherPanel(JFrame window) {
        this.setPreferredSize(new Dimension(320, 480));
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1;

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(new JLabel(new ImageIcon(RessourceManager.ImageManager.launcherLogo)), gbc);

        SettingsPanel settingsPanel = new SettingsPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(settingsPanel, gbc);

        JButton start = new JButton(new ImageIcon(RessourceManager.ImageManager.startButton));
        start.setBorderPainted(false);
        start.setContentAreaFilled(false);
        start.setFocusPainted(false);
        start.setRolloverIcon(new ImageIcon(RessourceManager.ImageManager.clickedStartButton));
        start.setPressedIcon(new ImageIcon(RessourceManager.ImageManager.clickedStartButton));
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameConstants(settingsPanel.getNbSpaceMarines(), settingsPanel.getNbAliens(), settingsPanel.getNbMeteorites(), settingsPanel.getNbMountains(), settingsPanel.getDifficulty(), settingsPanel.getPlayerName(), settingsPanel.getSeed());
                GameEngine gameEngine = new GameEngine();
                GameView gameView = new GameView(gameEngine);
                BoardController boardController = new BoardController(gameView);
                gameView.setBoardController(boardController);
                window.dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(start, gbc);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(RessourceManager.ImageManager.launcherBackground, 0, 0, null);

    }
}
