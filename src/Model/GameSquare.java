package Model;

import Model.Entities.Entity;
import View.Squares.SquareView;

/**
 * Interface représentant une case du plateau
 */
public interface GameSquare {

    boolean addEntityToSquare(Entity entity, int x, int y);

    Entity[][] getEntities();

    void clicked(int localX, int localY);

    void setView(SquareView view);

    SquareView getView();

}
