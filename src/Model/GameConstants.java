package Model;

public class GameConstants {

    /** Hauteur du terrain de jeu */
    // 40 * 2^n
    public static final int BOARD_SIZE = 320;
    /** Dimension d'une montagne en nombre de case */
    public static final int MOUNTAIN_SIZE = 16;

    /** Rayon autour des SpaceMarines représentant la peur des Aliens*/
    public static final int fearOfSpaceMarines = 8;
    /** Rayon de vision des Aliens pour repérer une météorite*/
    public static final int alienRadar = 16;

    /** vitesses des éléments bougeant*/
    public static final int SpaceMarineSpeed = 75;
    public static final int AlienSpeed = 225;

    /** Hauteur du vaisseau (doit être pair et ≤ SPACESHIP_LANDING_ZONE - 2) */
    public static final int SPACESHIP_WIDTH = 6;
    /** Largeur du vaisseau (doit être pair et ≤ SPACESHIP_LANDING_ZONE - 2) */
    public static final int SPACESHIP_HEIGHT = 8;
    /** Dimension de la zone de recherche où apparait le vaisseau et les space marines
     * (doit être ≥ à SPACESHIP_WIDTH+2 & SPACESHIP_HEIGHT+2) */
    public static final int SPACESHIP_LANDING_ZONE = 16;

    /** Nombre de météorites placés sur la map au début d'une partie*/
    public static final int NBR_METEORITES=1;
}
