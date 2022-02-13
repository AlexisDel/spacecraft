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
     * @param healthPoints points de vie de l'alien
     * @param speed        vitesse en ms de déplacement de l'alien
     */
    public Alien(Point coordinate, int healthPoints, int speed) {
        super("Alien",coordinate, new Dimension(1,1), healthPoints, speed,"./resources/alien.jpeg");
        setView(new AlienView(this));
        super.addAction(Action.MOVE);
        super.addAction(Action.ATTACK);
    }
}
