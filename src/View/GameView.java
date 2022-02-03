package View;

import javax.swing.*;
import java.awt.*;

public class GameView implements Runnable{

    private JFrame window;
    private Thread displayUpdateThread;
    private BoardPanel boardPanel;

    public GameView() {
        window = new JFrame();
        initWindow();

        BoardPanel boardPanel = new BoardPanel();
        window.add(boardPanel, BorderLayout.WEST);

        startGameViewThread();
        window.setVisible(true);
    }

    private void initWindow() {
        window.setTitle("Spacecraft");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(ViewConstants.WINDOW_WIDTH, ViewConstants.WINDOW_HEIGHT);
        window.setResizable(false);

    }

    private void startGameViewThread() {
        displayUpdateThread = new Thread(this);
        displayUpdateThread.start();
    }

    @Override
    public void run() {
        System.out.println("game is running");
    }
}
