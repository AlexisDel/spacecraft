package Model.Layer0;

import Model.Object;
import View.Tiles.MountainView;
import View.Tiles.ObjectView;

import java.awt.*;

import static Model.GameConstants.MOUNTAIN_SIZE;

/**
 * Classe représentant une parcelle de montagne
 */
public class Mountain extends Object {

    ObjectView view;

    /**
     * Constructeur
     */
    public Mountain(Point coordinate) {
        super(coordinate, new Dimension(MOUNTAIN_SIZE,MOUNTAIN_SIZE));
        setView(new MountainView(this));

    }
}
