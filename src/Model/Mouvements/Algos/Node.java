package Model.Mouvements.Algos;

import Model.Mouvements.HitBoard;

import java.awt.*;
import java.util.ArrayList;

/**
 * structure pour A*
 */
public class Node{
    // Attributs
    private int posx;
    private int posy;
    private Node parent;
    // Attribut pour A*
    private double h;
    private double g;
    private double f;

    /**
     * constructeur sans initialisation de parent
     * @param x
     * @param y
     */
    public Node(int x, int y){
        this.posx = x;
        this.posy = y;
        this.parent = null;
    }

    /**
     * constructeur de Node prenant en paramêtre un Point
     * @param p
     */
    public Node(Point p){
        this.posx = p.x;
        this.posy = p.y;
        this.parent = null;
    }

    /**
     * constructeur avec initialisationde parent
     * @param x
     * @param y
     * @param p
     */
    public Node(int x, int y, Node p){
        this.posx = x;
        this.posy = y;
        this.parent = p;
    }

    /**
     * getter de pos X
     * @return
     */
    public int getPosx() {
        return posx;
    }

    /**
     * getter de pos Y
     * @return
     */
    public int getPosy() {
        return posy;
    }

    /**
     * getter de parent
     * @return
     */
    public Node getParent() {
        return parent;
    }

    /**
     * getter de h
     * @return
     */
    public double getH() {
        return h;
    }

    /**
     * getter de f
     * @return
     */
    public double getF() {
        return f;
    }

    /**
     * getter de g
     * @return
     */
    public double getG() {
        return g;
    }

    /**
     * setter de parent
     * @param parent
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }

    /**
     * setter de h
     * @param h
     */
    public void setH(double h) {
        this.h = h;
    }

    /**
     * setter de f
     * @param f
     */
    public void setF(double f) {
        this.f = f;
    }

    /**
     * setter de g
     * @param g
     */
    public void setG(double g) {
        this.g = g;
    }

    /**
     * méthode renvoyanbt la liste des enfants du noeud courrant
     * @param hb
     * @return
     */
    public ArrayList<Node> getChild(HitBoard hb){
        ArrayList<Node> res = new ArrayList<>();
        if(hb.isInBoard(this.posx, this.posy - 1) && hb.isEmpty(this.posx, this.posy - 1))
            res.add(new Node(this.posx, this.posy - 1, this));
        if(hb.isInBoard(this.posx, this.posy + 1) && hb.isEmpty(this.posx, this.posy + 1))
            res.add(new Node(this.posx, this.posy + 1, this));
        if(hb.isInBoard(this.posx - 1, this.posy) && hb.isEmpty(this.posx - 1, this.posy))
            res.add(new Node(this.posx - 1, this.posy, this));
        if(hb.isInBoard(this.posx + 1, this.posy) && hb.isEmpty(this.posx + 1, this.posy))
            res.add(new Node(this.posx + 1, this.posy, this));
        return res;
    }

    /**
     * méthode d'égalité surécrivant Object.equals
     * @param n
     * @return
     */
    public boolean equals(Node n){
        return this.posx == n.getPosx() && this.posy == n.getPosy();
    }

    /**
     * méthode représantant un objet Node
     * @return
     */
    public String toString(){
        String res = "";
        res += "node(" + this.posx + " " + this.posy;
        if(this.getParent() != null){
            res += ", parent : [" + this.getParent().getPosx() + ", " + this.getParent().getPosy() + "]";
        }
        res += " f = " + this.getF();
        res += " g = " + this.getG();
        res += ")";
        return res;
    }
}
