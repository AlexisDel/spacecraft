package View;

import Controller.GameController;
import Model.GameEngine;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class GameView implements Runnable{

    private JFrame window;
    private Thread displayUpdateThread;
    private BoardPanel boardPanel;

    private GameEngine gameEngine;

    public GameView(GameEngine gameEngine) {

        this.gameEngine = gameEngine;

        window = new JFrame();
        initWindow();

        boardPanel = new BoardPanel(gameEngine);
        window.add(boardPanel, BorderLayout.WEST);

        displayUpdateThread = new Thread(this);
        displayUpdateThread.start();

        window.pack();
        window.setVisible(true);
    }

    private void initWindow() {
        window.setTitle("Spacecraft");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

    }

    @Override
    public void run() {
        while(gameEngine.isGameRunning()){
            boardPanel.repaint();

            try {
                sleep(1000/ViewConstants.FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public void setController(GameController gameController) {
        getBoardPanel().addKeyListener(gameController);
    }
}
