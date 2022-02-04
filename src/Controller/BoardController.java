package Controller;

import View.GameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoardController implements KeyListener, MouseListener {

    GameView gameView;

    public BoardController(GameView gameView) {
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

    @Override
    public void mouseClicked(MouseEvent e) {
        gameView.getBoardPanel().ClickTile(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
