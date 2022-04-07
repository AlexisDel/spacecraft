package Game.View.Board.Views;

import Game.Model.GameElements.GameElement;
import Game.View.RessourceManager;

import java.awt.*;

import static Game.View.ViewConstants.TILE_SIZE;

public abstract class ItemView {

    // Pointeur vers l'élément du jeu associé à cette vue
    GameElement gameElement;

    /**
     * Constructeur
     * @param gameElement élément lié à cette vie
     */
    public ItemView(GameElement gameElement) {
        this.gameElement = gameElement;
    }

    /**
     * Renvoie le nom de l'image à dessiner par la vue pour l'élément associé à celle-ci
     */
    public String getImageName(){
        return gameElement.getClass().getSimpleName();
    }

    /**
     * Dessine la vue associée à l'élément
     * @param g
     */
    public void draw(Graphics2D g) {
        g.drawImage(RessourceManager.ImageManager.getTileImage(getImageName()), (gameElement.getCoordinate().x)*TILE_SIZE, (gameElement.getCoordinate().y)*TILE_SIZE, gameElement.getDimension().width*TILE_SIZE, gameElement.getDimension().height*TILE_SIZE, null);
    }
}
