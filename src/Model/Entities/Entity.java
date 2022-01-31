package Model.Entities;

import java.awt.*;

/**class la plus globale pour les éléments du jeu*/
public abstract class Entity {
    // Attribut désignant la vie de l'entité
    private int healthPoints;
    // Attribut désignant la position de l'entitée
    private Point coordinate;
    // Méthode décrémentant la vie
    public void decrementLife(int n){
        this.healthPoints -= n;
    }
    // Getter de healthPoints
    public int gethealthPoints() {
        return healthPoints;
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }
}
