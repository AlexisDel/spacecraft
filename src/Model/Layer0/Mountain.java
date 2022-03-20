package Model.Layer0;

import Model.Item;
import View.Board.Tiles.ItemTile;
import View.ItemsViews.MountainView;

import java.awt.*;

import static Model.GameConstants.MOUNTAIN_SIZE;

/**
 * Classe représentant une parcelle de montagne
 */
public class Mountain extends Item {

    ItemTile view;
    // Entier représentant le type de montagne pour l'affichage
    int type;

    /**
     * Constructeur
     */
    public Mountain(Point coordinate) {
        super(coordinate, new Dimension(MOUNTAIN_SIZE,MOUNTAIN_SIZE));
        setView(new MountainView(this));
        this.type = -1;
    }

    /**
     * constructeur avec type en paramètre
     * @param coordinate ses coordonnées
     * @param type son type
     */
    public Mountain(Point coordinate, int type) {
        super(coordinate, new Dimension(MOUNTAIN_SIZE,MOUNTAIN_SIZE));
        setView(new MountainView(this));
        this.type = type;
    }

    /**
     * getter
     * @return
     */
    public int getType() {
        return type;
    }
}
