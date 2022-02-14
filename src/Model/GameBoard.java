package Model;

import Model.Layer0.Mountain;
import Model.Layer1.Entities.Alien;
import Model.Layer1.Entities.Entity;
import Model.Layer1.Entities.SpaceMarine;
import Model.Layer1.Structures.Spaceship;
import Model.Layer1.Structures.Structure;
import Model.Mouvements.AlienMouvement;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameBoard {

    /** Le terrain du jeu */
    ArrayList<Mountain> mountains;
    /** Liste représentant les structures qui sont sur le terrain de jeu
     *  une structure étant un objet immobile */
    ArrayList<Structure> structures;
    /** Liste représentant les entités qui sont sur le terrain de jeu
     *  une entité étant un objet que l'on peut déplacer sur la carte */
    ArrayList<Entity> entities;
    /***
     * Tableau 2D de booléens désignants les zones libres ou non-libres*/
    HitBoard hitbox;

    /**
     * Constructeur, initialise les différentes couches qui composent notre terrain de jeu
     */
    public GameBoard() {
        mountains = new RandomLandGeneration().getBoard();
        this.hitbox = new HitBoard(this);
        structures = new ArrayList<>();
        entities = new ArrayList<>();
        this.initLand(5, 3);
    }

    /**
     * @return La liste des montagnes présentes sur le terrain
     */
    public ArrayList<Mountain> getMountains() {
        return mountains;
    }

    /**
     * @return La liste des structures présentes sur le terrain
     */
    public ArrayList<Structure> getStructures() {
        return structures;
    }

    /**
     * @return La liste des entités présentes sur le terrain
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    /**
     * renvoie si un point est dans la map ou non
     * @param x
     * @param y
     * @return
     */
    public boolean isInBoard(int x, int y){
        return x >= 0 && y >= 0 && x < GameConstants.BOARD_SIZE && y < GameConstants.BOARD_SIZE;
    }

    private void initLand(int nbAliens, int nbSpaceMarines){
        Random rand = new Random();
        // Génération aléatoire de la position du space ship
        int shipX = rand.nextInt(GameConstants.BOARD_SIZE);
        int shipY = rand.nextInt(GameConstants.BOARD_SIZE);
        boolean flag = false;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(!(this.isInBoard(shipX + i, shipY + j)) || !(this.hitbox.isEmpty(shipX + i, shipY + j))){
                    flag = true;
                }
            }
        }
        while (flag) {
            flag = false;
            shipX = rand.nextInt(GameConstants.BOARD_SIZE);
            shipY = rand.nextInt(GameConstants.BOARD_SIZE);
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    if(!(this.isInBoard(i, j)) || !(this.hitbox.isEmpty(shipX + i, shipY + j))){
                        flag = true;
                    }
                }
            }
        }
        structures.add(new Spaceship(new Point(shipX,shipY), new Dimension(4, 4), 1000, 10));
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                this.hitbox.fill(shipX + i, shipY + j);
            }
        }

        //On ajoute nbSpaceMarines space marines, à max 3 cases de distance du space ship
        for (int i = 0; i < nbSpaceMarines; i++) {
          int newX = shipX - 3 + rand.nextInt(10);
          int newY = shipY - 3 + rand.nextInt(10);
            //la nouvel GameSquare doit être dans le tableau et a une case vide
            while (!isInBoard(newX, newY) || !(this.hitbox.isEmpty(newX, newY))){
                newX = shipX - 3 + rand.nextInt(10);
                newY = shipY - 3 + rand.nextInt(10);
                }
            entities.add(new SpaceMarine(new Point(newX,newY),200,10, this));
            this.hitbox.fill(newX, newY);
        }

        // Enfin, on ajoute nbAliens alien dans le tableau
        for(int i = 0; i < nbAliens; i++){
            int newX = rand.nextInt(GameConstants.BOARD_SIZE);
            int newY = rand.nextInt(GameConstants.BOARD_SIZE);
            while (!this.hitbox.isEmpty(newX, newY)){
                newX = rand.nextInt(GameConstants.BOARD_SIZE);
                newY = rand.nextInt(GameConstants.BOARD_SIZE);
            }
            Alien tempAlien = new Alien(new Point(newX,newY), 150, 10, this);
            entities.add(tempAlien);
            this.hitbox.fill(newX, newY);
            AlienMouvement am = new AlienMouvement(this, tempAlien);
            am.start();
        }
    }

    /**
     * getter de la hitbox
     * @return
     */
    public HitBoard getHitbox() {
        return hitbox;
    }
}
