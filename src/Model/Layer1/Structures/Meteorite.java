package Model.Layer1.Structures;

import View.ItemsViews.MeteoriteView;

import java.awt.*;

public class Meteorite extends Structure {
    //private int rocks;

    /**
     * Constructeur de la classe
     * @param coordinate   coordonnées globales de la structure
     * @param dimension    width et size de la structure
     * @param healthPoints points de vie de la structure entre 100 et 800
     */
    public Meteorite(Point coordinate, Dimension dimension, int healthPoints) {
        super("Meteorite", coordinate, dimension, healthPoints, 0);

        //this.rocks= healthPoints*2;
        setView(new MeteoriteView(this));

    }
    /**Getter pour les roches de la météorite*/
    //public int getRocks() {
        //return rocks;
    //}
    /**Setter pour update le nombre de roches disponibles (ie: quand on mine on update ceci)*/
    //public void setRocks(int rocks) {
       // this.rocks = rocks;
    //}

    /**
     * Cette méthode est utilisée quand on mine un météorite, on diminue les points de vie et les roches
     * Les roches diminuent et les HealthPoints diminuent de hitPoints
     * @param hitPoints
     */
    public int mined(int hitPoints){
        //On garde le nombre de rocks courants dans une variable, ainsi si on mine plus qu'il y
        /*int res= rocks;
        if(this.getHealthPoints()-hitPoints<0){
            this.rocks=0;
            setHealthPoints(0);
            return res;
        }
        else{
            this.setHealthPoints(this.getHealthPoints()-hitPoints);
            this.rocks-=hitPoints*2;
            return hitPoints*2;
        }*/
        int res= this.getHealthPoints();
        if(this.getHealthPoints()-hitPoints<0){
            setHealthPoints(0);
            return res;
        }
        else {
            this.setHealthPoints(this.getHealthPoints() - hitPoints);
            return hitPoints;
        }

    }

}
