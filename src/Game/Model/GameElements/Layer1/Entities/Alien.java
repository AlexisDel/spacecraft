package Game.Model.GameElements.Layer1.Entities;

import Game.View.Board.Views.AlienView;

import java.awt.*;

/**
 * Cette classe décrit les personnages du jeu qui sont des Aliens
 */
public class Alien extends Entity{
    /**
     * Constructeur
     * @param coordinate   coordonnées globales de l'alien
     * @param healthPoints points de vie de l'alien
     */
    public Alien(Point coordinate, int healthPoints) {
        super("Alien", coordinate, new Dimension(1,1), healthPoints);
        setView(new AlienView(this));
    }
}
