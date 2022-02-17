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
        switch (e.getKeyCode()){
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
        // Récupère les coordonnées de la case sur laquelle le joueur a cliqué et les transmet au control panel
        gameView.getControlPanel().SelectItem(gameView.getBoardPanel().getTileFromClick(e.getX(), e.getY()));
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
    /**
     * Ici on utilise un système de seuil afin de prendre en compte le déplacement de la souris
     * seulement après qu'une certaine distance soit parcouru.
     * Cette distance minimum parcourue est pondéré par le niveau de zoom, cela permet d'avoir un déplacement
     * homogène sur la carte independent du niveau de zoom
     * Autrement dit un déplacement de 5 cm avec la souris décalera la fenêtre d'affichage du même nombre de cases
     * que l'on soit au zoom maximum ou minimum.
     */
    public void mouseDragged(MouseEvent e) {
        // Déplacement de la souris en x
        int dx = (e.getX() - mousePt.x);
        // Déplacement de la souris en y
        int dy = (e.getY() - mousePt.y);
        // Accumulateur représentant la distance déjà parcourus par la souris
        mouseDraggedThresholdX+=dx;
        mouseDraggedThresholdY+=dy;

        // Si la distance parcourue est jugé assez grande, on déplace la fenêtre d'affichage
        if (abs(mouseDraggedThresholdX) > gameView.getBoardPanel().getDisplayMovementSpeed()){
            gameView.getBoardPanel().moveViewportX(-mouseDraggedThresholdX/gameView.getBoardPanel().getDisplayMovementSpeed());
            mouseDraggedThresholdX = 0;
        }
        if (abs(mouseDraggedThresholdY) > gameView.getBoardPanel().getDisplayMovementSpeed()){
            gameView.getBoardPanel().moveViewportY(-mouseDraggedThresholdY/gameView.getBoardPanel().getDisplayMovementSpeed());
            mouseDraggedThresholdY = 0;
        }
        // Réinitialise la position de la souris
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
