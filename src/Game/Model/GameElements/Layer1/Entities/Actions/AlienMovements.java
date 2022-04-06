package Game.Model.GameElements.Layer1.Entities.Actions;

import Game.Model.GameBoard.GameBoard;
import Game.Model.GameConstants;
import Game.Model.GameElements.Layer1.Entities.Alien;
import Game.Model.GameElements.Layer1.Entities.Actions.Mouvements.HitBoard;
import Game.Model.GameElements.Layer1.Entities.Actions.Mouvements.Movement;

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
        boolean lookingForMeteorite = true;
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
            this.movement = new Movement(this.alien, new Point(nextX, nextY), gameboard, lookingForMeteorite);
            // On lance le thread du mouvement
            // On attend qu'il meurt (finisse son exécution)
            try {
                this.movement.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Si on se trouvé à côté d'une météorite, on mine puis on s'éloigne
            if(movement.getGotMeteorite()){
                // On mine une fois puis on lance un mouvement sans recherche de météorite
                // On mine
                System.out.println(movement.getMeteorite().getHealthPoints());
                movement.getMeteorite().mined(GameConstants.damages);
                // On gagne des cailloux
                this.alien.setRocks(this.alien.getRocks()+1);
                System.out.println("alien rocks : " + this.alien.getRocks());
                // On lance une fois sans chercher de météorite
                lookingForMeteorite = false;
            }
            // Sinon, alors on a pas trouvé de météorite auquel cas lookingForMeteorite = true;
            else{
                lookingForMeteorite = true;
            }
            if(movement.getGotMeteorite() && movement.getMeteorite().getHealthPoints() <= 0){
                // La météorite n'as plus de vie, on la supprime
                this.gameboard.getStructures().remove(movement.getMeteorite());
                for(int i = 0; i < movement.getMeteorite().getDimension().height; i++){
                    for(int j = 0; j < movement.getMeteorite().getDimension().width; j++){
                        this.gameboard.getHitbox().empty(new Point(movement.getMeteorite().getCoordinate().x + i,movement.getMeteorite().getCoordinate().y + j));
                        this.gameboard.getAlienView().empty(new Point(movement.getMeteorite().getCoordinate().x + i,movement.getMeteorite().getCoordinate().y + j));
                    }
                }
            }
        }
    }

}
