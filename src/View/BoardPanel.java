package View;

import Model.GameEngine;
import Model.Layer1.Entities.Entity;
import Model.Layer1.Structures.Structure;

import javax.swing.*;
import java.awt.*;

import static Model.GameConstants.BOARD_SIZE;

/**
 * Classe représentant l'affichage du terrain de jeu
 */
public class BoardPanel extends JPanel {

    // Taille d'une parcelle du terrain de jeu en pixel
    private int tileSize = 16;
    // Nombre de cases par affiché en largeur et en hauteur
    private int maxBoardView = 40;
    // Défini le nombre de "crans" du zoom
    private int maxZoomScaleFactor = BOARD_SIZE/4;

    // Position de la fenêtre d'affichage (déplacement ZQSD)
    private int displayX = 0;
    private int displayY = 0;
    // Valeur indiquant le zoom de l'affichage
    private int zoomScaleFactor = 1;

    // Moteur du jeu
    private GameEngine gameEngine;

    /**
     * Constructeur
     * @param gameEngine moteur du jeu, utilisé pour dessiner les éléments du jeu dans la fenêtre
     */
    public BoardPanel(GameEngine gameEngine) {
        this.gameEngine = gameEngine;

        /** Paramètre de l'affichage du terrain de jeu */
        this.setPreferredSize(new Dimension(640, 640));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        // All the drawing from this component will be done in an offscreen painting buffer
        // tl;dr : improves game's rendering performance
        this.setDoubleBuffered(true);

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // Layer 0
        // Dessine seulement ce qui est affiché
        for(int i = displayX; i < maxBoardView + displayX; i++){
            for(int j = displayY; j < maxBoardView + displayY; j++){
                gameEngine.getGameBoard().getTerrain()[i][j].getView().draw(g2, (i- displayX), (j- displayY), tileSize);
            }
        }

        //TODO: optimisation ne pas dessiner si hors de l'écran (prendre en compte la taille)
        // Layer 1
        for(Structure structure : gameEngine.getGameBoard().getStructures()){
            structure.getView().draw(g2, tileSize, displayX, displayY);
        }
        // Layer 2
        for(Entity entity : gameEngine.getGameBoard().getEntities()){
            entity.getView().draw(g2, tileSize, displayX, displayY);
        }

        // Dispose of this graphics context and release any system ressources that it is using
        g2.dispose();
    }

    /**
     * Méthode appelée lorsque l'utilisateur clique sur la fenêtre
     * TODO ... (ce qu'elle fait, control panel...)
     * @param mouseX coordonnée en abscisse du curseur dans le repère de la fenêtre lors du clic
     * @param mouseY coordonnée en ordonné du curseur dans le repère de la fenêtre lors du clic
     */
    public void clickTile(int mouseX, int mouseY) {

        // Coordonnée x de la case sur laquelle le joueur a cliqué
        int x = mouseX/tileSize + displayX;
        // Coordonnée y de la case sur laquelle le joueur a cliqué
        int y = mouseY/tileSize + displayY;

        System.out.println("("+x+", "+y+")");
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

    public void zoomIn() {
        if(maxBoardView/2 >= 40){
            tileSize*=2;
            maxBoardView/=2;
            displayX = 0;
            displayY = 0;
        }
    }

    public void zoomOut(){
        if(maxBoardView*2 <= BOARD_SIZE){
            tileSize/=2;
            maxBoardView *=2;
            displayX = 0;
            displayY = 0;
        }
    }
}
