package View.Board.Tiles;

import Model.Item;
import View.ImageManager;

import java.awt.*;

import static View.ViewConstants.TILE_SIZE;

public abstract class ItemTile {

    Item item;

    public ItemTile(Item item) {
        this.item = item;
    }

    public String getImageName(){
        return item.getClass().getSimpleName();
    }

    public void draw(Graphics2D g) {
        g.drawImage(ImageManager.getTileImage(getImageName()), (item.getCoordinate().x)*TILE_SIZE, (item.getCoordinate().y)*TILE_SIZE, item.getDimension().width*TILE_SIZE, item.getDimension().height*TILE_SIZE, null);
    }
}
