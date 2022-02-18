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
        return Math.sqrt(pow((p1.getPosx() - p2.getPosx()), 2) + pow((p1.getPosy() - p2.getPosy()), 2));
    }

    /**
     * méthode principale pour A*
     * @param start
     * @param end
     * @return
     */
    private ArrayList<Point>AStar(Node start, Node end){
        // Initialisation de start
        start.setH(heuristic(start, end));
        start.setF(start.getH());

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
                double cost = this.heuristic(currentNode, end);
                // Pos pour potentiellement retirer l'enfant des listes
                int posOpen = 0;
                int posClose = 0;
                // Si child est dans la liste Open, et à une plus petite fvalue, on l'enlève
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
                if(!flag1) {
                    Open.remove(posOpen);
                }
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
                if(flag2) {
                    Close.remove(posClose);
                }

                if(notSeenOpen && notSeenClose){
                    // On calcul les heuristiques de child
                    child.setH(this.heuristic(child, end));
                    child.setF(child.getH());
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
        ArrayList<Point> track = this.AStar(Nstart, Nend);
        for(Point p : track)
            System.out.print("(" + p.x + ", " + p.y + ") ");
        System.out.println();
        assert track.size() != 0;
        if(track.size() == 1){
            return Direction.NULL;
        }
        else{
            Point delta = new Point( track.get(track.size() - 2).x - start.x,track.get(track.size() - 2).y - start.y);
            if (new Point(1, 0).equals(delta)) {
                return Direction.SOUTH;
            }
            else if (new Point(-1, 0).equals(delta)) {
                return Direction.NORTH;
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
        System.out.println("////////////////////////");

        //--------------------------------------------

        HitBoard hb3 = new HitBoard(2);
        System.out.println(hb3);
        sp = new ShortestPath((hb3));
        start=  new Node(0,0);
        end = new Node(0,0);
        test = sp.AStar(start, end);
        System.out.println(test);
        System.out.println("////////////////////////");

        //--------------------------------------------

        HitBoard hb4 = new HitBoard(10);
        hb4.fill(new Point(0,1));
        hb4.fill(new Point(1,1));
        hb4.fill(new Point(2,1));
        hb4.fill(new Point(3,1));
        hb4.fill(new Point(4,1));
        hb4.fill(new Point(5,1));

        hb4.fill(new Point(7,0));
        hb4.fill(new Point(7,1));
        hb4.fill(new Point(8,1));

        hb4.fill(new Point(3,2));
        hb4.fill(new Point(3,3));
        hb4.fill(new Point(3,4));
        hb4.fill(new Point(3,5));
        hb4.fill(new Point(3,6));
        hb4.fill(new Point(3,7));

        hb4.fill(new Point(1,7));
        hb4.fill(new Point(2,7));
        hb4.fill(new Point(2,8));

        hb4.fill(new Point(4,9));

        hb4.fill(new Point(4,7));
        hb4.fill(new Point(5,7));

        hb4.fill(new Point(6,3));
        hb4.fill(new Point(6,4));
        hb4.fill(new Point(6,5));
        hb4.fill(new Point(6,6));
        hb4.fill(new Point(6,7));
        hb4.fill(new Point(6,8));

        hb4.fill(new Point(8,3));
        hb4.fill(new Point(8,4));
        hb4.fill(new Point(8,5));
        hb4.fill(new Point(8,6));
        hb4.fill(new Point(8,7));

        hb4.fill(new Point(9,3));

        System.out.println(hb4);
        sp = new ShortestPath(hb4);
        start = new Node(1,4);
        end = new Node(8,0);
        test = sp.AStar(start, end);
        System.out.println(test);

        //---------------------------------


        HitBoard hb5 = new HitBoard(7);

        hb5.fill(new Point(2,1));
        hb5.fill(new Point(2,2));
        hb5.fill(new Point(2,3));
        hb5.fill(new Point(2,4));

        hb5.fill(new Point(3,4));
        hb5.fill(new Point(4,4));

        System.out.println(hb5);
        start = new Node(4,2);
        end = new Node(1,5);
        sp = new ShortestPath(hb5);
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
        res += " = " + this.getF();
        res += ")";
        return res;
    }
}


