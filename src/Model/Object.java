package Model;

import View.Tiles.ObjectView;

import java.awt.*;

/**
* Classe décrivant les objets de Layer1 et Layer2: objets avec des healthPoints, des coordonnées
 * et des dimensions
 */
public abstract class Object {

    ObjectView view;

    private Point coordinate;
    private Dimension dimension;

    /**
     * Constructeur
     * @param coordinate coordonnées globales de l'objet
     * @param dimension width et size du objet
     */
    public Object(Point coordinate, Dimension dimension){
        this.coordinate=coordinate;
        this.dimension=dimension;
    }

    protected void setView(ObjectView view){
        this.view = view;
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

    public ObjectView getView() {
        return view;
    }
}

