package View.Board;

import Model.GameEngine;
import Model.Layer0.Mountain;
import Model.Layer1.Entities.Entity;
import Model.Layer1.Structures.Structure;

import javax.swing.*;
import java.awt.*;

import static Model.GameConstants.BOARD_SIZE;
import static View.ViewConstants.BOARD_PANEL_HEIGHT;
import static View.ViewConstants.BOARD_PANEL_WIDTH;

/**
 * Classe représentant l'affichage du terrain de jeu
 */
public class BoardPanel extends JPanel {

    // Taille d'une parcelle du terrain de jeu en pixel
    private int tileSize = 16;
    // Nombre de cases par affiché en largeur et en hauteur
    private int maxBoardView = 40;

    // Position de la fenêtre d'affichage (déplacement ZQSD)
    private int displayX = 0;
    private int displayY = 0;
    private int displayMovementSpeed = 5;

    // Moteur du jeu
    private GameEngine gameEngine;

    /**
     * Constructeur
     * @param gameEngine moteur du jeu, utilisé pour dessiner les éléments du jeu dans la fenêtre
     */
    public BoardPanel(GameEngine gameEngine) {
        this.gameEngine = gameEngine;

        /** Paramètre de l'affichage du terrain de jeu */
        this.setPreferredSize(new Dimension(BOARD_PANEL_WIDTH, BOARD_PANEL_HEIGHT));
        this.setFocusable(true);
        // All the drawing from this component will be done in an offscreen painting buffer
        // tl;dr : improves game's rendering performance
        this.setDoubleBuffered(true);

        // Layer 0
        this.setBackground(new Color(234, 138, 54));

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // Sand layer
        for (Mountain mountain : gameEngine.getGameBoard().getMountains()){
            if (mountain.getView().getTileView().isDisplayed(displayX, displayY, maxBoardView)) {
                mountain.getView().getTileView().draw(g2, tileSize, displayX, displayY);
            }
        }

        // Structure layer
        for (Structure structure : gameEngine.getGameBoard().getStructures()){
            if (structure.getView().getTileView().isDisplayed(displayX, displayY, maxBoardView)){
                structure.getView().getTileView().draw(g2, tileSize, displayX, displayY);
            }
        }
        // Entity layer
        for (Entity entity : gameEngine.getGameBoard().getEntities()){
            if (entity.getView().getTileView().isDisplayed(displayX, displayY, maxBoardView)){
                entity.getView().getTileView().draw(g2, tileSize, displayX, displayY);
            }
        }

        // Dispose of this graphics context and release any system ressources that it is using
        g2.dispose();
    }

    /**
     * Méthode appelée lorsque l'utilisateur clique sur le terrain
     * Retourne les coordonnées du point cliqué sur la map.
     * @param mouseX coordonnée en abscisse du curseur dans le repère de la fenêtre lors du clic
     * @param mouseY coordonnée en ordonné du curseur dans le repère de la fenêtre lors du clic
     */
    public Point getTileFromClick(int mouseX, int mouseY) {

        // Coordonnée x de la case sur laquelle le joueur a cliqué
        int x = mouseX/tileSize + displayX;
        // Coordonnée y de la case sur laquelle le joueur a cliqué
        int y = mouseY/tileSize + displayY;

        System.out.println("("+x+", "+y+")");
        return new Point(x,y);
    }

    /* --------------------- Déplacement/Zoom de l'affichage ----------------------- */

    public void moveUp(){
        if(displayY > 0){
            displayY--;
        }
    }

    public void moveDown(){
        if(displayY < BOARD_SIZE - maxBoardView){
            displayY++;
        }
    }

    public void moveLeft() {
        if (displayX > 0){
            displayX--;
        }
    }

    public void moveRight() {
        if (displayX < BOARD_SIZE - maxBoardView){
            displayX++;
        }
    }

    // TODO : ne pas reset la position de la caméra à chaque fois
    public void zoomIn() {
        if(maxBoardView/2 >= 40){
            tileSize*=2;
            maxBoardView/=2;
            displayX = 0;
            displayY = 0;
            displayMovementSpeed++;
        }
    }

    public void zoomOut(){
        if(maxBoardView*2 <= BOARD_SIZE){
            tileSize/=2;
            maxBoardView *=2;
            displayX = 0;
            displayY = 0;
            displayMovementSpeed--;
        }
    }

    public void moveViewportX(int x){
        if (displayX+x >= 0 && displayX+x <= BOARD_SIZE - maxBoardView){
            displayX+=x;
        }
    }
    public void moveViewportY(int y){
        if (displayY+y >= 0 && displayY+y <= BOARD_SIZE - maxBoardView){
            displayY+=y;
        }
    }

    public int getDisplayMovementSpeed() {
        return displayMovementSpeed;
    }
}
