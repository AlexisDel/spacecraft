package Model.Layer1.Entities.Actions;

import Model.GameBoard;
import Model.Layer1.Entities.Entity;
import Model.Layer1.Structures.Meteorite;
import Model.Layer1.Structures.Structure;

import java.awt.*;

public class Mine extends Thread{
    private Entity miner;
    private GameBoard gameboard;

    public Mine(Entity miner, GameBoard gameBoard){
        this.miner= miner;
        this.gameboard=gameBoard;
        this.start();
    }

    @Override
    public void run(){
        // Météorite à détruire
        Meteorite meteorite = this.choseMeteorite();
        // Tant que la météorite à des HP
        while(meteorite != null && meteorite.getHealthPoints() > 0 && false){
            System.out.println("MINNNINNNNNNGGGGGG BITCHHH");
            // On mine
            meteorite.mined(50);
            // On gagne des cailloux
            this.miner.setRocks(this.miner.getRocks()+1);
            // On fait une pause
            try {
                //mine once then stop for cooldown
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // La météorite n'as plus de vie, on la supprime
        //this.gameboard.getStructures().remove(meteorite);
        //todo : supprimer des HitBoxs
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
                    System.out.println("YO DA");

                    break;
                }
            }
        }
        return res;
    }
}
