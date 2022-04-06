package Game.Model.GameElements.Layer1;

import Game.Model.GameElements.GameElement;

import java.awt.*;

public class InteractiveGameElement extends GameElement {

    /**Attributes*/
    private int healthPoints;
    private String name;

    /**
     * Constructeur
     * @param coordinate   coordonnées globales de l'entité
     * @param dimension    width et size de l'entité
     * @param healthPoints points de vie de l'entité
     */
    public InteractiveGameElement(String name, Point coordinate, Dimension dimension, int healthPoints) {
        super(coordinate, dimension);
        this.name = name;
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public String getName(){ return this.name;}

}