package Model.Layer1.Structures;

import View.ItemsViews.SpaceshipView;

import java.awt.*;

/**
 * Cette classe représente le vaisseau spatial
 */
public class Spaceship extends Structure{

    /**
     * Constructeur
     * @param coordinate   coordonnées globales de la structure
     * @param dimension    width et size de la structure
     * @param healthPoints points de vie de la structure
     * @param capacity     nombre max d'occupants
     */
    public Spaceship(Point coordinate, Dimension dimension, int healthPoints, int capacity) {
        super("Spaceship",coordinate, dimension, healthPoints, capacity);
        setView(new SpaceshipView(this));
    }
}
