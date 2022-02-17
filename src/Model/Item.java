package Model;

import View.ItemsViews.ItemView;

import java.awt.*;

/**
* Classe décrivant les objets de Layer1 et Layer2: objets avec des healthPoints, des coordonnées
 * et des dimensions
 */
public abstract class Item {

    ItemView view;

    private Point coordinate;
    private Dimension dimension;

    /**
     * Constructeur
     * @param coordinate coordonnées globales de l'objet
     * @param dimension width et size du objet
     */
    public Item(Point coordinate, Dimension dimension){
        this.coordinate=coordinate;
        this.dimension=dimension;
    }

    /** setter */

    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    /** getters*/

    public Dimension getDimension() {
        return dimension;
    }

    public Point getCoordinate() {
        return coordinate;
    }

    /** Fonctions liées à la vue */
    public ItemView getView() {
        return view;
    }

    protected void setView(ItemView view){
        this.view = view;
    }
}

