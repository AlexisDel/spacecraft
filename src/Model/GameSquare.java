package Model;

import Model.Entities.Entity;

import java.awt.*;

/**
 * Interface représentant une case du plateau
 */
public interface GameSquare {

    public boolean addEntity(Entity entity, int i, int j);

    Entity[][] getEntities();

    void draw(Graphics2D g, int x, int y);
}
