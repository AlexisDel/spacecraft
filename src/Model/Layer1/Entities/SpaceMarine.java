package Model.Layer1.Entities;

import View.ItemsViews.SpaceMarineView;

import java.awt.*;

/**
 * Cette classe décrit les personnages du jeu qui sont des SpaceMarines
 */

public class SpaceMarine extends Entity{
    /**
     * Constructeur
     *
     * @param coordinate   coordonnées globales du SpaceMarine
     * @param healthPoints points de vie du SpaceMarine
     * @param hitSpeed        vitesse de Attaque et de minage du SpaceMarine
     */
    public SpaceMarine(Point coordinate, int healthPoints, int hitSpeed) {
        super("Space Marine",coordinate, new Dimension(1,1), healthPoints, hitSpeed);
        setView(new SpaceMarineView(this));
    }
}
