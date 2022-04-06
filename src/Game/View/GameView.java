package Game.View;

import Game.Controller.BoardController;
import Game.Model.GameConstants;
import Game.Model.GameEngine;
import Game.View.Board.GameBoardPanel;
import Game.View.ControlPanel.GameControlPanel;
import GameOver.LeaderBoard;
import GameOver.LeaderboardWindow;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class GameView extends JFrame implements Runnable{

    private Thread displayUpdateThread;
    private GameBoardPanel gameBoardPanel;
    private GameControlPanel gameControlPanel;

    private GameEngine gameEngine;

    public GameView(GameEngine gameEngine) {

        this.gameEngine = gameEngine;

        initWindow();
        /** adds the board panel to the window*/
        gameBoardPanel = new GameBoardPanel(gameEngine);
        this.add(gameBoardPanel, BorderLayout.WEST);

        /** adds the control panel to the window*/
        gameControlPanel = new GameControlPanel(gameEngine);
        this.add(gameControlPanel, BorderLayout.EAST);

        displayUpdateThread = new Thread(this);
        displayUpdateThread.start();

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initWindow() {
        this.setTitle("Spacecraft");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }

    @Override
    public void run() {
        while(!gameEngine.getGameBoard().isGameOver()){
            gameBoardPanel.repaint();
            gameControlPanel.repaint();
            try {
                sleep(1000/ViewConstants.FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.dispose();
        try {

            new LeaderboardWindow(new LeaderBoard(GameConstants.PLAYER_NAME, (int)gameEngine.getGameBoard().getTimer().getTime()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBoardController(BoardController boardController) {
        getBoardPanel().addMouseListener(boardController);
        getBoardPanel().addMouseMotionListener(boardController);
        getBoardPanel().addMouseWheelListener(boardController);
    }

    public GameBoardPanel getBoardPanel() {
        return gameBoardPanel;
    }

    public GameControlPanel getControlPanel() {
        return gameControlPanel;
    }
}
