package Game.View.Board.Views;

import Game.Model.GameElements.GameElement;
import Game.View.RessourceManager;

import java.awt.*;

import static Game.View.ViewConstants.TILE_SIZE;

public abstract class ItemView {

    GameElement gameElement;

    public ItemView(GameElement gameElement) {
        this.gameElement = gameElement;
    }

    public String getImageName(){
        return gameElement.getClass().getSimpleName();
    }

    public void draw(Graphics2D g) {
        g.drawImage(RessourceManager.ImageManager.getTileImage(getImageName()), (gameElement.getCoordinate().x)*TILE_SIZE, (gameElement.getCoordinate().y)*TILE_SIZE, gameElement.getDimension().width*TILE_SIZE, gameElement.getDimension().height*TILE_SIZE, null);
    }
}
