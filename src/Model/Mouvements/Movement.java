package Model.Mouvements;

import Model.GameBoard;
import Model.GameConstants;
import Model.Layer1.Entities.Alien;
import Model.Layer1.Entities.Entity;
import Model.Layer1.Entities.SpaceMarine;
import Model.Mouvements.Algos.Node;
import Model.Mouvements.Algos.ShortestPath;

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

    @Override    public void run() {
        this.entity.setIsMoving(true);
        // On test si le mouvement est possible
        if(this.gameBoard.getHitbox().isEmpty(destination.x, destination.y)) {
            // Tant qu'on est pas arrivé
            while (!entity.getCoordinate().equals(destination)) {
                ArrayList<Point> track;
                // On calcul le chemin en fonction de qui on est
                if(this.entity instanceof Alien) {
                    track = shortestPath.AStar(new Node(entity.getCoordinate()), new Node(destination), this.gameBoard.getAlienView());
                }
                else{
                    track = shortestPath.AStar(new Node(entity.getCoordinate()), new Node(destination), this.gameBoard.getHitbox());
                }
                // Pour chaque étapes du chemin trouvé
                for (int i = 0; i < track.size(); i++) {
                    // Si c'est un alien qui bouge
                    if(this.entity instanceof Alien) {
                        // Si la prochaine étape est libre :
                        if ((i < track.size() - 1 && this.gameBoard.getAlienView().isEmpty(track.get(i + 1).x, track.get(i + 1).y)) || i == track.size() - 1){
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
                                sleep(150 + (new Random()).nextInt(150));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }
                    // Si on est pas un alien :
                    else{
                        // Si la prochaine case est libre
                        if ((i < track.size() - 1 && this.gameBoard.getHitbox().isEmpty(track.get(i + 1).x, track.get(i + 1).y)) || i == track.size() - 1) {
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
        this.entity.setIsMoving(false);
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
            switch (dir){
                case SOUTH -> {
                    // On va vers le nord, on cherche la ligne de la hitbox du spaceMarines la plus haute.
                    int x = Math.max(0, entity.getCoordinate().x - GameConstants.fearOfSpaceMarines/2);
                    // Pour tous les points dans cette ligne et dans la hitbox du spaceMarine :
                    for(int y = -GameConstants.fearOfSpaceMarines/2; y < GameConstants.fearOfSpaceMarines/2 + 1; y++){
                        // Si ce point est dans la grille et normalement vide :
                        newCoord = entity.getCoordinate().y + y;
                        if(this.gameBoard.isInBoard(x, newCoord) && this.gameBoard.getHitbox().isEmpty(x, newCoord)){
                            // On vide ce point
                            this.gameBoard.getAlienView().empty(new Point(x, newCoord));
                        }
                    }
                }
                case NORTH -> {
                    // On va vers le sud, on cherche la ligne de la hitbox du spaceMarines la plus haute.
                    int x = Math.min(GameConstants.BOARD_SIZE - 1, entity.getCoordinate().x + GameConstants.fearOfSpaceMarines/2);
                    // Pour tous les points dans cette ligne et dans la hitbox du spaceMarine :
                    for(int y = -GameConstants.fearOfSpaceMarines/2; y < GameConstants.fearOfSpaceMarines/2 + 1; y++){
                        // Si ce point est dans la grille et normalement vide :
                        newCoord = entity.getCoordinate().y + y;
                        if(this.gameBoard.isInBoard(x, newCoord) && this.gameBoard.getHitbox().isEmpty(x, newCoord)){
                            // On vide ce point
                            this.gameBoard.getAlienView().empty(new Point(x, newCoord));
                        }
                    }
                }
                case WEST -> {
                    // On va vers l'est, on cherche la ligne de la hitbox du spaceMarines la plus haute.
                    int y = Math.min(GameConstants.BOARD_SIZE - 1, entity.getCoordinate().y + GameConstants.fearOfSpaceMarines/2);
                    // Pour tous les points dans cette ligne et dans la hitbox du spaceMarine :
                    for(int x = -GameConstants.fearOfSpaceMarines/2; x < GameConstants.fearOfSpaceMarines/2 + 1; x++){
                        // Si ce point est dans la grille et normalement vide :
                        newCoord = entity.getCoordinate().x + x;
                        if(this.gameBoard.isInBoard(newCoord, y) && this.gameBoard.getHitbox().isEmpty(newCoord, y)){
                            // On vide ce point
                            this.gameBoard.getAlienView().empty(new Point(newCoord, y));
                        }
                    }
                }
                case EAST -> {
                    // On va vers l'ouest, on cherche la ligne de la hitbox du spaceMarines la plus haute.
                    int y = Math.max(0, entity.getCoordinate().y - GameConstants.fearOfSpaceMarines/2);
                    // Pour tous les points dans cette ligne et dans la hitbox du spaceMarine :
                    for(int x = -GameConstants.fearOfSpaceMarines/2; x < GameConstants.fearOfSpaceMarines/2 + 1; x++){
                        // Si ce point est dans la grille et normalement vide :
                        newCoord = entity.getCoordinate().x + x;
                            if(this.gameBoard.isInBoard(newCoord, y) && this.gameBoard.getHitbox().isEmpty(newCoord, y)){
                            // On vide ce point
                            this.gameBoard.getAlienView().empty(new Point(newCoord, y));
                        }
                    }
                }
            }
            // On effectue le mouvement
            entity.move(dir);
            // On remplie le nouvel endroit où on se trouve
            switch (dir){
                case NORTH -> {
                    // On va vers le nord, on cherche la ligne de la hitbox du spaceMarines la plus haute.
                    int x = Math.max(0, entity.getCoordinate().x - GameConstants.fearOfSpaceMarines/2);
                    // Pour tous les points dans cette ligne et dans la hitbox du spaceMarine :
                    for(int y = -GameConstants.fearOfSpaceMarines/2; y < GameConstants.fearOfSpaceMarines/2 + 1; y++){
                        // Si ce point est dans la grille et normalement vide :
                        newCoord = entity.getCoordinate().y + y;
                        if(this.gameBoard.isInBoard(x, newCoord) && this.gameBoard.getHitbox().isEmpty(x, newCoord)){
                            // On vide ce point
                            this.gameBoard.getAlienView().fill(new Point(x, newCoord));
                        }
                    }
                }
                case SOUTH -> {
                    // On va vers le sud, on cherche la ligne de la hitbox du spaceMarines la plus haute.
                    int x = Math.min(GameConstants.BOARD_SIZE - 1, entity.getCoordinate().x + GameConstants.fearOfSpaceMarines/2);
                    // Pour tous les points dans cette ligne et dans la hitbox du spaceMarine :
                    for(int y = -GameConstants.fearOfSpaceMarines/2; y < GameConstants.fearOfSpaceMarines/2 + 1; y++){
                        // Si ce point est dans la grille et normalement vide :
                        newCoord = entity.getCoordinate().y + y;
                        if(this.gameBoard.isInBoard(x, newCoord) && this.gameBoard.getHitbox().isEmpty(x, newCoord)){
                            // On vide ce point
                            this.gameBoard.getAlienView().fill(new Point(x, newCoord));
                        }
                    }
                }
                case EAST -> {
                    // On va vers l'est, on cherche la ligne de la hitbox du spaceMarines la plus haute.
                    int y = Math.min(GameConstants.BOARD_SIZE - 1, entity.getCoordinate().y + GameConstants.fearOfSpaceMarines/2);
                    // Pour tous les points dans cette ligne et dans la hitbox du spaceMarine :
                    for(int x = -GameConstants.fearOfSpaceMarines/2; x < GameConstants.fearOfSpaceMarines/2 + 1; x++){
                        // Si ce point est dans la grille et normalement vide :
                        newCoord = entity.getCoordinate().x + x;
                        if(this.gameBoard.isInBoard(newCoord, y) && this.gameBoard.getHitbox().isEmpty(newCoord, y)){
                            // On vide ce point
                            this.gameBoard.getAlienView().fill(new Point(newCoord, y));
                        }
                    }
                }
                case WEST -> {
                    // On va vers l'ouest, on cherche la ligne de la hitbox du spaceMarines la plus haute.
                    int y = Math.max(0, entity.getCoordinate().y - GameConstants.fearOfSpaceMarines/2);
                    // Pour tous les points dans cette ligne et dans la hitbox du spaceMarine :
                    for(int x = -GameConstants.fearOfSpaceMarines/2; x < GameConstants.fearOfSpaceMarines/2 + 1; x++){
                        // Si ce point est dans la grille et normalement vide :
                        newCoord = entity.getCoordinate().x + x;
                        if(this.gameBoard.isInBoard(newCoord, y) && this.gameBoard.getHitbox().isEmpty(newCoord, y)){
                            // On vide ce point
                            this.gameBoard.getAlienView().fill(new Point(newCoord, y));
                        }
                    }
                }
            }
            gameBoard.getHitbox().fill(entity.getCoordinate());
        }
        // Sinon :
        else{
            gameBoard.getHitbox().empty(entity.getCoordinate());
            gameBoard.getAlienView().empty(entity.getCoordinate());
            entity.move(dir);
            gameBoard.getHitbox().fill(entity.getCoordinate());
            gameBoard.getAlienView().fill(entity.getCoordinate());
        }
    }
}
