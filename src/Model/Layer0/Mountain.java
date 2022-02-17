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

    /**
     * Constructeur
     */
    public Mountain(Point coordinate) {
        super(coordinate, new Dimension(MOUNTAIN_SIZE,MOUNTAIN_SIZE));
        setView(new MountainView(this));

    }
}
