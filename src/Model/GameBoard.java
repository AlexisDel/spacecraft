package Model;

import Model.Layer0.Mountain;
import Model.Layer1.Entities.Alien;
import Model.Layer1.Entities.Entity;
import Model.Layer1.Entities.SpaceMarine;
import Model.Layer1.Structures.Spaceship;
import Model.Layer1.Structures.Structure;
import Model.Mouvements.HitBoard;

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
    /** Tableau 2D de booléens désignant le monde selon les aliens avec leur peur des spaceMarines*/
    HitBoard AlienView;

    /**
     * Constructeur, initialise les différentes couches qui composent notre terrain de jeu
     */
    public GameBoard() {
        mountains = new RandomLandGeneration().getBoard();
        this.hitbox = new HitBoard(this);
        this.AlienView = new HitBoard(this);
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

        // Trouve une zone libre assez grande sur la carte pour accueillir le vaisseau et les space marines
        boolean findAPlace = false;
        while (!findAPlace){

            // Calcul des coordonnées aléatoire pour la position du vaisseau
            int shipX = rand.nextInt(GameConstants.BOARD_SIZE);
            int shipY = rand.nextInt(GameConstants.BOARD_SIZE);

            boolean isThisPlaceBigEnough = true;
            // Verifie que la zone trouvée est assez grande
            for (int i = 0; i < 16; i++){
                for(int j = 0; j < 16; j++){
                    if(!(this.isInBoard(shipX + i, shipY + j)) || !(this.hitbox.isEmpty(shipX + i, shipY + j))){
                        isThisPlaceBigEnough = false;
                    }
                }
            }
            if(isThisPlaceBigEnough){
                // TODO : to modularize

                // Ajoute le vaisseau sur la carte
                structures.add(new Spaceship(new Point(shipX+6,shipY+6), new Dimension(4, 4), 1000, 10));
                for(int i = 6; i < 10; i++){
                    for(int j = 6; j < 10; j++){
                        this.hitbox.fill(new Point(shipX + i, shipY + j));
                        this.AlienView.fill(new Point(shipX + i, shipY + j));
                    }
                }

                // Ajoute les spaces marines sur la carte
                for (int i = 0; i < nbSpaceMarines; i++) {
                    int newX = shipX + (8 + (rand.nextBoolean() ? 1 : -1) * (2+rand.nextInt(4)));
                    int newY = shipY + (8 + (rand.nextBoolean() ? 1 : -1) * (2+rand.nextInt(4)));
                    entities.add(new SpaceMarine(new Point(newX,newY),200,10));
                    this.hitbox.fill(new Point(newX, newY));
                    for(int j = -GameConstants.fearOfSpaceMarines/2; j < GameConstants.fearOfSpaceMarines/2 + 1; j++){
                        for(int k = -GameConstants.fearOfSpaceMarines/2; k < GameConstants.fearOfSpaceMarines/2 + 1; k++){
                            if(isInBoard(newX + j, newY + k)) {
                                this.AlienView.fill(new Point(newX + j, newY + k));
                            }
                        }
                    }
                }

                findAPlace = true;
            }
        }

        // Enfin, on ajoute nbAliens alien dans le tableau
        for(int i = 0; i < nbAliens; i++){
            int newX = rand.nextInt(GameConstants.BOARD_SIZE);
            int newY = rand.nextInt(GameConstants.BOARD_SIZE);
            while (!this.AlienView.isEmpty(newX, newY)){
                newX = rand.nextInt(GameConstants.BOARD_SIZE);
                newY = rand.nextInt(GameConstants.BOARD_SIZE);
            }
            Alien tempAlien = new Alien(new Point(newX,newY), 150, 10);
            entities.add(tempAlien);
            this.AlienView.fill(new Point(newX, newY));
            AlienMovements am = new AlienMovements(tempAlien, this.AlienView, this);
            am.start();
        }
    }

    /**
     * getter de la hitbox
     * @return HitBoard
     */
    public HitBoard getHitbox() {
        return hitbox;
    }

    /**
     * getter de AlienView
     * @return HitBoard
     */
    public HitBoard getAlienView(){return this.AlienView;}
}
