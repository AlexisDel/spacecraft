package Model.Layer2;

import java.awt.*;

public class Alien extends Entity{
    /**
     * Constructor
     * @param coordinate   coordonnees globales de l'alien
     * @param dimension    width et size de l'alien
     * @param healthpoints points de vie de l'alien
     * @param speed        vitesse en ms de deplacement de l'alien
     */
    public Alien(Point coordinate, Dimension dimension, int healthpoints, int speed) {
        super(coordinate, dimension, healthpoints, speed);
    }
}
