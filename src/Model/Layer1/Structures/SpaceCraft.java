package Model.Layer1.Structures;
import java.awt.*;

/**
 * Cette classe représente le vaisseau spatial
 */
public class SpaceCraft extends Structure{

    /**
     * Constructeur
     * @param coordinate   coordonnées globales de la structure
     * @param dimension    width et size de la structure
     * @param healthPoints points de vie de la structure
     * @param capacity     nombre max d'occupants
     */
    public SpaceCraft(Point coordinate, Dimension dimension, int healthPoints, int capacity) {
        super(coordinate, dimension, healthPoints, capacity);
    }
}
