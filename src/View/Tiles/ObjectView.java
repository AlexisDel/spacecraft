package View.Tiles;

import Model.Object;

import java.awt.*;

public abstract class ObjectView {

    Object object;

    public ObjectView(Object object) {
        this.object = object;
    }

    public abstract void draw(Graphics2D g, int tileSize, int displayX, int displayY);

    public boolean isDisplayed(int screenX, int screenY, int displayedGridSize){

        // À gauche de la fenêtre
        if (object.getCoordinate().x + object.getDimension().width <= screenX){
            return false;
        }
        // À droite de la fenêtre
        else if (object.getCoordinate().x >= screenX + displayedGridSize){
            return false;
        }
        // Au dessus de la fenêtre
        else if (object.getCoordinate().y + object.getDimension().height <= screenY){
            return false;
        }
        // En dessous de la fenêtre
        else if (object.getCoordinate().y >= screenY + displayedGridSize){
            return false;
        }
        else {
            return true;
        }
    }
}
