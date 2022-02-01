package Model.Squares;

import Model.Entities.Entity;
import Model.GameSquare;

import java.util.ArrayList;

/**
 * Classe abstraite représentant toutes les cases pouvant accueillir des entités
 */
public abstract class Container implements GameSquare {

    // Liste des entities
    ArrayList<Entity> entities;

    public Container() {
        this.entities = new ArrayList<>();
    }

    // Ajoute une entity sur une case
    public void addEntity(Entity entity){
        entities.add(entity);
    }

    // Getter de la liste des entités sur la case
    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

}
