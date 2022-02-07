package Model.Layer2;

import java.awt.*;

public class SpaceMarine extends Entity{
    /**
     * Constructor
     *
     * @param coordinate   coordonnees globales du SpaceMarine
     * @param dimension    width et size du SpaceMarine
     * @param healthpoints points de vie du SpaceMarine
     * @param speed        vitesse en ms du SpaceMarine
     */
    public SpaceMarine(Point coordinate, Dimension dimension, int healthpoints, int speed) {
        super(coordinate, dimension, healthpoints, speed);
    }
}
