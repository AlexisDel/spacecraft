package Model.Squares;

import Model.Entities.Entity;

/**
 * Classe abstraite représentant toutes les cases pouvant accueillir des entités
 */
public abstract class Container {

    // Liste des entities
    Entity[][] entities;

    public Container() {
        this.entities = new Entity[4][4];
    }

    // Ajoute une entity sur une case
    public boolean addEntityToSquare(Entity entity, int x, int y){
        entities[x][y] = entity;
        return true;
    }

    // Getter de la liste des entités sur la case
    public Entity[][] getEntities() {
        return this.entities;
    }

}
