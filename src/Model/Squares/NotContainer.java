package Model.Squares;

import Model.Entities.Entity;

public abstract class NotContainer {

    public boolean addEntityToSquare(Entity entity, int x, int y) {
        return false;
    }

    public Entity[][] getEntities() {
        return null;
    }
}
