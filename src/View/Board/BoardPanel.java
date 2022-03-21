package View.Board;

import Model.GameBoardAddOns.Score;
import Model.GameBoardAddOns.Timer;
import Model.GameEngine;
import Model.Layer0.Mountain;
import Model.Layer1.Entities.Entity;
import Model.Layer1.Structures.Structure;
import View.FontManager;
import View.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
    private int centerX = BOARD_PANEL_WIDTH/2;
    private int centerY = BOARD_PANEL_HEIGHT/2;

    // Moteur du jeu
    private GameEngine gameEngine;

    //Le score
    private Score score;
    private Timer timer;

    /**
     * Constructeur
     * @param gameEngine moteur du jeu, utilisé pour dessiner les éléments du jeu dans la fenêtre
     */
    public BoardPanel(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.score= gameEngine.getGameBoard().getScore();
        this.timer= gameEngine.getGameBoard().getTimer();

        /** Paramètre de l'affichage du terrain de jeu */
        this.setPreferredSize(new Dimension(BOARD_PANEL_WIDTH, BOARD_PANEL_HEIGHT));
        this.setFocusable(true);
        // All the drawing from this component will be done in an offscreen painting buffer
        // tl;dr : improves game's rendering performance
        this.setDoubleBuffered(true);
    }

    /**
     * Cette méthode dessine le score en haut à gauche du tableau de jeu
     * @param g
     */
    public void drawScore(Graphics2D g){
        int maxPoints=this.score.getInitialRocks();
        int playerPoints=this.score.getPlayersRocks();
        g.setFont(FontManager.Dune2000);
        g.setColor(Color.WHITE);
        String score= playerPoints+" / "+maxPoints;
        int scoreWidth= g.getFontMetrics(FontManager.Dune2000).stringWidth(score);
        g.drawString(score,40,15);
        g.drawImage(ImageManager.rock, 40+scoreWidth+4,0,this);
    }

    public void drawTimer(Graphics2D g){
        long elapsedTime = timer.getTime();
        long elapsedMs = elapsedTime%1000;
        long elapsedSeconds = elapsedTime / 1000;
        long secondsDisplay = elapsedSeconds % 60;
        long elapsedMinutes = elapsedSeconds / 60;

        g.setFont(FontManager.Dune2000);
        g.setColor(Color.WHITE);
        g.drawString(elapsedMinutes+":"+secondsDisplay+":"+elapsedMs,500,15);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        Graphics2D gScore = (Graphics2D)g.create();

        g2.scale(zoomFactor, zoomFactor);

        //g2.translate(centerX, centerY);

        //Layer 0
        g.drawImage(ImageManager.getTileImage("Sand"), 0, 0,null);

        //TODO : dessiner si afficher

        // Mountain layer
        for (Mountain mountain : gameEngine.getGameBoard().getMountains()){
                mountain.getView().getTileView().draw(g2);
        }

        // Structure layer
        for (Structure structure : (ArrayList<Structure>)gameEngine.getGameBoard().getStructures().clone()){
                structure.getView().getTileView().draw(g2);
        }
        // Entity layer
        for (Entity entity : (ArrayList<Entity>)gameEngine.getGameBoard().getEntities().clone()){
                entity.getView().getTileView().draw(g2);
        }

        drawScore(gScore);
        drawTimer(gScore);

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
        }
    }

    public void zoomIn(int  mouseX, int mouseY){
        if (zoomFactor < 10){
            zoomFactor+=zoomInterval;
        }
    }

    public void moveViewportX(int x){
        centerX+=x;
    }
    public void moveViewportY(int y){
        centerY+=y;
    }
}
