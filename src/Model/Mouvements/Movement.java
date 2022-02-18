package Model.Mouvements;

import Model.GameBoard;
import Model.Layer1.Entities.Entity;
import Model.Mouvements.Algos.ShortestPath;

import java.awt.*;

import static java.lang.Thread.sleep;

public class Movement extends Thread{

    Entity entity;
    Point destination;
    ShortestPath shortestPath;
    GameBoard gameBoard;

    public Movement(Entity entity, Point destination, GameBoard gameBoard) {
        this.entity = entity;
        this.destination = destination;
        this.gameBoard = gameBoard;
        this.shortestPath = new ShortestPath(gameBoard.getHitbox());
        this.start();
    }

    @Override
    public void run() {
        while (entity.getCoordinate() != destination){
            gameBoard.getHitbox().empty(entity.getCoordinate());
            entity.move(shortestPath.nextMove(entity.getCoordinate(), destination));
            System.out.println(shortestPath.nextMove(entity.getCoordinate(), destination));
            System.out.println(entity.getCoordinate());
            gameBoard.getHitbox().fill(entity.getCoordinate());

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
