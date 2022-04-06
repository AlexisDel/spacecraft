package Game.Model.GameElements.Layer1.Structures;

import Game.View.Board.Views.MeteoriteView;

import java.awt.*;

public class Meteorite extends Structure {

    /**
     * Constructeur de la classe
     * @param coordinate   coordonnées globales de la structure
     * @param dimension    width et size de la structure
     * @param healthPoints points de vie de la structure entre 100 et 800
     */
    public Meteorite(Point coordinate, Dimension dimension, int healthPoints) {
        super("Meteorite", coordinate, dimension, healthPoints, 0);

        setView(new MeteoriteView(this));

    }
    /**
     * Cette méthode est utilisée quand on mine un météorite, on diminue les points de vie et les roches
     * Les roches diminuent et les HealthPoints diminuent de hitPoints
     * @param hitPoints
     */
    public void mined(int hitPoints){
        if(this.getHealthPoints()-hitPoints<0){
            setHealthPoints(0);
        }
        else {
            this.setHealthPoints(this.getHealthPoints() - hitPoints);
        }
    }
}
