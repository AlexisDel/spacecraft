package Game.Model.GameElements.Layer0;

import Game.Model.GameElements.GameElement;
import Game.View.Board.Views.ItemView;
import Game.View.Board.Views.MountainView;

import java.awt.*;

import static Game.Model.GameConstants.MOUNTAIN_SIZE;

/**
 * Classe représentant une parcelle de montagne
 */
public class Mountain extends GameElement {

    ItemView view;
    // Entier représentant le type de montagne pour l'affichage
    int type;

    /**
     * Constructeur
     */
    public Mountain(Point coordinate) {
        super(coordinate, new Dimension(MOUNTAIN_SIZE,MOUNTAIN_SIZE));
        setView(new MountainView(this));
        this.type = -1;
    }

    /**
     * constructeur avec type en paramètre
     * @param coordinate ses coordonnées
     * @param type son type
     */
    public Mountain(Point coordinate, int type) {
        super(coordinate, new Dimension(MOUNTAIN_SIZE,MOUNTAIN_SIZE));
        setView(new MountainView(this));
        this.type = type;
    }

    /**
     * getter
     * @return
     */
    public int getType() {
        return type;
    }
}
