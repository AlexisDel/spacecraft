package Game.Model.Scoring;

import Game.Model.GameBoard.GameBoard;
import Game.Model.GameConstants;
import Game.Model.GameElements.Layer1.Entities.Entity;
import Game.Model.GameElements.Layer1.Entities.SpaceMarine;
import Game.Model.GameElements.Layer1.Structures.Meteorite;
import Game.Model.GameElements.Layer1.Structures.Structure;



public class Score {

    private GameBoard gameBoard;
    private int rocksSM;
    private int initialRocks;

    public Score(GameBoard gameBoard){
        this.gameBoard= gameBoard;
        initRocks();
    }

    public void initRocks(){
        for(Structure structure: gameBoard.getStructures()){
            if(structure instanceof Meteorite){
                this.initialRocks+=  structure.getHealthPoints()/GameConstants.damages;
            }
        }
    }

    public void update(){
        int counter=0;
        for(Entity entity: gameBoard.getEntities()){
            if(entity instanceof SpaceMarine){
                counter+=  entity.getRocks();
            }
        }
        this.rocksSM=counter;
    }

    public int getPlayersRocks() {
        this.update();
        return rocksSM;
    }

    public int getInitialRocks() {
        return initialRocks;
    }
}
