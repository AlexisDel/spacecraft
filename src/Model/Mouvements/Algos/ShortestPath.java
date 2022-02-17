package Model.Mouvements.Algos;

import Model.Mouvements.Direction;
import Model.Mouvements.HitBoard;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.pow;

/**
 * Classe calculant les plus courts chemins
 */
public class ShortestPath {
    // tableau 2D de booléen de dimension celles de la grille du jeu stockant les cases occupées ou non
    private HitBoard hitbox;

    /**
     * Constructeur de la classe
     */
    public ShortestPath(HitBoard hb){
        this.hitbox = hb;
    }


    /**
     * heuristique pour A*
     * @param p1 : point de départ
     * @param p2 : point d'arrivée
     * @return
     */
    private double heuristic(Node p1, Node p2){
        return Math.sqrt(pow((p1.getPosx() - p2.getPosy()), 2) + pow((p1.getPosx() - p2.getPosy()), 2));
    }

    /**
     * méthode principale pour A*
     * @param start
     * @param end
     * @return
     */
    private ArrayList<Point>AStar(Node start, Node end){
        // Initialisation de start
        start.setG(0);
        start.setH((int) heuristic(start, end));
        start.setF(start.getG() + start.getH());

        // Initialisation de Open et Close
        ArrayList<Node> Open = new ArrayList<>();
        ArrayList<Node> Close = new ArrayList<>();
        Open.add(start);
        // Tant que la recherche continue
        while(!Open.isEmpty()){
            // Entier servant lors des parcours de listes de Noeuds
            int pos = 0;
            // On suppose Open non-vide
            Node currentNode = Open.get(0);
            // On cherche le noeud de plus petite fvalue dans open : currentNode
            for(int i = 0; i < Open.size(); i++){
                if(Open.get(i).getF() < currentNode.getF()){
                    currentNode = Open.get(i);
                    pos = i;
                }
                else{
                }
            }
            // On retire currentNode de Open
            Open.remove(pos);
            // Si on trouve l'arrivée, alors on arrête
            if(currentNode.equals(end)){
                end = currentNode;
                break;
            }
            // On ajoute le noeud courrant à close
            Close.add(currentNode);
            for(Node child : currentNode.getChild(this.hitbox)){
                double cost = currentNode.getG() + this.heuristic(currentNode, end);
                // Pos pour potentiellement retirer l'enfant des listes
                int posOpen = 0;
                int posClose = 0;
                // Si child est dans la liste Open, et à une plus petite fvalue, on ignore
                boolean flag1 = true;
                boolean notSeenOpen = true;
                for(int i = 0; i < Open.size(); i++){
                    if(Open.get(i).equals(child)){
                        notSeenOpen = false;
                        if(Open.get(i).getF() < cost){
                            posOpen = i;
                            flag1 = false;
                            break;
                        }
                    }
                }
                if(flag1 && !notSeenOpen)
                    Open.remove(posOpen);
                boolean flag2 = false;
                boolean notSeenClose = true;
                // Si child est dans Close mais et avec une plus petite valeur f, on ignore
                for(int i = 0; i < Close.size(); i++){
                    if(Close.get(i).equals(child)){
                        notSeenClose = false;
                        if(Close.get(i).getF() < cost){
                            posClose = i;
                            flag2 = true;
                            break;
                        }
                    }
                }
                if(flag2 && !notSeenClose)
                    Close.remove(posClose);
                if(notSeenOpen && notSeenClose){
                    // On calcul les heuristiques de child
                    child.setG(currentNode.getG());
                    child.setH(this.heuristic(child, end));
                    child.setF(child.getG() + child.getH());
                    // On fixe le parent
                    child.setParent(currentNode);
                    Open.add(child);
                }
            }
        }
        return this.backtrack(start, end);
    }

    /**
     * backtracking
     * @param start
     * @param end
     * @return
     */
    public ArrayList<Point> backtrack(Node start, Node end){
        ArrayList<Point> res = new ArrayList<>();
        Node currentNode = end;
        while(!currentNode.equals(start)){
            res.add(new Point(currentNode.getPosx(), currentNode.getPosy()));
            currentNode = currentNode.getParent();
        }
        res.add(new Point(start.getPosx(), start.getPosy()));
        return res;
    }

    public Direction nextMove(Point start, Point end){
        Node Nstart = new Node(start.x, start.y);
        Node Nend = new Node(end.x, end.y);
        ArrayList<Point> track = this.backtrack(Nstart, Nend);
        assert track.size() != 0;
        if(track.size() == 1){
            return Direction.NULL;
        }
        else{
            Point delta = new Point( start.y - track.get(track.size() - 1).x,start.y - track.get(track.size() - 1).y);
            if (new Point(1, 0).equals(delta)) {
                return Direction.SOUTH;
            }
            else if (new Point(-1, 0).equals(delta)) {
                return Direction.NULL;
            }
            else if (new Point(0, 1).equals(delta)) {
                return Direction.EAST;
            }
            else if (new Point(0, -1).equals(delta)) {
                return Direction.WEST;
            }
            else{
                return Direction.NULL;
            }
        }
    }

    /**
     * méthode main pour tests
     * @param args
     */
    public static void main(String[] args) {
        HitBoard hb1 = new HitBoard(2);
        hb1.fill(new Point(1,1));
        System.out.println(hb1);

        ShortestPath sp = new ShortestPath(hb1);
        Node start = new Node(0,0);
        Node end = new Node(1,0);
        ArrayList<Point>test = sp.AStar(start, end);
        System.out.println(test);

        //--------------------------------------------

        HitBoard hb2 = new HitBoard(4);
        hb2.fill(new Point(1,1));
        hb2.fill(new Point(1,2));
        hb2.fill(new Point(2,2));
        hb2.fill(new Point(3,2));
        System.out.println(hb2);
        sp = new ShortestPath(hb2);
        start = new Node(3,3);
        end = new Node(2,1);
        test = sp.AStar(start, end);
        System.out.println(test);
    }



}

//-----------------------------------------------------------------------------------------//
/**
 * structure pour A*
 */
class Node{
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
     * getter de g
     * @return
     */
    public double getG() {
        return g;
    }

    /**
     * getter de f
     * @return
     */
    public double getF() {
        return f;
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
     * setter de g
     * @param g
     */
    public void setG(double g) {
        this.g = g;
    }

    /**
     * setter de f
     * @param f
     */
    public void setF(double f) {
        this.f = f;
    }

    /**
     * méthode renvoyanbt la liste des enfants du noeud courrant
     * @param hb
     * @return
     */
    public ArrayList<Node>getChild(HitBoard hb){
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
     * méthode d'égalité
     * @param n
     * @return
     */
    public boolean equals(Node n){
        return this.posx == n.getPosx() && this.posy == n.getPosy();
    }

    public String toString(){
        String res = "";
        res += "node(" + this.posx + " " + this.posy;
        if(this.getParent() != null){
            res += ", parent : [" + this.getParent().getPosx() + ", " + this.getParent().getPosy() + "]";
        }
        res += ")";
        return res;
    }
}


