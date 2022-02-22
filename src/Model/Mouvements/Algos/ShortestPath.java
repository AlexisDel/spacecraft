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
    public ArrayList<Point> AStar(Node start, Node end){
        // Initialisation de start
        start.setH(heuristic(start, end));
        start.setG(0);
        start.setF(start.getG() + start.getH());

        // Initialisation de Open et Close
        BinaryHeap Open = new BinaryHeap(100000);
        BinaryHeap Close = new BinaryHeap(100000);
        Open.insert(start);
        // Tant que la recherche continue
        int cpt = 0;
        while(!Open.isEmpty()){
            cpt++;
            // On cherche le noeud de plus petite fvalue dans open : currentNode
            Node currentNode = Open.findMin();
            // On retire currentNode de Open
            Open.delete(0);
            // Si on trouve l'arrivée, alors on arrête
            if(currentNode.equals(end)){
                end = currentNode;
                break;
            }
            // On ajoute le noeud courrant à close
            Close.insert(currentNode);
            for(Node child : currentNode.getChild(this.hitbox)){
                double cost = currentNode.getG() + 1;
                // Pose pour potentiellement retirer l'enfant des listes
                int posOpen = 0;
                boolean notInOpen = true;
                boolean notInClose = true;
                ///////////////////////////////////////////////
                // Si child est dans la liste Open, et à une plus grande G-value, on l'enlève de Open
                boolean flag1 = true;
                for(int i = 0; i < Open.getHeapSize(); i++){
                    if(Open.getHeap()[i].equals(child)){
                        notInOpen = false;
                        if(Open.getHeap()[i].getG() > cost){
                            posOpen = i;
                            flag1 = false;
                        }
                    }
                }
                if(!flag1) {
                    Open.delete(posOpen);
                    notInOpen = true;
                }
                //////////////////////////////////////////////
                // On regarde si child est dans Close
                for(int i = 0; i < Close.getHeapSize(); i++){
                    if(Close.getHeap()[i].equals(child)){
                        notInClose = false;
                        break;
                    }
                }
                //////////////////////////////////////////////
                // Si l'enfant est ni dans Open ni dans Close
                if(notInOpen && notInClose){
                    // On calcul les heuristiques de child
                    child.setH(this.heuristic(child, end));
                    child.setG(currentNode.getG() + 1);
                    child.setF(child.getH() + child.getH());
                    // On fixe le parent
                    child.setParent(currentNode);
                    Open.insert(child);
                }
            }
        }
        System.out.println("nombre de tours : " + cpt);
        return this.backtrack(start, end);
    }

    /**
     * backtracking
     * @param start
     * @param end
     * @return
     */
    private ArrayList<Point> backtrack(Node start, Node end){
        ArrayList<Point> res = new ArrayList<>();
        Node currentNode = end;
        while(!currentNode.equals(start)){
            res.add(new Point(currentNode.getPosx(), currentNode.getPosy()));
            currentNode = currentNode.getParent();
        }
        res.add(new Point(start.getPosx(), start.getPosy()));
        return res;
    }


    public Direction nextMove(ArrayList<Point> track, int i){
        assert track.size() != 0;
        if(track.size() == 1){
            return Direction.NULL;
        }
        if(i == 0){
            return Direction.NULL;
        }
        else{
            Point delta = new Point(track.get(track.size() - 1 - i).x - track.get(track.size() - i).x,track.get(track.size() - 1 - i).y - track.get(track.size() - i).y);
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

        int n = 2;

        HitBoard hb1 = new HitBoard(n);
        hb1.fill(new Point(1,1));
        System.out.println(hb1);

        ShortestPath sp = new ShortestPath(hb1);
        Node start = new Node(0,0);
        Node end = new Node(1,0);
        ArrayList<Point>test = sp.AStar(start, end);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(test.contains(new Point(i, j))){
                    System.out.print("C ");
                }
                else if(hb1.isEmpty(i, j)){
                    System.out.print(". ");
                }
                else{
                    System.out.print("M ");
                }
            }
            System.out.println();
        }
        System.out.println("/////////////////////////");

        //--------------------------------------------

        n = 4;
        HitBoard hb2 = new HitBoard(n);
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
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(test.contains(new Point(i, j))){
                    System.out.print("C ");
                }
                else if(hb2.isEmpty(i, j)){
                    System.out.print(". ");
                }
                else{
                    System.out.print("M ");
                }
            }
            System.out.println();
        }
        System.out.println("////////////////////////");

        //--------------------------------------------

        n = 2;

        HitBoard hb3 = new HitBoard(n);
        System.out.println(hb3);
        sp = new ShortestPath((hb3));
        start=  new Node(0,0);
        end = new Node(0,0);
        test = sp.AStar(start, end);
        System.out.println(test);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(test.contains(new Point(i, j))){
                    System.out.print("C ");
                }
                else if(hb3.isEmpty(i, j)){
                    System.out.print(". ");
                }
                else{
                    System.out.print("M ");
                }
            }
            System.out.println();
        }
        System.out.println("////////////////////////");

        //--------------------------------------------

        n = 10;

        HitBoard hb4 = new HitBoard(n);
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
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(test.contains(new Point(i, j))){
                    System.out.print("C ");
                }
                else if(hb4.isEmpty(i, j)){
                    System.out.print(". ");
                }
                else{
                    System.out.print("M ");
                }
            }
            System.out.println();
        }
        System.out.println("////////////////");

        //---------------------------------

        n = 7;

        HitBoard hb5 = new HitBoard(n);

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
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(test.contains(new Point(i, j))){
                    System.out.print("C ");
                }
                else if(hb5.isEmpty(i, j)){
                    System.out.print(". ");
                }
                else{
                    System.out.print("M ");
                }
            }
            System.out.println();
        }


    }



}
