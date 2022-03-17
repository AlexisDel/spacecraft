package View.Board;

import Model.GameEngine;
import Model.Layer0.Mountain;
import Model.Layer1.Entities.Entity;
import Model.Layer1.Structures.Structure;

import javax.swing.*;
import java.awt.*;

import static View.ViewConstants.*;

/**
 * Classe représentant l'affichage du terrain de jeu
 */
public class BoardPanel extends JPanel {

    private int zoomFactor = 1;
    private int zoomInterval = 1;

    // Position de la fenêtre d'affichage
    private int displayX = 0;
    private int displayY = 0;

    int viewPortSize = BOARD_PANEL_WIDTH;

    private int cameraX = 0;
    private int cameraY = 0;
    private int zoomX = 0;
    private int zoomY = 0;

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

        g2.translate(zoomX, zoomY);
        g2.scale(zoomFactor, zoomFactor);
        g2.translate(-zoomX, -zoomY);

        g2.translate(-displayX, -displayY);

        //TODO : dessiner si afficher

        // Sand layer
        for (Mountain mountain : gameEngine.getGameBoard().getMountains()){
                mountain.getView().getTileView().draw(g2);
        }

        // Structure layer
        for (Structure structure : gameEngine.getGameBoard().getStructures()){
                structure.getView().getTileView().draw(g2);
        }
        // Entity layer
        for (Entity entity : gameEngine.getGameBoard().getEntities()){
                entity.getView().getTileView().draw(g2);
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

        int x = ((mouseX / zoomFactor) + cameraX) / TILE_SIZE;
        int y = ((mouseY / zoomFactor) + cameraY) / TILE_SIZE;

        System.out.println("("+x+", "+y+")");
        return new Point(x,y);
    }

    /* --------------------- Déplacement/Zoom de l'affichage ----------------------- */

    public void zoomOut(int  mouseX, int mouseY) {
        if (zoomFactor > 1) {
            zoomFactor-=zoomInterval;
            zoomX = mouseX;
            zoomY = mouseY;
            int viewPortSizeX = BOARD_PANEL_WIDTH / zoomFactor;
            int viewPortSizeY = BOARD_PANEL_HEIGHT / zoomFactor;
            cameraX = (BOARD_PANEL_WIDTH - viewPortSizeX) * mouseX/BOARD_PANEL_WIDTH;
            cameraY = (BOARD_PANEL_HEIGHT - viewPortSizeY) * mouseY/BOARD_PANEL_HEIGHT;
        }
    }

    public void zoomIn(int  mouseX, int mouseY){
        if (zoomFactor < 10){
            zoomFactor+=zoomInterval;
            zoomX = mouseX;
            zoomY = mouseY;
            viewPortSize = BOARD_PANEL_WIDTH / zoomFactor;
            cameraX = (BOARD_PANEL_WIDTH - viewPortSize) * mouseX/BOARD_PANEL_WIDTH;
            cameraY = (BOARD_PANEL_HEIGHT - viewPortSize) * mouseY/BOARD_PANEL_HEIGHT;
        }
    }

    public void moveViewportX(int x){
        /*
        if (cameraX-x >= 0 && cameraX+viewPortSize-x <= BOARD_PANEL_WIDTH){
            displayX-=x;
            cameraX-=x;
            System.out.println(cameraX);
        }
        */
    }
    public void moveViewportY(int y){
        /*
        if (cameraY-y >= 0 && cameraY+viewPortSize-y <= BOARD_PANEL_HEIGHT) {
            displayY -= y;
            cameraY -= y;
        }
        */
    }
}
