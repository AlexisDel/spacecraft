package Game.Controller;

import Game.View.GameView;

import java.awt.*;
import java.awt.event.*;

public class BoardController implements MouseListener, MouseMotionListener, MouseWheelListener {

    private Point mousePt;

    // Pointeur vers la gameView
    GameView gameView;

    public BoardController(GameView gameView) {
        this.gameView = gameView;

    }

    @Override
    /**
     * Méthode appelée lorsque l'utilisateur clic sur l'affichage
     */
    public void mouseClicked(MouseEvent e) {
        try {
            // Récupère les coordonnées de la case sur laquelle le joueur a cliqué et les transmet au control panel
            gameView.getControlPanel().SelectItem(gameView.getBoardPanel().getTileFromClick(e.getX(), e.getY()));
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Sauvegarde la position de la souris au moment à l'utilisateur clic sur le terrain
        // Cette variable sert d'ancre pour le drag and move
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
     * Méthode appelée quand l'utilisateur fait glisser sa souris
     * ce qui correspond dans notre jeu au déplacement de la caméra
     */
    public void mouseDragged(MouseEvent e) {
        // Déplacement de la souris en x
        int dx = (e.getX() - mousePt.x);
        // Déplacement de la souris en y
        int dy = (e.getY() - mousePt.y);

        // Déclenche le déplacement de la caméra dans vue
        gameView.getBoardPanel().moveViewportX(dx);
        gameView.getBoardPanel().moveViewportY(dy);

        // Réinitialise la position de la souris (l'ancre)
        mousePt = e.getPoint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    /**
     * Méthode appelée quand l'utilisateur utilise sa mollette
     * ce qui correspond dans notre jeu au zoom de la caméra
     */
    public void mouseWheelMoved(MouseWheelEvent e) {
        // "Tirage" de la mollette
        if (e.getWheelRotation() > 0) {
            // De zoom
            gameView.getBoardPanel().zoomOut();
        }
        // "Poussée" de la mollette
        if (e.getWheelRotation() < 0) {
            // Zoom
            gameView.getBoardPanel().zoomIn();
        }
    }
}
