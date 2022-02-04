package Model;

import Model.Entities.Entity;

import java.awt.*;

/**
 * Interface représentant une case du plateau
 */
public interface GameSquare {

    boolean addEntityToSquare(Entity entity, int x, int y);

    Entity[][] getEntities();

    void draw(Graphics2D g, int x, int y, int boardTileSize, int tileSize);

    void clicked(int localX, int localY);
}
