package Model.Cells;

import Model.Entities.Entity;
import Model.GameCell;

import java.util.ArrayList;

public abstract class Container extends GameCell {

    ArrayList<Entity> entities;

    public Container() {
        this.entities = new ArrayList<>();
    }

    public void addEntity(Entity entity){
        entities.add(entity);
    }

    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

}
