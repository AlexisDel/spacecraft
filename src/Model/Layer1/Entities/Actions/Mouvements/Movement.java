package Model.Layer1.Entities.Actions.Mouvements;

import Model.GameBoard;
import Model.GameConstants;
import Model.Layer1.Entities.Alien;
import Model.Layer1.Entities.Entity;
import Model.Layer1.Entities.SpaceMarine;
import Model.Layer1.Entities.Actions.Mouvements.Algos.Node;
import Model.Layer1.Entities.Actions.Mouvements.Algos.ShortestPath;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

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
        try {
            this.entity.setIsMoving(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // On test si le mouvement est possible
        if(this.gameBoard.getHitbox().isEmpty(destination.x, destination.y)) {
            // Tant qu'on est pas arrivé
            while (!entity.getCoordinate().equals(destination) && entity.getIsMoving()) {
                ArrayList<Point> track;
                // On calcul le chemin
                track = shortestPath.AStar(new Node(entity.getCoordinate()), new Node(destination), this.gameBoard.getHitbox());
                // Pour chaque étapes du chemin trouvé
                for (int i = 0; i < track.size() - 1 && entity.getIsMoving(); i++) {
                    // Si c'est un alien qui bouge
                    if(this.entity instanceof Alien) {
                        // Si la prochaine étape est libre dans la vue des Aliens:
                        if (this.gameBoard.getAlienView().isEmpty(track.get(i + 1).x, track.get(i + 1).y)){
                            // On calcul la direction à prendre
                            Direction direction = shortestPath.nextMove(track, i);
                            // On effectue le déplacement de l'entité
                            this.shiftEntity(entity, direction);
                            // On fait une petite pause
                            try {
                                sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        // Sinon, alors on fait une petite pause et on recalcule un chemin
                        else {
                            try {
                                this.entity.setIsMoving(false);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    // Si on est pas un alien :
                    else{
                        // Si la prochaine case est libre
                        if (this.gameBoard.getHitbox().isEmpty(track.get(i + 1).x, track.get(i + 1).y)) {
                            // On calcul la direction
                            Direction direction = shortestPath.nextMove(track, i);
                            // On effectue le mouvement
                            this.shiftEntity(entity, direction);
                            // On fait une petite pause
                            try {
                                sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            try {
                                sleep(50 + (new Random()).nextInt(50));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }
                }
            }
        }
        if(this.entity.getIsMoving()) {
            try {
                this.entity.setIsMoving(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * méthode privée décalant les entités dans les Hitboxs.
     * @param ent
     * @param dir
     */
    private void shiftEntity(Entity ent , Direction dir){
        int newCoord;
        // Si on déplace un spaceMarines :
        if(ent instanceof SpaceMarine){
            // On vide là où on était
            gameBoard.getHitbox().empty(entity.getCoordinate());
            if(entity instanceof  SpaceMarine){
                for (int x = -GameConstants.fearOfSpaceMarines / 2; x < GameConstants.fearOfSpaceMarines / 2 + 1; x++) {
                    for (int y = -GameConstants.fearOfSpaceMarines / 2; y < GameConstants.fearOfSpaceMarines / 2 + 1; y++) {
                        // Si ce point est dans la grille et normalement vide :
                        if (this.gameBoard.isInBoard(entity.getCoordinate().x + x,entity.getCoordinate().y + y) && this.gameBoard.getHitbox().isEmpty(entity.getCoordinate().x + x, entity.getCoordinate().y + y)) {
                            // On remplit ce point
                            this.gameBoard.getAlienView().empty(new Point(entity.getCoordinate().x + x,entity.getCoordinate().y + y));
                        }
                    }
                }
            }
            // On effectue le mouvement
            entity.move(dir);
            // On remplie le nouvel endroit où on se trouve
            if(entity instanceof  SpaceMarine){
                for (int x = -GameConstants.fearOfSpaceMarines / 2; x < GameConstants.fearOfSpaceMarines / 2 + 1; x++) {
                    for (int y = -GameConstants.fearOfSpaceMarines / 2; y < GameConstants.fearOfSpaceMarines / 2 + 1; y++) {
                        // Si ce point est dans la grille et normalement vide :
                        if (this.gameBoard.isInBoard(entity.getCoordinate().x + x,entity.getCoordinate().y + y) && this.gameBoard.getHitbox().isEmpty(entity.getCoordinate().x + x, entity.getCoordinate().y + y)) {
                            // On remplit ce point
                            this.gameBoard.getAlienView().fill(new Point(entity.getCoordinate().x + x,entity.getCoordinate().y + y));
                        }
                    }
                }
            }

            gameBoard.getHitbox().fill(entity.getCoordinate());
        }
        // Sinon : (Alors on est un Alien
        else{
            gameBoard.getHitbox().empty(entity.getCoordinate());
            gameBoard.getAlienView().empty(entity.getCoordinate());
            entity.move(dir);
            gameBoard.getHitbox().fill(entity.getCoordinate());
            gameBoard.getAlienView().fill(entity.getCoordinate());
        }
    }
}
