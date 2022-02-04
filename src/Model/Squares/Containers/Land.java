package Model.Squares.Containers;

import Model.GameSquare;
import Model.Squares.Container;

import java.awt.*;

import static View.BoardPanel.boardTileSize;
import static View.BoardPanel.tileSize;

/**
 * Case représentant un terrain
 */
public class Land extends Container implements GameSquare {

    public Land() {
        super();
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, boardTileSize, boardTileSize);

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(getEntities()[i][j] != null){
                    getEntities()[i][j].draw(g, x+(i*tileSize), y+(j*tileSize));
                }
            }
        }
    }

    @Override
    public void clicked(int localX, int localY) {
        System.out.println("Land !");

        // TODO : discuter de l'implémentation (click direct sur entity?)
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(getEntities()[i][j] != null){
                    System.out.println("entity");
                }
            }
        }

    }
}
