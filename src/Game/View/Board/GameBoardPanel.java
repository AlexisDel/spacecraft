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

    // Pointeur vers le moteur du jeu
    private GameEngine gameEngine;

    //Le score
    private Score score;
    private Timer timer;

    // Contrôle du zoom
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
        // Dessine le panel dans un buffer avant de l'afficher
        // -> amélioration perf graphique + empêche glitch
        this.setDoubleBuffered(true);
    }

    /**
     * Dessine le score en haut à gauche de l'affichage de jeu
     * @param g
     */
    public void drawScore(Graphics2D g){
        // Nombre total de roches sur la carte
        int maxPoints=this.score.getInitialRocks();
        // Nombre de roches détenues par le joueur
        int playerPoints=this.score.getPlayersRocks();

        // Affichage
        g.setFont(RessourceManager.FontManager.Dune2000_15);
        g.setColor(Color.WHITE);
        String score= playerPoints+" / "+maxPoints;

        // Décalage de l'affichage de l'image de roche en fonction de la taille occupé par l'affichage score
        int scoreWidth= g.getFontMetrics(RessourceManager.FontManager.Dune2000_15).stringWidth(score);
        g.drawString(score,40,15);
        g.drawImage(RessourceManager.ImageManager.rock, 40+scoreWidth+4,0,this);
    }

    /**
     * Dessine le timer en haut à droite de l'affichage du jeu
     * @param g
     */
    public void drawTimer(Graphics2D g){
        // Calcul du temps écoulé depuis le début de la partie
        long elapsedTime = timer.getTime();
        long elapsedMs = elapsedTime%1000;
        long elapsedSeconds = elapsedTime / 1000;
        long secondsDisplay = elapsedSeconds % 60;
        long elapsedMinutes = elapsedSeconds / 60;

        // Affichage
        g.setFont(RessourceManager.FontManager.Dune2000_15);
        g.setColor(Color.WHITE);
        g.drawString(elapsedMinutes+":"+secondsDisplay+":"+elapsedMs,500,15);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        // Copie de l'affichage avant le zoom pour le dessin du score et timer
        Graphics2D gScore = (Graphics2D)g.create();

        // Zoom de la caméra
        g2.scale(Math.pow(2, evol), Math.pow(2, evol));

        // Déplacement de la caméra
        g2.translate(-(dimension - dimension/Math.pow(2, evol))/2, -(dimension - dimension/Math.pow(2, evol))/2);
        g2.translate(decalageX, decalageY);

        // Dessin de l'arrière-plan
        g.drawImage(RessourceManager.ImageManager.getTileImage("Sand"), 0, 0,null);

        // Dessin des montagnes
        for (Mountain mountain : gameEngine.getGameBoard().getMountains()){
                mountain.getView().draw(g2);
        }

        // Dessin des structures
        for (Structure structure : (ArrayList<Structure>)gameEngine.getGameBoard().getStructures().clone()){
                structure.getView().draw(g2);
        }
        // Dessin des entités
        for (Entity entity : (ArrayList<Entity>)gameEngine.getGameBoard().getEntities().clone()){
                entity.getView().draw(g2);
        }

        // Dessin du score et du timer
        drawScore(gScore);
        drawTimer(gScore);

        // Libère les ressources utilisées par le système pour l'affichage.
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
        return new Point((int) ((x/dimension)*GameConstants.BOARD_SIZE),(int) ((y/dimension)*GameConstants.BOARD_SIZE));
    }

    /* --------------------- Déplacement/Zoom de l'affichage ----------------------- */

    /**
     * Diminue le niveau de zoom
     */
    public void zoomOut() {
        if(evol > 0) {
            int newXGauche = (int) (milieuX - decalageX - dimension/Math.pow(2, evol));
            int newYGauche = (int) (milieuY - decalageY - dimension/Math.pow(2, evol));
            int newXDroite = (int) (milieuX - decalageX + dimension/Math.pow(2, evol));
            int newYDroite = (int) (milieuY - decalageY + dimension/Math.pow(2, evol));
            // On regarde si le dezoom sort par la gauche (x < 0)
            if(newXGauche < 0){
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

    /**
     * Augmente le niveau de zoom
     */
    public void zoomIn(){
         if(evol < evolMax)
            evol++;
    }

    /**
     * Déplace horizontalement la fenêtre d'affichage
     * @param x décalage de la fenêtre en pixel en abscisse
     */
    public void moveViewportX(int x){
        double newX = x/Math.pow(1.5, evol);
        if(milieuX + decalageX + dimension/Math.pow(2, evol + 1) + newX < dimension && milieuX + decalageX - dimension/Math.pow(2, evol + 1) + newX >= 0){
            decalageX+=newX;
        }
    }

    /**
     * Déplace verticalement la fenêtre d'affichage
     * @param y décalage de la fenêtre en pixel en ordonné
     */
    public void moveViewportY(int y){
        double newY = y/Math.pow(1.5, evol);
        if(milieuY + decalageY + dimension/Math.pow(2, evol + 1) + newY < dimension && milieuY + decalageY - dimension/Math.pow(2, evol + 1) + newY >= 0){
            decalageY+=newY;
        }
    }
}
