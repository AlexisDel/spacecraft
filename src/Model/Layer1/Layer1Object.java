package Model.Layer1;

import Model.Object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Layer1Object extends Object {
    /**Attributes*/
    private int healthPoints;
    private String name;
    private String imagePath;
    private Image image;
    /**
     * Constructeur
     * @param coordinate   coordonnées globales de l'entité
     * @param dimension    width et size de l'entité
     * @param healthPoints points de vie de l'entité
     */
    public Layer1Object(String name, Point coordinate, Dimension dimension, int healthPoints, String imagePath) {
        super(coordinate, dimension);
        this.name=name;
        this.healthPoints = healthPoints;
        this.imagePath=imagePath;
        try {
            image = ImageIO.read(new File(imagePath));
            image= image.getScaledInstance(128,128,Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public String getName(){ return this.name;}

    public String getImagePath() {
        return imagePath;
    }

    public Image getImage() {
        return image;
    }
}
