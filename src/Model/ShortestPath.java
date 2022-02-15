package Model;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.pow;

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

    /**
     * heuristique pour A*
     * @param p1 : point de départ
     * @param p2 : point d'arrivée
     * @return
     */
    private double heuristic(Point p1, Point p2){
        return Math.sqrt(pow((p1.x - p2.x), 2) + pow((p1.y - p2.y), 2));
    }

    private ArrayList<Point>AStar(Point start, Point end){
        ArrayList<Point> Open = new ArrayList<>();
        ArrayList<Point> Close = new ArrayList<>();
        return Open;
    }



}
