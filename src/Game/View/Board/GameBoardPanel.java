package Game.View.Board;

import Game.Model.Scoring.Score;
import Game.Model.Scoring.Timer;
import Game.Model.GameConstants;
import Game.Model.GameEngine;
import Game.Model.GameElements.Layer0.Mountain;
import Game.Model.GameElements.Layer1.Entities.Entity;
import Game.Model.GameElements.Layer1.Structures.Structure;
import Game.View.RessourceManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static Game.View.ViewConstants.*;

/**
 * Classe représentant l'affichage du terrain de jeu
 */
public class GameBoardPanel extends JPanel {

    // Moteur du jeu
    private GameEngine gameEngine;

    //Le score
    private Score score;
    private Timer timer;

    // Controleurs du zoom
    int evol = 0;
    final int evolMax = 3;
    // Dimension du zoom
    int dimension = BOARD_PANEL_WIDTH;
    // Decalages
    int decalageX = 0;
    int decalageY = 0;
    int milieuX = dimension/2;
    int milieuY = dimension/2;

    /**
     * Constructeur
     * @param gameEngine moteur du jeu, utilisé pour dessiner les éléments du jeu dans la fenêtre
     */
    public GameBoardPanel(GameEngine gameEngine) {
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
        g.setFont(RessourceManager.FontManager.Dune2000_15);
        g.setColor(Color.WHITE);
        String score= playerPoints+" / "+maxPoints;
        int scoreWidth= g.getFontMetrics(RessourceManager.FontManager.Dune2000_15).stringWidth(score);
        g.drawString(score,40,15);
        g.drawImage(RessourceManager.ImageManager.rock, 40+scoreWidth+4,0,this);
    }

    public void drawTimer(Graphics2D g){
        long elapsedTime = timer.getTime();
        long elapsedMs = elapsedTime%1000;
        long elapsedSeconds = elapsedTime / 1000;
        long secondsDisplay = elapsedSeconds % 60;
        long elapsedMinutes = elapsedSeconds / 60;

        g.setFont(RessourceManager.FontManager.Dune2000_15);
        g.setColor(Color.WHITE);
        g.drawString(elapsedMinutes+":"+secondsDisplay+":"+elapsedMs,500,15);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        Graphics2D gScore = (Graphics2D)g.create();

        g2.scale(Math.pow(2, evol), Math.pow(2, evol));

        g2.translate(-(dimension - dimension/Math.pow(2, evol))/2, -(dimension - dimension/Math.pow(2, evol))/2);
        g2.translate(decalageX, decalageY);

        //Layer 0
        g.drawImage(RessourceManager.ImageManager.getTileImage("Sand"), 0, 0,null);

        //TODO : dessiner si afficher

        // Mountain layer
        for (Mountain mountain : gameEngine.getGameBoard().getMountains()){
                mountain.getView().draw(g2);
        }

        // Structure layer
        for (Structure structure : (ArrayList<Structure>)gameEngine.getGameBoard().getStructures().clone()){
                structure.getView().draw(g2);
        }
        // Entity layer
        for (Entity entity : (ArrayList<Entity>)gameEngine.getGameBoard().getEntities().clone()){
                entity.getView().draw(g2);
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
        double x = (((double )mouseX)/dimension)*dimension/Math.pow(2, evol) + (dimension - dimension/Math.pow(2, evol))/2 - decalageX;
        double y = (((double )mouseY)/dimension)*dimension/Math.pow(2, evol) + (dimension - dimension/Math.pow(2, evol))/2 - decalageY;
        System.out.println("("+x+", "+y+")");
        System.out.println("decalage : " + decalageX + " " + decalageY);
        return new Point((int) ((x/dimension)*GameConstants.BOARD_SIZE),(int) ((y/dimension)*GameConstants.BOARD_SIZE));
    }

    /* --------------------- Déplacement/Zoom de l'affichage ----------------------- */

    // todo : faire zoomOut quand la fenêtre sort un peu (avec modification de decalageX et decalageY)
    public void zoomOut(int  mouseX, int mouseY) {
        if(evol > 0) {
            int newXGauche = (int) (milieuX - decalageX - dimension/Math.pow(2, evol));
            int newYGauche = (int) (milieuY - decalageY - dimension/Math.pow(2, evol));
            int newXDroite = (int) (milieuX - decalageX + dimension/Math.pow(2, evol));
            int newYDroite = (int) (milieuY - decalageY + dimension/Math.pow(2, evol));
            // On regarde si le dezoom sort par la gauche (x < 0)
            if(newXGauche < 0){
                System.out.println("yo1");
                // On regarde si le dezoom sort par le haut (y < 0)
                if(newYGauche < 0){
                    decalageY += newYGauche;
                }
                // On regarde si le dezoom sort par le bas (y >= dimension)
                else if(newYDroite >= dimension){
                    decalageY -= dimension - newYDroite;
                }
                decalageX += newXGauche;
            }
            // On regarde si le dezoom sort par la droite (x >= dimension)
            else if(newXDroite > dimension){
                // On regarde si le dezoom sort par le haut (y < 0)
                if(newYGauche < 0){
                    decalageY += newYGauche;
                }
                // On regarde si le dezoom sort par le bas (y >= dimension)
                else if(newYDroite > dimension){
                    decalageY -= dimension - newYDroite;
                }
                decalageX -= dimension - newXDroite;
            }
            // On regarde si le dezoom sort par le haut (y < 0)
            else if(newYGauche < 0){
                decalageY += newYGauche;
            }
            // On regarde si le dezoom sort par le bas (y >= dimension)
            else if(newYDroite > dimension){
                decalageY -= dimension - newYDroite;
            }
            evol--;
        }
    }

    public void zoomIn(int  mouseX, int mouseY){
         if(evol < evolMax)
            evol++;
    }

    public void moveViewportX(int x){
        double newX = x/Math.pow(1.5, evol);
        if(milieuX + decalageX + dimension/Math.pow(2, evol + 1) + newX < dimension && milieuX + decalageX - dimension/Math.pow(2, evol + 1) + newX >= 0){
            decalageX+=newX;
        }
    }
    public void moveViewportY(int y){
        double newY = y/Math.pow(1.5, evol);
        if(milieuY + decalageY + dimension/Math.pow(2, evol + 1) + newY < dimension && milieuY + decalageY - dimension/Math.pow(2, evol + 1) + newY >= 0){
            decalageY+=newY;
        }
    }
}
