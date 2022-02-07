package Model.Layer1;
import java.awt.*;

/** Cette classe correponde a celle du Vaiseau Spatial*/

public class SpaceCraft extends Structure{

    /**
     * Constructor
     * @param coordinate   coordonnees globales de la structure
     * @param dimension    width et size de la structure
     * @param healthpoints points de vie de la structure
     * @param capacity     nombre max d'occupants
     */
    public SpaceCraft(Point coordinate, Dimension dimension, int healthpoints, int capacity) {
        super(coordinate, dimension, healthpoints, capacity);
    }
}
