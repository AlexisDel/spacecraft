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
import java.io.IOException;

public class LauncherPanel extends JPanel {

    public LauncherPanel(JFrame window) {
        // Configuration de la fenêtre
        this.setPreferredSize(new Dimension(320, 480));
        this.setLayout(new GridBagLayout());

        // Configuration de l'agencement des éléments affiché dans la fenêtre
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1;

        // Affichage du logo
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(new JLabel(new ImageIcon(RessourceManager.ImageManager.launcherLogo)), gbc);

        // Affichage du panneau dans lequel l'utilisateur change les paramètres du jeu
        SettingsPanel settingsPanel = new SettingsPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(settingsPanel, gbc);

        // Affichage du bouton start permettant de lancer le jeu
        JButton start = new JButton(new ImageIcon(RessourceManager.ImageManager.startButton));

        // Configuration du bouton start
        start.setBorderPainted(false);
        start.setContentAreaFilled(false);
        start.setFocusPainted(false);
        start.setRolloverIcon(new ImageIcon(RessourceManager.ImageManager.clickedStartButton));
        start.setPressedIcon(new ImageIcon(RessourceManager.ImageManager.clickedStartButton));
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Récupération des paramètres entrée dans le launcher par l'utilisateur
                new GameConstants(settingsPanel.getNbSpaceMarines(), settingsPanel.getNbAliens(), settingsPanel.getNbMeteorites(), settingsPanel.getNbMountains(), settingsPanel.getDifficulty(), settingsPanel.getPlayerName(), settingsPanel.getSeed());

                // Lancement du jeu
                GameEngine gameEngine = new GameEngine();
                GameView gameView = null;

                try {
                    gameView = new GameView(gameEngine);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                BoardController boardController = new BoardController(gameView);
                gameView.setBoardController(boardController);

                // Fermeture du launcher
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
        // Dessin de l'arrière-plan
        g.drawImage(RessourceManager.ImageManager.launcherBackground, 0, 0, null);

    }
}
