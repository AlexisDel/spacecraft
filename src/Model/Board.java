package Model;

import Model.Entities.Character;
import Model.Entities.Entity;

import java.awt.*;

public class Board {

    private Entity[][] board;

    public Board() {
        this.board = new Entity[GameConstants.BOARD_COLS][GameConstants.BOARD_ROWS];
        this.addEntity(new Character(), new Point(0,0));
        this.addEntity(new Character(), new Point(0,1));
        this.addEntity(new Character(), new Point(9, 9));
    }

    public Entity[][] getArray() {
        return board;
    }

    /**
     * ajoute une entitée au plateau
     * @param entity
     * @param pos
     * toDo : check que la case soit vide
     */
    public void addEntity(Entity entity, Point pos){
        this.board[pos.x][pos.y] = entity;
        entity.setCoordinate(pos);
    }
}
