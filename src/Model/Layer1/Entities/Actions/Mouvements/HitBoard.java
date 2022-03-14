package Model.Layer1.Entities.Actions.Mouvements;

import Model.GameBoard;
import Model.GameConstants;
import Model.Layer0.Mountain;

import java.awt.*;
import java.util.ArrayList;

/**
 * classe désignant les hitbox ^^ de la grille
 */
public class HitBoard {
    // Tableau de booléen désignant les endroits vides ou non
    ArrayList<ArrayList<Integer>> hitbox;
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
                this.hitbox.get(i).add(0);
            }
        }
        // On remplie le tableau par les montagnes
        for(Mountain m : gb.getMountains()){
            Point pos = m.getCoordinate();
            Dimension dim = m.getDimension();
            for(int i = 0; i < dim.width; i++){
                for(int j = 0; j < dim.height; j++){
                    if(this.isInBoard(pos.x + i, pos.y + j))
                        this.hitbox.get(pos.x + i).set(pos.y + j, 1);
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
                this.hitbox.get(i).add(0);
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
        return this.hitbox.get(x).get(y) == 0;
    }

    public boolean isInBoard(int x, int y){
        return x >= 0 && x < this.dim && y >= 0 && y < this.dim;
    }

    /**
     * vide la case de coordonnées (x, y)
     * @param p
     */
    public synchronized void empty(Point p){
        int tmp = this.hitbox.get(p.x).get(p.y);
        if(tmp > 0)
            tmp--;
        this.hitbox.get(p.x).set(p.y, tmp);
    }

    /**
     * remplie la case de coordonnées (x, y)
     */
    public synchronized void fill(Point p){
        int tmp = this.hitbox.get(p.x).get(p.y);
        tmp++;
        this.hitbox.get(p.x).set(p.y, tmp);
    }

    public String toString(){
        String s = "";
        for(int i = 0; i < this.dim; i++){
            for(int j = 0; j < this.dim; j++){
                if(this.hitbox.get(j).get(i) == 0)
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
