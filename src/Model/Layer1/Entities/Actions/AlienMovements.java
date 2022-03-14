package Model.Layer1.Entities.Actions;

import Model.GameBoard;
import Model.GameConstants;
import Model.Layer1.Entities.Alien;
import Model.Layer1.Entities.Actions.Mouvements.HitBoard;
import Model.Layer1.Entities.Actions.Mouvements.Movement;

import java.awt.*;
import java.util.Random;

public class AlienMovements extends Thread{
    // Attributs
    Alien alien;
    HitBoard alienView;
    GameBoard gameboard;
    Movement movement;

    /**
     * Constructeur
     * @param a
     * @param av
     * @param gb
     */
    public AlienMovements(Alien a, HitBoard av, GameBoard gb){
        this.alienView = av;
        this.alien = a;
        this.gameboard = gb;
    }
    @Override
    public void run(){
        Random rand = new Random();
        // Variables stockant les coordonnées du prochain point où l'alien souhaite aller.
        int nextX;
        int nextY;
        // On veut que ce thread tourne à l'infini
        while(true){
            // On cherche une zone libre vers laquelle aller
            nextX = rand.nextInt(GameConstants.BOARD_SIZE);
            nextY = rand.nextInt(GameConstants.BOARD_SIZE);
            while(!this.alienView.isEmpty(nextX, nextY)){
                nextX = rand.nextInt(GameConstants.BOARD_SIZE);
                nextY = rand.nextInt(GameConstants.BOARD_SIZE);
            }
            // On initialise notre objet movement pour notre alien vers notre destination
            this.movement = new Movement(this.alien, new Point(nextX, nextY), gameboard);
            // On lance le thread du mouvement
            // On attend qu'il meurt (finisse son exécution)
            try {
                this.movement.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
