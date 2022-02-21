package Model.Mouvements;

import Model.GameBoard;
import Model.Layer1.Entities.Entity;
import Model.Mouvements.Algos.Node;
import Model.Mouvements.Algos.ShortestPath;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

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
        while (!entity.getCoordinate().equals(destination)){
            ArrayList<Point> track = shortestPath.AStar(new Node(entity.getCoordinate()), new Node(destination));
            for(int i = 0; i < track.size(); i++){
                if(this.gameBoard.getHitbox().isEmpty(track.get(i).x, track.get(i).y)){
                    gameBoard.getHitbox().empty(entity.getCoordinate());
                    entity.move(shortestPath.nextMove(track, i));
                    gameBoard.getHitbox().fill(entity.getCoordinate());
                    try {
                        sleep(25);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    break;
                }
            }
        }
    }
}
