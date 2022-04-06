package Game.Model.GameElements.Layer1.Entities.Actions.Mouvements;

import Game.Model.GameBoard.GameBoard;
import Game.Model.GameConstants;
import Game.Model.GameElements.Layer1.Entities.Alien;
import Game.Model.GameElements.Layer1.Entities.Entity;
import Game.Model.GameElements.Layer1.Entities.SpaceMarine;

/**
 * Classe de combat entre un alien et un SpaceMarine
 */
public class Fight extends Thread{
    // Alien du combat
    Alien alien;
    // SpaceMarine du combat
    SpaceMarine spacemarine;
    // gameboard
    GameBoard gameboard;

    public Fight(SpaceMarine spacemarine, GameBoard gameBoard){
        this.spacemarine = spacemarine;
        this.gameboard = gameBoard;
        this.alien = this.choseAlien();
        this.start();
    }

    @Override
    public void run(){
        if(this.alien != null){
            while (this.alien.getHealthPoints() > 0) {
                this.alien.setHealthPoints(this.alien.getHealthPoints() - GameConstants.damages);
                try {
                    sleep(GameConstants.SpaceMarineDPS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // A la fin du combat :
            // On ramasse les cailloux
            this.spacemarine.setRocks(this.spacemarine.getRocks() + this.alien.getRocks());
            // On supprime l'alien
            this.gameboard.getHitbox().empty(this.alien.getCoordinate());
            this.gameboard.getAlienView().empty(this.alien.getCoordinate());
            this.gameboard.getEntities().remove(this.alien);
        }
    }
    /**
     * méthode renvoyant une météorite proche
     * @return
     */
    private Alien choseAlien(){
        Alien res = null;
        for(Entity entity : this.gameboard.getEntities()){
            if(entity instanceof Alien){
                // si la distance euclidienne est inferieur à 1
                if(entity.isAjdacent(spacemarine.getCoordinate())){
                    res = (Alien) entity;
                    break;
                }
            }
        }
        return res;
    }
}
