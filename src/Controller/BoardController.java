package Controller;

import View.GameView;

import java.awt.*;
import java.awt.event.*;

import static java.lang.Math.abs;

public class BoardController implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    private Point mousePt;
    int mouseDraggedThresholdX = 0;
    int mouseDraggedThresholdY = 0;

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
            case KeyEvent.VK_Z -> gameView.getBoardPanel().moveUp();
            case KeyEvent.VK_Q -> gameView.getBoardPanel().moveLeft();
            case KeyEvent.VK_S -> gameView.getBoardPanel().moveDown();
            case KeyEvent.VK_D -> gameView.getBoardPanel().moveRight();
            case KeyEvent.VK_UP -> gameView.getBoardPanel().zoomIn();
            case KeyEvent.VK_DOWN -> gameView.getBoardPanel().zoomOut();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /**Récupère les coordonnées du point cliqué et
         *  les transmet à la view qui les transmet au control panel*/
        Point clickedCoordinates= gameView.getBoardPanel().clickTile(e.getX(), e.getY());
        gameView.setSelectedCoord(clickedCoordinates);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePt = e.getPoint();
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

    @Override
    public void mouseDragged(MouseEvent e) {
        int dx = (e.getX() - mousePt.x);
        int dy = (e.getY() - mousePt.y);
        mouseDraggedThresholdX+=dx;
        mouseDraggedThresholdY+=dy;
        if (abs(mouseDraggedThresholdX) > gameView.getBoardPanel().getDisplayMovementSpeed()){
            gameView.getBoardPanel().moveViewportX(-mouseDraggedThresholdX/gameView.getBoardPanel().getDisplayMovementSpeed());
            mouseDraggedThresholdX = 0;
        }
        if (abs(mouseDraggedThresholdY) > gameView.getBoardPanel().getDisplayMovementSpeed()){
            gameView.getBoardPanel().moveViewportY(-mouseDraggedThresholdY/gameView.getBoardPanel().getDisplayMovementSpeed());
            mouseDraggedThresholdY = 0;
        }
        mousePt = e.getPoint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.isControlDown()) {
            if (e.getWheelRotation() < 0) {
                gameView.getBoardPanel().zoomIn();
            } else {
                gameView.getBoardPanel().zoomOut();
            }
        }
    }
}
