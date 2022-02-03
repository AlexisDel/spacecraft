package Model.Squares;

import Model.Entities.Entity;
import Model.GameSquare;

public abstract class NotContainer {

    public boolean addEntity(Entity entity, int x, int y) {
        return false;
    }

    public Entity[][] getEntities() {
        return null;
    }
}
