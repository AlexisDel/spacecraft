package Model;

import Model.Layer0.Mountain;
import Model.Layer1.Entities.Entity;
import Model.Layer1.Structures.Structure;

import java.awt.*;
import java.util.ArrayList;

/**
 * classe désignant les hitbow ^^ de la grille
 */
public class HitBoard<Synchronised, synchronised> {
    // Tableau de booléen désignant les endroits vides ou non
    ArrayList<ArrayList<Boolean>> hitbox;

    /**
     * constructeur
     * @param gb
     */
    public HitBoard(GameBoard gb){
        this.hitbox = new ArrayList<>(GameConstants.BOARD_SIZE);
        for(int i = 0; i < GameConstants.BOARD_SIZE; i++){
            this.hitbox.add(new ArrayList<>(GameConstants.BOARD_SIZE));
            for(int j = 0; j < GameConstants.BOARD_SIZE; j++){
                this.hitbox.get(i).add(true);
            }
        }
        // On remplie le tableau par les montagnes
        for(Mountain m : gb.getMountains()){
            Point pos = m.getCoordinate();
            Dimension dim = m.getDimension();
            for(int i = 0; i < dim.width; i++){
                for(int j = 0; j < dim.height; j++){
                    this.hitbox.get(pos.x + i).set(pos.y + j, false);
                }
            }
        }
    }

    /**
     * renvoie vrai si la case (x, y) est vide
     * @param x
     * @param y
     * @return
     */
    public boolean isEmpty(int x, int y){
        return this.hitbox.get(x).get(y);
    }

    /**
     * vide la case (x, y)
     * @param x
     * @param y
     */
    public synchronized void empty(int x, int y){
        this.hitbox.get(x).set(y, true);
    }

    /**
     * remplie la case (x, y)
     * @param x
     * @param y
     */
    public synchronized void fill(int x, int y){
        this.hitbox.get(x).set(y, false);
    }

}
