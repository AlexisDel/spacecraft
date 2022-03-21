package Model.Layer1.Entities.Actions.Mouvements;

import Model.GameBoard;
import Model.GameConstants;
import Model.Layer1.Entities.Alien;
import Model.Layer1.Entities.Entity;
import Model.Layer1.Entities.SpaceMarine;
import Model.Layer1.Entities.Actions.Mouvements.Algos.Node;
import Model.Layer1.Entities.Actions.Mouvements.Algos.ShortestPath;
import Model.Layer1.Structures.Meteorite;
import Model.Layer1.Structures.Structure;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Movement extends Thread{

    Entity entity;
    Point destination;
    ShortestPath shortestPath;
    GameBoard gameBoard;
    Meteorite meteorite;
    Boolean gotMeteorite = false;
    Boolean lookingForMeteorite;

    public Movement(Entity entity, Point destination, GameBoard gameBoard, Boolean lookingForMeteorite) {
        this.entity = entity;
        this.destination = destination;
        this.gameBoard = gameBoard;
        this.shortestPath = new ShortestPath(gameBoard.getHitbox());
        this.lookingForMeteorite = lookingForMeteorite;
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
                track = shortestPath.AStar(new Node(entity.getCoordinate()), new Node(destination), this.gameBoard.getHitbox(), false);
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
                                sleep(GameConstants.AlienSpeed);
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
                        // Si on cherche une météorite, alors si on se trouve proche d'une météorire, on s'y rend
                        if(this.lookingForMeteorite){
                            Meteorite tmp = this.findMeteorite();
                            if (tmp != null) {
                                for (int k = 0; k < tmp.getDimension().width; k++) {
                                    if (this.gameBoard.getHitbox().isEmpty(tmp.getCoordinate().x - 1, k + tmp.getCoordinate().y)) {
                                        this.destination = new Point(tmp.getCoordinate().x - 1, k + tmp.getCoordinate().y);
                                        this.gotMeteorite = true;
                                        this.meteorite = tmp;
                                        this.lookingForMeteorite = false;
                                        break;
                                    }
                                    if (this.gameBoard.getHitbox().isEmpty(tmp.getCoordinate().x + tmp.getDimension().height, k + tmp.getCoordinate().y)) {
                                        this.destination = new Point(tmp.getCoordinate().x + tmp.getDimension().height, k + tmp.getCoordinate().y);
                                        this.gotMeteorite = true;
                                        this.meteorite = tmp;
                                        this.lookingForMeteorite = false;
                                        break;
                                    }
                                }
                                for (int k = 0; k < tmp.getDimension().height; k++) {
                                    if (this.gameBoard.getHitbox().isEmpty(k + tmp.getCoordinate().x, tmp.getCoordinate().y - 1)) {
                                        this.destination = new Point(k + tmp.getCoordinate().x, tmp.getCoordinate().y - 1);
                                        this.gotMeteorite = true;
                                        this.meteorite = tmp;
                                        this.lookingForMeteorite = false;
                                        break;
                                    }
                                    if (this.gameBoard.getHitbox().isEmpty(k + tmp.getCoordinate().x, tmp.getCoordinate().y + tmp.getDimension().width)) {
                                        this.destination = new Point(k + tmp.getCoordinate().x, tmp.getCoordinate().y + tmp.getDimension().width);
                                        this.gotMeteorite = true;
                                        this.meteorite = tmp;
                                        this.lookingForMeteorite = false;
                                        break;
                                    }
                                }
                                break;
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
                                sleep(GameConstants.SpaceMarineSpeed);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
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

    /**
     * méthode renvoyant un emplacement collé à une météorite proche (de distance inférieur à alienRadar), (-1,-1) sinon
     * @return un Point
     */
    private Meteorite findMeteorite() {
        // Pour chaque structure
        for (Structure structure : (ArrayList<Structure>) this.gameBoard.getStructures().clone()) {
            // Si c'est une météorite
            if(structure instanceof Meteorite){
                // Si elle est à une distance euclienne inférieur à 2 de l'Alien
                for(int i = 0; i < structure.getDimension().height; i++){
                    for(int j = 0; j < structure.getDimension().width; j++){
                        Point res = new Point(structure.getCoordinate().x + i, structure.getCoordinate().y + j);
                        if(this.entity.getCoordinate().distance(res) <= GameConstants.alienRadar){
                            // Si elle a une case adjacente libre, on renvoie cette case
                            for(int k = 0 ; k < structure.getDimension().width; k++){
                                if(this.gameBoard.getHitbox().isEmpty(structure.getCoordinate().x - 1, k + structure.getCoordinate().y)){
                                    return (Meteorite) structure;
                                }
                                if(this.gameBoard.getHitbox().isEmpty(structure.getCoordinate().x + structure.getDimension().height,k + structure.getCoordinate().y)){
                                    return (Meteorite) structure;
                                }
                            }
                            for(int k = 0 ; k < structure.getDimension().height; k++){
                                if(this.gameBoard.getHitbox().isEmpty(k + structure.getCoordinate().x, structure.getCoordinate().y - 1)){
                                    return (Meteorite) structure;                                }
                                if(this.gameBoard.getHitbox().isEmpty(k + structure.getCoordinate().x, structure.getCoordinate().y + structure.getDimension().width)){
                                    return (Meteorite) structure;                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * getter de gotMeteorite
     * @return
     */
    public Boolean getGotMeteorite() {
        return gotMeteorite;
    }

    /**
     * getter de meteorite
     * @return
     */
    public Meteorite getMeteorite() {
        return meteorite;
    }
}
