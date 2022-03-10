package Model.Layer1.Structures;

import View.ItemsViews.MeteoriteView;

import java.awt.*;

public class Meteorite extends Structure {
    private int rocks;

    /**
     * Constructeur de la classe
     * @param coordinate   coordonnées globales de la structure
     * @param dimension    width et size de la structure
     * @param healthPoints points de vie de la structure
     */
    public Meteorite(Point coordinate, Dimension dimension, int healthPoints) {
        super("Meteorite", coordinate, dimension, healthPoints, 0);
        //Les roches sont les unités
        this.rocks= dimension.height*dimension.width*10;
        setView(new MeteoriteView(this));

    }
}