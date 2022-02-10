package Model.Layer1.Entities;

import Model.Layer1.Object;

import java.awt.*;
/**
 * Cette classe regroupe les entités du jeu,
 * à savoir les objets qui peuvent être déplacé
 */
public abstract class Entity extends Object {
    /**Attributes*/
    private int speed;
    /**
     * Constructeur
     * @param coordinate   coordonnées globales de l'entité
     * @param dimension    width et size de l'entité
     * @param healthPoints points de vie de l'entité
     * @param speed vitesse en ms de déplacement de l'entité
     */
    public Entity(Point coordinate, Dimension dimension, int healthPoints, int speed) {
        super(coordinate, dimension, healthPoints);
        this.speed= speed;
    }

    /** Cette méthode déplace l'entité */
    public void move(){}
}
