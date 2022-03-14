package Model.Layer1.Entities.Actions;

import Model.GameBoard;
import Model.Layer1.Entities.Entity;
import Model.Layer1.Structures.Meteorite;
import Model.Layer1.Structures.Structure;

public class Mine extends Thread{
    private Entity miner;
    private Meteorite meteorite;
    private GameBoard gameboard;

    public Mine(Entity miner, Meteorite meteorite, GameBoard gameBoard){
        this.miner= miner;
        this.meteorite=meteorite;
        this.gameboard=gameBoard;
        this.start();
    }

    @Override
    public void run(){
        try {
            //mine once then stop for cooldown
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
