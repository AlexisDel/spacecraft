package Model.Squares.Containers;

import Model.Entities.Character.Alien;
import Model.Entities.Entity;
import Model.GameSquare;
import Model.Squares.Container;

import java.awt.*;

import static View.BoardPanel.scaleSquare;
import static View.BoardPanel.tileSize;

/**
 * Case représentant un terrain
 */
public class Land extends Container implements GameSquare {

    public Land() {
        super();
    }

    @Override
    public void draw(Graphics2D g, int i, int j) {
        g.setColor(Color.ORANGE);
        g.fillRect(i*tileSize*scaleSquare, j*tileSize*scaleSquare, tileSize*scaleSquare, tileSize*scaleSquare);

        for(int x = 0; x < 4; x++){
            for(int y = 0; y < 4; y++){
                if(this.getEntities()[x][y] instanceof Alien){
                    g.setColor(Color.GREEN);
                    g.fillRect(i*tileSize*4+(x*tileSize), j*tileSize*4+(y*tileSize), tileSize, tileSize);
                }
            }
        }
    }
}
