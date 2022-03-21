package Model.GameBoardAddOns;

import Model.GameBoard;
import Model.GameConstants;
import Model.GameEngine;
import Model.Layer1.Entities.Entity;
import Model.Layer1.Entities.SpaceMarine;
import Model.Layer1.Structures.Meteorite;
import Model.Layer1.Structures.Structure;



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
                System.out.println(structure.getHealthPoints() + " " + structure.getHealthPoints()/GameConstants.HP + " " + GameConstants.HP);
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
