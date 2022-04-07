package Game.Model.GameElements.Layer1.Entities;

import Game.Model.GameElements.Layer1.InteractiveGameElement;
import Game.Model.GameElements.Layer1.Entities.Actions.Mouvements.Direction;

import java.awt.*;

import static java.lang.Thread.sleep;

/**
 * Cette classe regroupe les entités du jeu,
 * à savoir les objets qui peuvent être déplacé
 */
public abstract class Entity extends InteractiveGameElement {
    /**Attributes*/
    private int AttackDamage;
    private Direction direction;
    private boolean isMoving;
    private int rocks;
    /**
     * Constructeur
     * @param coordinate   coordonnées globales de l'entité
     * @param dimension    width et size de l'entité
     * @param healthPoints points de vie de l'entité
     */

    public Entity(String name,Point coordinate, Dimension dimension, int healthPoints) {
        super(name, coordinate, dimension, healthPoints);
        this.AttackDamage=50;
        this.rocks=0;
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

    public int getRocks() {
        return rocks;
    }

    public void setRocks(int rocks) {
        this.rocks = rocks;
    }

    public int getAttackDamage() {
        return AttackDamage;
    }

    public boolean isAjdacent(Point p){
        for(int i = 0 ; i < this.getDimension().width; i++){
            if(new Point( this.getCoordinate().x - 1, i + this.getCoordinate().y).equals(p)){
                return true;
            }
            if(new Point( this.getCoordinate().x + this.getDimension().height, i + this.getCoordinate().y).equals(p)){
                return true;
            }
        }
        for(int i = 0 ; i < this.getDimension().height; i++){
            if(new Point(i + this.getCoordinate().x, this.getCoordinate().y - 1).equals(p)){
                return true;
            }
            if(new Point(i + this.getCoordinate().x, this.getCoordinate().y + this.getDimension().width).equals(p)){
                return true;
            }
        }
        return false;
    }
}
