package Model;

import Model.Entities.Character;
import Model.Entities.Entity;
import Model.Entities.Mountain;
import Model.Entities.SpaceShip;

import java.awt.*;

public class Board {

    private Entity[][] board;

    public Board() {
        this.board = new Entity[GameConstants.BOARD_COLS][GameConstants.BOARD_ROWS];
        this.addEntity(new Character(), new Point(0,0));
        this.addEntity(new Character(), new Point(0,1));
        this.addEntity(new Character(), new Point(9,9));
        this.addEntity(new SpaceShip(), new Point(3,3));
        this.addEntity(new Mountain(), new Point(4,6));
        this.addEntity(new Mountain(), new Point(4,7));
        this.addEntity(new Mountain(), new Point(4,8));
        this.addEntity(new Mountain(), new Point(5,8));
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
