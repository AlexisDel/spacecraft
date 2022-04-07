package Game.Model.Scoring;

import Game.Model.GameBoard.GameBoard;
import Game.Model.GameConstants;
import Game.Model.GameElements.Layer1.Entities.Entity;
import Game.Model.GameElements.Layer1.Entities.SpaceMarine;
import Game.Model.GameElements.Layer1.Structures.Meteorite;
import Game.Model.GameElements.Layer1.Structures.Structure;

/**
 * Cette classe contient le code pour gerer le score (nombre de roches) du joueur
 */
public class Score {
    /** Attributs */
    private GameBoard gameBoard;
    //Les roches des Space Marines / du joueur
    private int rocksSM;
    private int initialRocks;

    /**
     * Constructeur
     * @param gameBoard le tableau de jeu
     */
    public Score(GameBoard gameBoard){
        this.gameBoard= gameBoard;
        initRocks();
    }

    /**
     * Cette méthode compte le nombre initial de roches dans toute la map
     */
    public void initRocks(){
        for(Structure structure: gameBoard.getStructures()){
            if(structure instanceof Meteorite){
                this.initialRocks+=  structure.getHealthPoints()/GameConstants.damages;
            }
        }
    }

    /**
     * Cette méthode mets à jour le nombre de roches du joueur
     */
    public void update(){
        int counter=0;
        //On somme le nombre de roches de tous les Space Marines (entités controlees par le joueur)
        for(Entity entity: gameBoard.getEntities()){
            if(entity instanceof SpaceMarine){
                counter+=  entity.getRocks();
            }
        }
        this.rocksSM=counter;
    }

    /**
     * Getter de l'attribut rocksSM
     * @return
     */
    public int getPlayersRocks() {
        //Avant de donner les roches du joueur on vérifie que ceci est à jour
        this.update();
        return rocksSM;
    }

    /**
     * Getter de l'attribut initialRocks
     * @return
     */
    public int getInitialRocks() {
        return initialRocks;
    }
}
