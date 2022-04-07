package Game.View;

import Game.Controller.BoardController;
import Game.Model.GameConstants;
import Game.Model.GameEngine;
import Game.View.Board.GameBoardPanel;
import Game.View.ControlPanel.GameControlPanel;
import GameOver.LeaderBoard;
import GameOver.LeaderboardWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class GameView extends JFrame implements Runnable{

    // Thread responsable du rafraichissement de l'affichage
    private Thread displayUpdateThread;
    // Pointeur vers l'affichage du terrain de jeu
    private GameBoardPanel gameBoardPanel;
    // Pointeur vers l'affichage du panneau de contrôle
    private GameControlPanel gameControlPanel;
    // Pointeur vers le moteur du jeu
    private GameEngine gameEngine;

    public GameView(GameEngine gameEngine) throws IOException {

        this.gameEngine = gameEngine;

        // Initialisation de la fenêtre
        initWindow();

        // Ajouts de l'affichage du terrain à la fenêtre
        gameBoardPanel = new GameBoardPanel(gameEngine);
        this.add(gameBoardPanel, BorderLayout.WEST);

        // Ajouts de l'affichage du panneau de contrôle à la fenêtre
        gameControlPanel = new GameControlPanel(gameEngine);
        this.add(gameControlPanel, BorderLayout.EAST);

        // Création et démarrage du thread rafraichissant l'affichage
        displayUpdateThread = new Thread(this);
        displayUpdateThread.start();

        // Modification de l'icône de la fenêtre
        this.setIconImage(ImageIO.read(new File("resources/logo_spacemarines.png")));

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Initialisation de la fenêtre
     */
    private void initWindow() {
        this.setTitle("Spacecraft");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }

    @Override
    /**
     * Fonction responsable du rafraichissement de la fenêtre
     */
    public void run() {
        while(!gameEngine.getGameBoard().isGameOver()){
            // Rafraichissement de l'affichage du terrain de jeu
            gameBoardPanel.repaint();
            // Rafraichissement de l'affichage du panneau de contrôle
            gameControlPanel.repaint();
            try {
                sleep(1000/ViewConstants.FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Libère les ressources utilisées par le système pour l'affichage du jeu
        this.dispose();

        try {
            // Création et affichage de la fenêtre du leaderboard
            new LeaderboardWindow(new LeaderBoard(GameConstants.PLAYER_NAME, (int)gameEngine.getGameBoard().getTimer().getTime()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ajoutes des different listeners à l'affichage du terrain afin que l'utilisateur puisse interagir avec celui-ci
     * @param boardController
     */
    public void setBoardController(BoardController boardController) {
        getBoardPanel().addMouseListener(boardController);
        getBoardPanel().addMouseMotionListener(boardController);
        getBoardPanel().addMouseWheelListener(boardController);
    }

    /**
     * Getter affichage terrain
     * @return
     */
    public GameBoardPanel getBoardPanel() {
        return gameBoardPanel;
    }

    /**
     * Getter affichage panneau de contrôle
     * @return
     */
    public GameControlPanel getControlPanel() {
        return gameControlPanel;
    }
}
