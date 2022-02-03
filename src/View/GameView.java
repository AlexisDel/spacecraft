package View;

import Model.GameEngine;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class GameView implements Runnable{

    private JFrame window;
    private Thread displayUpdateThread;
    private BoardPanel boardPanel;

    public GameView(GameEngine gameEngine) {

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
        // TODO use isGameRunning instead
        while(true){
            boardPanel.repaint();

            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
