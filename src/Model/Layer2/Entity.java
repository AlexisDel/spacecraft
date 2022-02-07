package Model.Layer2;

import Model.Object;

import java.awt.*;

public abstract class Entity extends Object {
    /**Attributes*/
    private int speed;
    /**
     * Constructor
     * @param coordinate   coordonnees globales de l'entité
     * @param dimension    width et size de l'entité
     * @param healthpoints points de vie de l'entité
     * @param speed vitesse en ms de deplacement de l'entité
     */
    public Entity(Point coordinate, Dimension dimension, int healthpoints, int speed) {
        super(coordinate, dimension, healthpoints);
        this.speed= speed;
    }
    /** Cette méthode bouge l'entité */
    public void move(){}
}
