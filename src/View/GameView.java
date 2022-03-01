package View;

import Controller.BoardController;
import Model.GameEngine;
import View.Board.BoardPanel;
import View.ControlPanel.ControlPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class GameView extends JFrame implements Runnable{

    private Thread displayUpdateThread;
    private BoardPanel boardPanel;
    private ControlPanel controlPanel;

    private GameEngine gameEngine;

    public GameView(GameEngine gameEngine) {

        try {
            new ImageManager();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.gameEngine = gameEngine;

        initWindow();
        /** adds the board panel to the window*/
        boardPanel = new BoardPanel(gameEngine);
        this.add(boardPanel, BorderLayout.WEST);
        /** adds the control panel to the window*/
        controlPanel = new ControlPanel(gameEngine);
        this.add(controlPanel, BorderLayout.EAST);

        displayUpdateThread = new Thread(this);
        displayUpdateThread.start();

        this.pack();
        this.setVisible(true);
    }

    private void initWindow() {
        this.setTitle("Spacecraft");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }

    @Override
    public void run() {
        while(true){
            boardPanel.repaint();
            controlPanel.repaint();
            try {
                sleep(1000/ViewConstants.FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setBoardController(BoardController boardController) {
        //getBoardPanel().addKeyListener(boardController);
        getBoardPanel().addMouseListener(boardController);
        getBoardPanel().addMouseMotionListener(boardController);
        getBoardPanel().addMouseWheelListener(boardController);
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }
}
