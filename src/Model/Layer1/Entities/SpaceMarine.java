package Model.Layer1.Entities;

import View.Tiles.SpaceMarineView;

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
     * @param speed        vitesse en ms du SpaceMarine
     */
    public SpaceMarine(Point coordinate, int healthPoints, int speed) {
        super("Space Marine",coordinate, new Dimension(1,1), healthPoints, speed);
        setView(new SpaceMarineView(this));
        super.addAction(Action.MOVE);
        super.addAction(Action.ATTACK);
    }
    @Override
    public void move(Point p){
        this.getGameBoard().getHitbox().empty(this.getCoordinate().x, this.getCoordinate().y);
        this.setCoordinate(p);
        this.getGameBoard().getHitbox().fill(p.x, p.y);
    }
}
