package Model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Classe calculant les plus courts chemins
 */
public class ShortestPath {
    // tableau 2D de booléen de dimension celles de la grille du jeu stockant les cases occupées ou non
    private ArrayList<ArrayList<Boolean>>HitBox;

    /**
     * Constructeur de la classe
     */
    public ShortestPath(){
        this.HitBox = new ArrayList<>(GameConstants.BOARD_SIZE);
        for(ArrayList<Boolean> l : this.HitBox){
            l = new ArrayList<>(GameConstants.BOARD_SIZE);
            for(Boolean b : l){
                b = Boolean.FALSE;
            }
        }
    }

    /**
     * méthode remplissant une case
     * @param pos Point à modifier
     */
    public void fillTile(Point pos){
        this.HitBox.get(pos.x).set(pos.y, Boolean.TRUE);
    }

    /**
     * méthode vidant une case
     * @param pos Point à modifier
     */
    public void freeTile(Point pos){
        this.HitBox.get(pos.x).set(pos.y, Boolean.FALSE);
    }



}
