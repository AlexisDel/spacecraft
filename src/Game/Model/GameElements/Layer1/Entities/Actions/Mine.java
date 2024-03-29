package Game.Model.GameElements.Layer1.Entities.Actions;

import Game.Model.GameBoard.GameBoard;
import Game.Model.GameConstants;
import Game.Model.GameElements.Layer1.Entities.Entity;
import Game.Model.GameElements.Layer1.Structures.Meteorite;
import Game.Model.GameElements.Layer1.Structures.Structure;

import java.awt.*;

/**
 * Cette classe est chargé de creer une thread qui fait qu'un Space Marine ou un Alien mine une météorite
 */
public class Mine extends Thread{
    /** Attributs */
    private Entity miner;
    private GameBoard gameboard;

    /**
     * Constructeur
     * @param miner un Alien ou un SpaceMarine
     * @param gameBoard le tableau de jeu
     */
    public Mine(Entity miner, GameBoard gameBoard){
        this.miner= miner;
        this.gameboard=gameBoard;
        this.start();
    }

    /**
     * Cette méthode contient la boucle du thread qui execute l'action de minage
     */
    @Override
    public void run(){
        // Météorite à détruire
        Meteorite meteorite = this.choseMeteorite();
        // Tant que la météorite à des HP
        if(meteorite != null){
            while (meteorite.getHealthPoints() > 0) {
                // On mine et on get les cailloux obtenu à chaque coup
                meteorite.mined(GameConstants.damages);
                // On gagne des cailloux
                this.miner.setRocks(this.miner.getRocks() + 1);
                // On fait une pause
                try {
                    //mine once then stop for cooldown
                    sleep(GameConstants.SpaceMarineDPS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // La météorite n'as plus de vie, on la supprime
            this.gameboard.getStructures().remove(meteorite);
            for (int i = 0; i < meteorite.getDimension().height; i++) {
                for (int j = 0; j < meteorite.getDimension().width; j++) {
                    this.gameboard.getHitbox().empty(new Point(meteorite.getCoordinate().x + i, meteorite.getCoordinate().y + j));
                    this.gameboard.getAlienView().empty(new Point(meteorite.getCoordinate().x + i, meteorite.getCoordinate().y + j));
                }
            }
        }
    }

    /**
     * méthode renvoyant une météorite proche
     * @return
     */
    private Meteorite choseMeteorite(){
        Meteorite res = null;
        for(Structure structure : this.gameboard.getStructures()){
            if(structure instanceof Meteorite){
                // si la distance euclidienne est inferieur à 1
                if(structure.isAjdacent(miner.getCoordinate())){
                    res = (Meteorite) structure;
                    break;
                }
            }
        }
        return res;
    }
}
