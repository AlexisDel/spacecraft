package Model.Layer1.Structures;

import View.ItemsViews.MeteoriteView;

import java.awt.*;

public class Meteorite extends Structure {
    private int rocks;

    /**
     * Constructeur
     *
     * @param coordinate   coordonnées globales de la structure
     * @param dimension    width et size de la structure
     * @param healthPoints points de vie de la structure
     * @param capacity     nombre max d'occupants
     */
    public Meteorite(Point coordinate, Dimension dimension, int healthPoints, int capacity) {
        super("Meteorite", coordinate, dimension, healthPoints, capacity);
        this.rocks= dimension.height*dimension.width*10;
        setView(new MeteoriteView(this));

    }
}