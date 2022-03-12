package View.Board.Tiles;

import Model.Item;
import View.ImageManager;

import java.awt.*;

public abstract class ItemTile {

    Item item;

    public ItemTile(Item item) {
        this.item = item;
    }

    public String getImageName(){
        return item.getClass().getSimpleName();
    }

    public void draw(Graphics2D g, int tileSize, int displayX, int displayY) {
        g.drawImage(ImageManager.getTileImage(getImageName()), (item.getCoordinate().x-displayX)*tileSize, (item.getCoordinate().y-displayY)*tileSize, item.getDimension().width*tileSize, item.getDimension().height*tileSize, null);
    }

    public boolean isDisplayed(int screenX, int screenY, int displayedGridSize){

        // À gauche de la fenêtre
        if (item.getCoordinate().x + item.getDimension().width <= screenX){
            return false;
        }
        // À droite de la fenêtre
        else if (item.getCoordinate().x >= screenX + displayedGridSize){
            return false;
        }
        // Au dessus de la fenêtre
        else if (item.getCoordinate().y + item.getDimension().height <= screenY){
            return false;
        }
        // En dessous de la fenêtre
        else if (item.getCoordinate().y >= screenY + displayedGridSize){
            return false;
        }
        else {
            return true;
        }
    }
}
