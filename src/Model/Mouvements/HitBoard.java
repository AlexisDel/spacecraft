package Model.Mouvements;

import Model.GameBoard;
import Model.GameConstants;
import Model.Layer0.Mountain;
import Model.Layer1.Entities.Entity;
import Model.Layer1.Structures.Structure;

import java.awt.*;
import java.util.ArrayList;

/**
 * classe désignant les hitbox ^^ de la grille
 */
public class HitBoard<Synchronised, synchronised> {
    // Tableau de booléen désignant les endroits vides ou non
    ArrayList<ArrayList<Boolean>> hitbox;
    int dim;

    /**
     * constructeur
     * @param gb
     */
    public HitBoard(GameBoard gb){
        this.dim = GameConstants.BOARD_SIZE;
        this.hitbox = new ArrayList<>(this.dim);
        for(int i = 0; i < this.dim; i++){
            this.hitbox.add(new ArrayList<>(this.dim));
            for(int j = 0; j < this.dim; j++){
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
     * autre constructeur pour tests
     * @param n
     */
    public HitBoard(int n){
        this.dim = n;
        this.hitbox = new ArrayList<>(n);
        for(int i = 0; i < n; i++){
            this.hitbox.add(new ArrayList<>(n));
            for(int j = 0; j < n; j++){
                this.hitbox.get(i).add(true);
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

    public boolean isInBoard(int x, int y){
        return x >= 0 && x < this.dim && y >= 0 && y < this.dim;
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

    public String toString(){
        String s = "";
        for(int i = 0; i < this.dim; i++){
            for(int j = 0; j < this.dim; j++){
                if(this.hitbox.get(i).get(j))
                    s += ". ";
                else{
                    s += "$ ";
                }
            }
            s += "\n";
        }
        return s;
    }

}
