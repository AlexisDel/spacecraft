package View.Board;

import Model.GameBoard;
import Model.GameEngine;
import Model.Layer1.Entities.Entity;
import Model.Layer1.Entities.SpaceMarine;
import Model.Layer1.Structures.Meteorite;
import Model.Layer1.Structures.Structure;



public class Score {

    private GameBoard gameBoard;
    private int rocksSM;
    private int initialRocks;

    public Score(GameEngine gameEngine){
        this.gameBoard= gameEngine.getGameBoard();
        initRocks();
    }

    public void initRocks(){
        for(Structure structure: gameBoard.getStructures()){
            if(structure instanceof Meteorite){
                this.initialRocks+=  structure.getHealthPoints();
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
