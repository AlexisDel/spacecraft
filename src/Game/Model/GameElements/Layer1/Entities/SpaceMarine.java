package Game.Model.GameElements.Layer1.Entities;

import Game.View.Board.Views.SpaceMarineView;

import java.awt.*;

/**
 * Cette classe décrit les personnages du jeu qui sont des SpaceMarines
 */

public class SpaceMarine extends Entity{

    /**
     * Constructeur
     *
     * @param coordinate   coordonnées globales du SpaceMarine
     */
    public SpaceMarine(Point coordinate) {
        super("Space Marine",coordinate, new Dimension(1,1), 200);
        setView(new SpaceMarineView(this));
    }
}
