package Model.Layer1.Entities;

import View.Tiles.AlienView;

import java.awt.*;

/**
 * Cette classe décrit les personnages du jeu qui sont des Aliens
 */
public class Alien extends Entity{
    /**
     * Constructeur
     * @param coordinate   coordonnées globales de l'alien
     * @param dimension    width et size de l'alien
     * @param healthPoints points de vie de l'alien
     * @param speed        vitesse en ms de déplacement de l'alien
     */
    public Alien(Point coordinate, Dimension dimension, int healthPoints, int speed) {
        super(coordinate, dimension, healthPoints, speed);
        setView(new AlienView(this));
    }
}
