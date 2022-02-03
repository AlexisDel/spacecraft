package Controller;

import View.GameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements KeyListener {

    GameView gameView;

    public GameController(GameView gameView) {
        this.gameView = gameView;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code){
            case KeyEvent.VK_UP -> gameView.getBoardPanel().moveUp();
            case KeyEvent.VK_DOWN -> gameView.getBoardPanel().moveDown();
            case KeyEvent.VK_LEFT -> gameView.getBoardPanel().moveLeft();
            case KeyEvent.VK_RIGHT -> gameView.getBoardPanel().moveRight();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
