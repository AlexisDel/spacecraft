package Model;

import Model.Entities.Character;
import Model.Entities.Entity;
import Model.Entities.Mountain;
import Model.Entities.SpaceShip;

import java.awt.*;
import java.util.Random;

public class Board {

    private Entity[][] board;

    public Board() {
        this.board = new Entity[GameConstants.BOARD_COLS][GameConstants.BOARD_ROWS];
        this.randomStartingBoard();
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

    public boolean isInBoard(Point p){
        return p.x < GameConstants.BOARD_COLS && p.y < GameConstants.BOARD_ROWS && p.x >= 0 && p.y >= 0;
    }

    /**
     * méthode privée générant un plateau de départ.
     * toDo : améliorer la génération des reliefs pour faire structures plus intéressantes
     */
    private void randomStartingBoard(){
        // Génération d'un objet random
        Random rand = new Random();

        // Génération aléatoire de la position du space ship
        int shipX = rand.nextInt(GameConstants.BOARD_COLS);
        int shipY = rand.nextInt(GameConstants.BOARD_ROWS);
        this.addEntity(new SpaceShip(), new Point(shipX, shipY));
        int newX, newY;
        // On ajoute 3 space marines, à max 2 cases de distance du space ship
        for(int i = 0; i < 3; i++){
            newX = shipX -2 + rand.nextInt(4);
            newY = shipY -2 + rand.nextInt(4);
            //la nouvel entity doit être dans le tableau et a une case vide
            while(!isInBoard(new Point(newX, newY)) || !(this.board[newX][newY] == null)){
                newX = shipX -2 + rand.nextInt(4);
                newY = shipY -2 + rand.nextInt(4);
            }
            this.addEntity(new Character(), new Point(newX, newY));
        }
        // On veut ensuite générer un relief, tmp pour temporary.
        int tmp = Math.min(GameConstants.BOARD_COLS,GameConstants.BOARD_ROWS) - 1;
        for(int i = 0; i < tmp; i++){
            newX = rand.nextInt(GameConstants.BOARD_COLS);
            newY = rand.nextInt(GameConstants.BOARD_ROWS);
            // Le relief doit être à une case vide
            while(!(this.board[newX][newY] == null)){
                newX = rand.nextInt(GameConstants.BOARD_COLS);
                newY = rand.nextInt(GameConstants.BOARD_ROWS);
            }
            this.addEntity(new Mountain(), new Point(newX, newY));
        }
    }
}
