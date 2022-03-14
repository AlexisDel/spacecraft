package Model.Layer1.Entities;

import Model.Layer1.InteractiveItem;
import Model.Layer1.Entities.Actions.Mouvements.Direction;

import java.awt.*;

import static java.lang.Thread.sleep;

/**
 * Cette classe regroupe les entités du jeu,
 * à savoir les objets qui peuvent être déplacé
 */
public abstract class Entity extends InteractiveItem {
    /**Attributes*/
    private int speed;
    private Direction direction;
    private boolean isMoving;
    /**
     * Constructeur
     * @param coordinate   coordonnées globales de l'entité
     * @param dimension    width et size de l'entité
     * @param healthPoints points de vie de l'entité
     * @param speed vitesse en ms de déplacement de l'entité
     */

    // TODO fix le gameBoard
    public Entity(String name,Point coordinate, Dimension dimension, int healthPoints, int speed) {
        super(name, coordinate, dimension, healthPoints);
        this.speed = speed;
        this.direction = Direction.EAST;
        this.isMoving = false;
    }

    /** Cette méthode déplace l'entité */
    public void move(Direction direction){
        this.direction = direction;
        switch (direction){
            case NORTH -> super.getCoordinate().translate(-1, 0);
            case SOUTH -> super.getCoordinate().translate(1, 0);
            case EAST -> super.getCoordinate().translate(0, 1);
            case WEST -> super.getCoordinate().translate(0, -1);
        }
    }

    /**
     * setter de isMoving
     * @param value
     */
    public void setIsMoving(boolean value) throws InterruptedException {
        this.isMoving = value;
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * getter du booléen désignant si l'entité est en train de bouger ou non
     * @return
     */
    public boolean getIsMoving(){
        return this.isMoving;
    }

    public Direction getDirection() {
        return direction;
    }
}
