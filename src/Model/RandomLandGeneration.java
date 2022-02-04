package Model;

import Model.GameSquare;
import Model.Squares.Containers.Land;
import Model.Squares.NotContainers.Mountain;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class RandomLandGeneration {
    private GameSquare[][] board;

    private static final int nbMountains = 5;
    private static int pourcent = 10;
    private final int borneMax;
    private final int borneMin;

    /**
     * constructeur de RandomLandGeneration
     * initialise les attributs privés puis génère un terrain aléatoire
     */
    public RandomLandGeneration() {
        int[] probs = this.probas(nbMountains, pourcent);
        this.borneMax = probs[1];
        this.borneMin = probs[0];
        this.board = new GameSquare[GameConstants.BOARD_HEIGHT][GameConstants.BOARD_WIDTH];
        // Pour chaque case du plateau
        for(int i = 0; i < GameConstants.BOARD_WIDTH; i++){
            for(int j = 0; j < GameConstants.BOARD_HEIGHT; j++){
                this.board[i][j] = new Land();
            }
        }
        this.randomStartingBoard();
    }

    /**
     * fonction renvoyant les paramètres pour les probabilités lors de la génération des montagnes
     *
     * @param nbMountains le nombre de chaînes de mantagnes souhaitées
     * @param pourcent    la proportion moyenne de montagne sur la map (en pourcentage, donc inferieur à 100)
     * @return un tableau à 2 dimensions
     */
    int[] probas(int nbMountains, int pourcent) {
        if (pourcent > 100) {
            assert false;
        }
        int[] res = new int[2];
        res[0] = pourcent*GameConstants.BOARD_HEIGHT * GameConstants.BOARD_WIDTH - 100*nbMountains;
        res[1] = pourcent*GameConstants.BOARD_HEIGHT * GameConstants.BOARD_WIDTH;
        return res;
    }

    /**
     * ajoute une entitée au plateau
     *
     * @param GameSquare
     * @param pos        toDo : check que la case soit vide
     */
    public void addGameSquare(GameSquare GameSquare, Point pos) {
        this.board[pos.x][pos.y] = GameSquare;
    }

    public boolean isInBoard(Point p) {
        return p.x < GameConstants.BOARD_HEIGHT && p.y < GameConstants.BOARD_WIDTH && p.x >= 0 && p.y >= 0;
    }

    /**
     * méthode privée générant un plateau de départ.
     * toDo : améliorer la génération des reliefs pour faire structures plus intéressantes
     */
    private void randomStartingBoard() {
        // Tableau de couleur sur lequel on va travailler pour la génération du terrain:
        ArrayList<ArrayList<Color.color>> mountains = new ArrayList<>(GameConstants.BOARD_HEIGHT);
        for (int i = 0; i < GameConstants.BOARD_HEIGHT; i++) {
            mountains.add(new ArrayList<Color.color>(GameConstants.BOARD_WIDTH));
            for (int j = 0; j < GameConstants.BOARD_WIDTH; j++) {
                mountains.get(i).add(Color.color.notseen);
            }
        }

        // Le contour est une grande montagne
        for(int i = 0; i < GameConstants.BOARD_HEIGHT; i++){
            mountains.get(i).set(0, Color.color.mountain);
            mountains.get(i).set(GameConstants.BOARD_WIDTH - 1, Color.color.mountain);
        }
        for(int i = 0; i < GameConstants.BOARD_WIDTH; i++){
            mountains.get(0).set(i, Color.color.mountain);
            mountains.get(GameConstants.BOARD_HEIGHT - 1).set(i, Color.color.mountain);
        }
        // On veut ensuite générer aléatoirement un relief dans la map

        // Génération d'un objet random
        Random rand = new Random();

        int newX, newY;

        for (int i = 0; i < nbMountains; i++) {
            newX = rand.nextInt(GameConstants.BOARD_HEIGHT);
            newY = rand.nextInt(GameConstants.BOARD_WIDTH);
            // Le relief doit être à une case vide
            while (!(this.board[newX][newY] instanceof Land)) {
                newX = rand.nextInt(GameConstants.BOARD_HEIGHT);
                newY = rand.nextInt(GameConstants.BOARD_WIDTH);
            }
            System.out.println(new Point(newX, newY));
            mountains = this.generateMountain(new Point(newX, newY), mountains);
        }

        // Appel à la fonction clear mountains qui rend le graph formé par la grille convexe.
        this.clearMountains(mountains);

        // On applique à board la génération effectuée

        for (int i = 0; i < GameConstants.BOARD_HEIGHT; i++) {
            for (int j = 0; j < GameConstants.BOARD_WIDTH; j++) {
                if (mountains.get(i).get(j).equals(Color.color.mountain)) {
                    this.addGameSquare(new Mountain(), new Point(i, j));
                }
            }
        }

        // Génération aléatoire de la position du space ship
//        int shipX = rand.nextInt(GameConstants.BOARD_HEIGHT);
        //      int shipY = rand.nextInt(GameConstants.BOARD_WIDTH);
        //    while (!(this.board[shipX][shipY] == null)) {
        //      shipX = rand.nextInt(GameConstants.BOARD_HEIGHT);
        //    shipY = rand.nextInt(GameConstants.BOARD_WIDTH);
        //}
        //this.addGameSquare(new SpaceShip(), new Point(shipX, shipY));
        // On ajoute 3 space marines, à max 2 cases de distance du space ship
        //for (int i = 0; i < 4; i++) {
        //  newX = shipX - 2 + rand.nextInt(4);
        //newY = shipY - 2 + rand.nextInt(4);
        //la nouvel GameSquare doit être dans le tableau et a une case vide
        //while (!isInBoard(new Point(newX, newY)) || !(this.board[newX][newY] == null)) {
        //  newX = shipX - 2 + rand.nextInt(4);
        //newY = shipY - 2 + rand.nextInt(4);
        //}
        //this.addGameSquare(new Character(), new Point(newX, newY));
        //}
    }

    /**
     * fonction renvoyant la liste des cases vides et dans la grille, autour de la case de coordonnée pos
     *
     * @param pos
     * @return
     */
    public ArrayList<Point> getEmptyNeighbor(Point pos) {
        ArrayList<Point> res = new ArrayList<Point>(0);
        Point temp = pos;
        temp.translate(0, -1);
        if (isInBoard(temp) && this.board[temp.x][temp.y] instanceof Land) {
            res.add((Point) temp.clone());
        }
        temp.translate(1, 1);
        if (isInBoard(temp) && this.board[temp.x][temp.y] instanceof Land) {
            res.add((Point) temp.clone());
        }
        temp.translate(-1, 1);
        if (isInBoard(temp) && this.board[temp.x][temp.y] instanceof Land) {
            res.add((Point) temp.clone());
        }
        temp.translate(-1, -1);
        if (isInBoard(temp) && this.board[temp.x][temp.y] instanceof Land) {
            res.add((Point) temp.clone());
        }
        return res;
    }

    /**
     * fonction ajoutant une montagne avec un certaine probabilité à un endroit donné.
     *
     * @param pos
     * @param mountains
     * @return ArrayList<ArrayList < Color>>
     */
    public ArrayList<ArrayList<Color.color>> generateMountain(Point pos, ArrayList<ArrayList<Color.color>> mountains) {
        // On veut créer une montagne autour du point pos
        mountains.get(pos.x).set(pos.y, Color.color.mountain);
        boolean flag = true;
        Random rand = new Random();
        int shot = rand.nextInt(this.borneMax);
        if (shot  <= this.borneMin) {
            ArrayList<Point> neighbors = getEmptyNeighbor(pos);
            int tmp = neighbors.size();
            if (tmp > 0) {
                mountains = this.generateMountain(neighbors.get(rand.nextInt(tmp)), mountains);
            }
        }
        else {
        }
        return mountains;
    }

    /**
     * fonction rendant fortement connexe le graph
     *
     * @param mountains
     */

    public void clearMountains(ArrayList<ArrayList<Color.color>> mountains) {
        Random rand = new Random();
        // Coloration du tableau mountains
        // Corp de cette fonction. Quand on trouve un point non vue, on le relie à un point vue et on recommanbce à 0.
        this.coloration(mountains);
        for (int i = 0; i < GameConstants.BOARD_HEIGHT; i++) {
            for (int j = 0; j < GameConstants.BOARD_WIDTH; j++) {
                if (mountains.get(i).get(j).equals(Color.color.notseen)) {
                    Point toJoin = this.nearestSeenTile(mountains, new Point(i, j));
                    this.dig(mountains, new Point(i, j), toJoin);
                    this.coloration(mountains);
                }
            }
        }
    }

    /**
     * fonction de coloration dui graph
     * @param mountains
     */
    public void coloration(ArrayList<ArrayList<Color.color>> mountains){
        boolean flag = true;
        for(int i = 0; flag&&(i < GameConstants.BOARD_HEIGHT); i++){
            for(int j = 0; flag&&(j < GameConstants.BOARD_WIDTH); j++){
                if(mountains.get(i).get(j).equals(Color.color.notseen)){
                    mountains.get(i).set(i, Color.color.seen);
                    flag = false;
                }
            }
        }
        heuristiqueColoration(mountains);
        Random rand = new Random();
        Point colorStartingPoint = new Point(rand.nextInt(GameConstants.BOARD_HEIGHT), rand.nextInt(GameConstants.BOARD_WIDTH));
        // On veut que le point de départ de la coloration soit pas une montagne
        while (mountains.get(colorStartingPoint.x).get(colorStartingPoint.y) == Color.color.mountain) {
            colorStartingPoint = new Point(rand.nextInt(GameConstants.BOARD_HEIGHT), rand.nextInt(GameConstants.BOARD_WIDTH));
        }
        System.out.println("search");
        path(mountains, colorStartingPoint);
        System.out.println("done");
    }

    /**
     * fonction parcourant le graph de la map à partir d'un point donné, et colorant toutes les cases parcourues.
     * @param mountains
     * @param point
     */
    public void path(ArrayList<ArrayList<Color.color>> mountains, Point point) {
        if (mountains.get(point.x).get(point.y).equals(Color.color.notseen)) {
            mountains.get(point.x).set(point.y, Color.color.seen);
        }
        for (Point p : getEmptyNeighbor(point)) {
            if (mountains.get(p.x).get(p.y).equals(Color.color.notseen)) {
                mountains.get(p.x).set(p.y, Color.color.seen);
                path(mountains, p);
            }
        }
    }

    /**
     * heuristique colorant une bonne partie des cases permettant le dfs sans stackoverflow
     * @param mountains
     */
    private void  heuristiqueColoration(ArrayList<ArrayList<Color.color>> mountains){
        // On parcours le tableau lignes par lignes de gauche à droite
        for(int i = 1; i < GameConstants.BOARD_HEIGHT - 1; i++){
            for(int j = 1; j < GameConstants.BOARD_WIDTH - 1; j++){
                if(mountains.get(i).get(j).equals(Color.color.seen)){
                    if(isInBoard(new Point(i + 1, j)) && mountains.get(i + 1).get(j).equals(Color.color.notseen)){
                        mountains.get(i + 1).set(j, Color.color.seen);
                    }
                    if(isInBoard(new Point(i, j + 1)) && mountains.get(i).get(j + 1).equals(Color.color.notseen)){
                        mountains.get(i).set(j + 1, Color.color.seen);
                    }
                    if(isInBoard(new Point(i - 1, j)) && mountains.get(i - 1).get(j).equals(Color.color.notseen)){
                        mountains.get(i - 1).set(j, Color.color.seen);
                    }
                    if(isInBoard(new Point(i, j - 1)) && mountains.get(i).get(j - 1).equals(Color.color.notseen)){
                        mountains.get(i).set(j - 1, Color.color.seen);
                    }
                }
            }
        }
        // On parcours le tableau colones par colones de haut en bas
        for(int j = 1; j < GameConstants.BOARD_HEIGHT - 1; j++){
            for(int i = 1; i < GameConstants.BOARD_WIDTH - 1; i++){
                if(mountains.get(i).get(j).equals(Color.color.seen)){
                    if(isInBoard(new Point(i + 1, j)) && mountains.get(i + 1).get(j).equals(Color.color.notseen)){
                        mountains.get(i + 1).set(j, Color.color.seen);
                    }
                    if(isInBoard(new Point(i, j + 1)) && mountains.get(i).get(j + 1).equals(Color.color.notseen)){
                        mountains.get(i).set(j + 1, Color.color.seen);
                    }
                    if(isInBoard(new Point(i - 1, j)) && mountains.get(i - 1).get(j).equals(Color.color.notseen)){
                        mountains.get(i - 1).set(j, Color.color.seen);
                    }
                    if(isInBoard(new Point(i, j - 1)) && mountains.get(i).get(j - 1).equals(Color.color.notseen)){
                        mountains.get(i).set(j - 1, Color.color.seen);
                    }
                }
            }
        }
        // On parcours le tableau lignes par lignes de droite à gauche
        for(int i = GameConstants.BOARD_HEIGHT - 2; i > 0 ; i--){
            for(int j = GameConstants.BOARD_WIDTH - 2; j > 0; j--){
                if(mountains.get(i).get(j).equals(Color.color.seen)){
                    if(isInBoard(new Point(i + 1, j)) && mountains.get(i + 1).get(j).equals(Color.color.notseen)){
                        mountains.get(i + 1).set(j, Color.color.seen);
                    }
                    if(isInBoard(new Point(i, j + 1)) && mountains.get(i).get(j + 1).equals(Color.color.notseen)){
                        mountains.get(i).set(j + 1, Color.color.seen);
                    }
                    if(isInBoard(new Point(i - 1, j)) && mountains.get(i - 1).get(j).equals(Color.color.notseen)){
                        mountains.get(i - 1).set(j, Color.color.seen);
                    }
                    if(isInBoard(new Point(i, j - 1)) && mountains.get(i).get(j - 1).equals(Color.color.notseen)){
                        mountains.get(i).set(j - 1, Color.color.seen);
                    }
                }
            }
        }
        // On parcours le tableau colones par colones de bas en haut
        for(int j = GameConstants.BOARD_HEIGHT - 2; j > 0 ; j--){
            for(int i = GameConstants.BOARD_WIDTH - 2; i > 0 ; i--){
                if(mountains.get(i).get(j).equals(Color.color.seen)){
                    if(isInBoard(new Point(i + 1, j)) && mountains.get(i + 1).get(j).equals(Color.color.notseen)){
                        mountains.get(i + 1).set(j, Color.color.seen);
                    }
                    if(isInBoard(new Point(i, j + 1)) && mountains.get(i).get(j + 1).equals(Color.color.notseen)){
                        mountains.get(i).set(j + 1, Color.color.seen);
                    }
                    if(isInBoard(new Point(i - 1, j)) && mountains.get(i - 1).get(j).equals(Color.color.notseen)){
                        mountains.get(i - 1).set(j, Color.color.seen);
                    }
                    if(isInBoard(new Point(i, j - 1)) && mountains.get(i).get(j - 1).equals(Color.color.notseen)){
                        mountains.get(i).set(j - 1, Color.color.seen);
                    }
                }
            }
        }
    }

    /**
     * fonction renvoyant la premiere case déjà vue trouvée autour du point de départ
     *
     * @param mountains
     * @param point
     * @return
     */
    public Point nearestSeenTile(ArrayList<ArrayList<Color.color>> mountains, Point point) {
        // Point résultat (premier point autour de point déjà vue.
        Point res = (Point) point.clone();
        res.translate(0, -2);
        while (true) {
            while (res.y != point.y) {
                res.translate(-1, 1);
                if (isInBoard(res) && mountains.get(res.x).get(res.y).equals(Color.color.seen)) {
                    return res;
                }
            }
            while (res.x != point.x) {
                res.translate(1, 1);
                if (isInBoard(res) && mountains.get(res.x).get(res.y).equals(Color.color.seen)) {
                    return res;
                }
            }
            while (res.y != point.y) {
                res.translate(1, -1);
                if (isInBoard(res) && mountains.get(res.x).get(res.y).equals(Color.color.seen)) {
                    return res;
                }
            }
            while (res.x != point.x) {
                res.translate(-1, -1);
                if (isInBoard(res) && mountains.get(res.x).get(res.y).equals(Color.color.seen)) {
                    return res;
                }
            }
            res.translate(0, -1);
        }
    }

    /**
     * fonction reliant deux points de la map en creusant les montagnes
     *
     * @param mountains
     * @param start
     * @param end       todo : faire une meilleure fonction de reliemant
     */
    public void dig(ArrayList<ArrayList<Color.color>> mountains, Point start, Point end) {
        for (int i = Math.min(start.x, end.x); i < Math.max(start.x, end.x); i++) {
            mountains.get(i).set(start.y, Color.color.seen);
        }
        for (int i = Math.min(start.y, end.y); i < Math.max(start.y, end.y); i++) {
            mountains.get(end.x).set(i, Color.color.seen);
        }
    }

    /**
     * getter de this.board : une terrain généré aléatoirement
     *
     * @return
     */
    public GameSquare[][] getBoard() {
        return board;
    }
}

    class Color {
        enum color {notseen, seen, mountain}

        color c;

        // Constructor
        public Color(color c) {
            this.c = c;
        }
    }




