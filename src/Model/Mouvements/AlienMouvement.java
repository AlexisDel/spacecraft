package Model.Mouvements;

import Model.GameBoard;
import Model.Layer1.Entities.Entity;

import java.awt.*;
import java.util.Random;

/**
 * classe définissant un mouvement
 */
public class AlienMouvement extends Thread{
    // Pointeur vers le modèle
    GameBoard gameboard;
    // Pointeur vers l'objet en mouvement
    Entity entity;

    /**
     * constructeur
     * @param gb Pointeur vers le modèle
     */
    public AlienMouvement(GameBoard gb, Entity entity){
        this.gameboard = gb;
        this.entity = entity;
    }

    /**
     * Thread déplacent naîvement les aliens (pour l'instant)
     */
    @Override
    public void run(){
        Random rand = new Random();
        // Boucle infinie
        while(true) {
            // On calcul un point proche de l'alien
            int randx = 1 - rand.nextInt(3) + this.entity.getCoordinate().x;
            int randy = 1 - rand.nextInt(3) + this.entity.getCoordinate().y;
            while (!(this.gameboard.isInBoard(randx, randy)) || !(this.gameboard.getHitbox().isEmpty(randx, randy))) {
                randx = 1 - rand.nextInt(3) + this.entity.getCoordinate().x;
                randy = 1 - rand.nextInt(3) + this.entity.getCoordinate().y;
            }
            // On bouge l'alien
            this.entity.move(new Point(randx, randy));
            // On fait une pause
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
