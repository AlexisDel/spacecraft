package Model;

import Model.Layer0.Mountain;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static Model.GameConstants.MOUNTAIN_SIZE;

public class RandomLandGeneration {
    private final ArrayList<Mountain> board;

    private static final int nbMountains = 4;
    private static int pourcent = 20;
    private final int borneMax;
    private final int borneMin;
    private final int dimH =  (GameConstants.BOARD_SIZE/ MOUNTAIN_SIZE);
    private final int dimW =  (GameConstants.BOARD_SIZE/ MOUNTAIN_SIZE);
    private final Random rand = new Random();


    /**
     * constructeur de RandomLandGeneration
     * initialise les attributs privés puis génère un terrain aléatoire
     */
    public RandomLandGeneration() {
        // Calcul des bornes pour les probabilités
        int[] probs = this.probas(pourcent);
        this.borneMax = probs[1];
        this.borneMin = probs[0];
        this.board = new ArrayList<>();

        // Génération de la seed :
        long seed = this.rand.nextLong();
        //this.rand.setSeed(137495780117517325L);
        this.rand.setSeed(seed);
        System.out.println("la seed de la partie est " + seed);

        this.randomStartingBoard();
    }

    /**
     * fonction renvoyant les paramètres pour les probabilités lors de la génération des montagnes
     *
     * @param pourcent    la proportion moyenne de montagne sur la map (en pourcentage, donc inferieur à 100)
     * @return un tableau à 2 dimensions
     */
    int[] probas(int pourcent) {
        assert pourcent <= 100;
        int[] res = new int[2];
        res[0] = pourcent*dimH*dimW - 100* RandomLandGeneration.nbMountains;
        res[1] = pourcent*dimH*dimW;
        return res;
    }

    /**
     * renvoie vrai ssi un point est dans la grille
     * @param p
     * @return
     */
    public boolean isInBoard(Point p) {
        return p.x < dimH && p.y < dimW && p.x >= 0 && p.y >= 0;
    }

    /**
     * méthode privée générant un plateau de départ.
     * toDo : améliorer la génération des reliefs pour faire structures plus intéressantes
     */
    private void randomStartingBoard() {
        // Tableau de couleur sur lequel on va travailler pour la génération du terrain:
        ArrayList<ArrayList<Color.color>> mountains = new ArrayList<>(dimW);
        for (int i = 0; i < dimH; i++) {
            mountains.add(new ArrayList<Color.color>(dimW));
            for (int j = 0; j < dimW; j++) {
                mountains.get(i).add(Color.color.notseen);
            }
        }
        // Le contour est une grande montagne
        for(int i = 0; i < dimH; i++){
            mountains.get(i).set(0, Color.color.mountain);
            mountains.get(i).set(dimW- 1, Color.color.mountain);
        }
        for(int i = 0; i < dimW; i++){
            mountains.get(0).set(i, Color.color.mountain);
            mountains.get(dimH - 1).set(i, Color.color.mountain);
        }
        // On veut ensuite générer aléatoirement un relief dans la map

        int newX, newY;
        for (int i = 0; i < nbMountains; i++) {
            newX = this.rand.nextInt(dimH - 2) + 1;
            newY = this.rand.nextInt(dimW - 2) + 1;
            // Le relief doit être à une case vide
            while (!(mountains.get(newX).get(newY).equals(Color.color.notseen))) {
                newX = this.rand.nextInt(dimH - 2) + 1;
                newY = this.rand.nextInt(dimW - 2) + 1;
            }
            mountains = this.generateMountain(new Point(newX, newY), mountains);
        }
        // Appel à la fonction clear mountains qui rend le graph formé par la grille convexe.
        this.clearMountains(mountains);
        // On applique à board la génération effectuée

        for (int i = 0; i < dimH; i++) {
            for (int j = 0; j < dimW; j++) {
                if (mountains.get(i).get(j).equals(Color.color.mountain)) {
                    this.board.add(new Mountain(new Point((int) (i* MOUNTAIN_SIZE), (int) (j* MOUNTAIN_SIZE))));
                }
            }
        }
    }

    /**
     * fonction renvoyant la liste des cases vides et dans la grille, autour de la case de coordonnée pos
     *
     * @param pos
     * @return
     */
    public ArrayList<Point> getEmptyNeighbor(Point pos, ArrayList<ArrayList<Color.color>> mountains) {
        ArrayList<Point> res = new ArrayList<Point>(0);
        Point temp = pos;
        temp.translate(0, -1);
        if (isInBoard(temp) && !mountains.get(temp.x).get(temp.y).equals(Color.color.mountain)) {
            res.add((Point) temp.clone());
        }
        temp.translate(1, 1);
        if (isInBoard(temp) && !mountains.get(temp.x).get(temp.y).equals(Color.color.mountain)) {
            res.add((Point) temp.clone());
        }
        temp.translate(-1, 1);
        if (isInBoard(temp) && !mountains.get(temp.x).get(temp.y).equals(Color.color.mountain)) {
            res.add((Point) temp.clone());
        }
        temp.translate(-1, -1);
        if (isInBoard(temp) && !mountains.get(temp.x).get(temp.y).equals(Color.color.mountain)) {
            res.add((Point) temp.clone());
        }
        return res;
    }

    public ArrayList<Point> getNeighbor(Point pos, ArrayList<ArrayList<Color.color>> mountains){
        ArrayList<Point> res = new ArrayList<Point>(0);
        Point temp = pos;
        temp.translate(0, -1);
        if (isInBoard(temp)) {
            res.add((Point) temp.clone());
        }
        temp.translate(1, 1);
        if (isInBoard(temp)) {
            res.add((Point) temp.clone());
        }
        temp.translate(-1, 1);
        if (isInBoard(temp)) {
            res.add((Point) temp.clone());
        }
        temp.translate(-1, -1);
        if (isInBoard(temp)) {
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
     *
     */
    public ArrayList<ArrayList<Color.color>> generateMountain(Point pos, ArrayList<ArrayList<Color.color>> mountains) {
        // On veut créer une montagne autour du point pos
        mountains.get(pos.x).set(pos.y, Color.color.mountain);
        int shot = this.rand.nextInt(this.borneMax);
        // Si le tirage est inferieur à borneMin, alors bingo, on génère une montagne.
        if (shot  <= this.borneMin) {
            ArrayList<Point> emptyNeighbors = getEmptyNeighbor(pos, mountains);
            int tmp = emptyNeighbors.size();
            // Si la montagne générée à au moins un voisin libre, on sélectionne parmis les voisins le successeur pour la génération
            if (tmp > 0) {
                mountains = this.generateMountain(emptyNeighbors.get(this.rand.nextInt(tmp)), mountains);
            }
            // Sinon, on transmet l'information qu'il faut ajouter une montagne
            else{
                Point newMountain = nearestColoredTile(mountains, pos, Color.color.notseen);
                mountains = this.generateMountain(newMountain, mountains);
            }
        }
        return mountains;
    }

    /**
     * fonction rendant fortement connexe le graph
     *
     * @param mountains
     */

    public void clearMountains(ArrayList<ArrayList<Color.color>> mountains) {
        Point colorStartingPoint = new Point(0,0);
        // Coloration du tableau mountains
        // Corp de cette fonction. Quand on trouve un point non vue, on le relie à un point vue et on recommanbce à 0.
        // On commence par colorier une première case :
        boolean flag = true;
        for(int i = 0; flag&&(i < dimH); i++){
            for(int j = 0; flag&&(j < dimW); j++){
                if(mountains.get(i).get(j).equals(Color.color.notseen)){
                    mountains.get(i).set(j, Color.color.seen);
                    colorStartingPoint = new Point(i, j);
                    flag = false;
                }
            }
        }
        // Puis on colorie une première fois
        this.coloration(mountains, colorStartingPoint);
        // On parcours le tableau coloré
        for (int i = 0; i < dimH; i++) {
            for (int j = 0; j < dimW; j++) {
                // Si on trouve une case non colorée:
                if (mountains.get(i).get(j).equals(Color.color.notseen)) {
                    // On trouve une case colorée proche
                    Point toJoin = this.nearestColoredTile(mountains, new Point(i, j), Color.color.seen);
                    // On relie ces deux cases
                    this.dig(mountains, new Point(i, j), toJoin);
                    // On recolore
                    this.coloration(mountains, new Point(i, j));
                }
            }
        }
    }

    /**
     * fonction de coloration du graph
     * @param mountains
     * @param colorStartingPoint
     */
    public void coloration(ArrayList<ArrayList<Color.color>> mountains, Point colorStartingPoint){
        // appel à l'heuristique de coloration pour éviter au plus les stacks overflow lors de l'appel à path
        heuristiqueColoration(mountains);
        path(mountains, colorStartingPoint);
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
        for (Point p : getEmptyNeighbor(point, mountains)) {
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
        for(int i = 1; i < dimH - 1; i++){
            for(int j = 1; j < dimH - 1; j++){
                // Si une case a été vue
                if(mountains.get(i).get(j).equals(Color.color.seen)){
                    // On colorie si besoin chacun de ses voisins
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
        for(int j = 1; j < dimH - 1; j++){
            for(int i = 1; i < dimW - 1; i++){
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
        for(int i = dimH - 2; i > 0 ; i--){
            for(int j = dimW - 2; j > 0; j--){
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
        for(int j = dimH - 2; j > 0 ; j--){
            for(int i = dimW - 2; i > 0 ; i--){
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
     * fonction renvoyant la premiere case colorée trouvée autour du point de départ suivant un BFS
     * @param mountains le tableau d'informations
     * @param point le point de départ
     * @param color la couleur
     * @return le point où se trouve l'information recherchée
     */
    public Point nearestColoredTile(ArrayList<ArrayList<Color.color>> mountains, Point point, Color.color color) {
        // Point résultat (premier point autour de point déjà vue.
        Point res = (Point) point.clone();
        // On choisis aléatoirement le point de départ et on adapte le parcours en fonction
        int parcours = this.rand.nextInt(4);
        int dx;
        int dy;
        int evolx;
        int evoly;
        if(parcours == 0){
            evolx = 0;
            evoly = -1;
            dx = -1;
            dy = 1;
        }
        else if(parcours == 1){
            evolx = 0;
            evoly = 1;
            dx = 1;
            dy = -1;
        }
        else if(parcours == 2){
            evolx = 1;
            evoly = 0;
            dx = -1;
            dy = -1;
        }
        else{
            evolx = -1;
            evoly = 0;
            dx = 1;
            dy = 1;
        }
        res.translate(evolx, evoly);
        // Tant qu'on a pas trouvé ce qu'on cherche :
        while (true) {
            int tmp;
            // On veut faire des déplacement dans les 4 directions pour boucler, on exécute donc 2 fois le code ci-dessous
            for(int i = 0; i < 2; i++){
                // Si on est sur le parcours 0 ou 1, on compare les x, sinon, on compare les y
                while(((parcours == 0 || parcours == 1) && res.y != point.y) || ((parcours == 2 || parcours == 3) && res.x != point.x)){
                    res.translate(dx, dy);
                    // Si on trouve ce qu'on cherche
                    if (isInBoard(res) && mountains.get(res.x).get(res.y).equals(color)) {
                        // On renvoie la position
                        return res;
                    }
                }
                // On calcul le vecteur normal au déplacement précédent pour changer de direction
                tmp = dx;
                dx = dy;
                dy = -tmp;
                // Si on est sur le parcours 0 ou 1 on compare les y, sinon on compare les x
                while(((parcours == 0 || parcours == 1) && res.x != point.x) || ((parcours == 2 || parcours == 3) && res.y != point.y)){
                    res.translate(dx, dy);
                    // Si on trouve ce qu'on cherche
                    if (isInBoard(res) && mountains.get(res.x).get(res.y).equals(color)) {
                        // On renvoie la position
                        return res;
                    }
                }
                // On calcul le vecteur normal au déplacement précédent pour changer de direction
                tmp = dx;
                dx = dy;
                dy = -tmp;
            }
            // On effectue la translation de départ pour faire évoluer la profondeur.
            res.translate(evolx, evoly);
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
        for (int i = Math.min(start.x, end.x); i < Math.max(start.x, end.x) +  1; i++) {
            mountains.get(i).set(start.y, Color.color.seen);
        }
        for (int i = Math.min(start.y, end.y); i < Math.max(start.y, end.y) + 1; i++) {
            mountains.get(end.x).set(i, Color.color.seen);
        }
    }

    /**
     * getter de this.board : une terrain généré aléatoirement
     *
     * @return
     */
    public ArrayList<Mountain> getBoard() {
        return board;
    }

    /**
     * fonction d'affichage de la grille générée pour le debbogage
     * @param mountains
     */
    private void printMountains(ArrayList<ArrayList<Color.color>> mountains){
        System.out.println("grille de montagnes générée : ");
        for (int i = 0; i < dimH; i++) {
            for (int j = 0; j < dimW; j++) {
                // Si on trouve une case non colorée:
                if (mountains.get(j).get(i).equals(Color.color.notseen)) {
                    System.out.print("x");
                }
                // Si on trouve une case colorée
                if (mountains.get(j).get(i).equals(Color.color.seen)) {
                    System.out.print("o");
                }
                // Si on trouve une montagne
                if (mountains.get(j).get(i).equals(Color.color.mountain)) {
                    System.out.print("M");
                }
            }
            System.out.println("");
        }
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




