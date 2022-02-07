package Model.Layer0;

import View.Tiles.LandView;

/**
 * Classe représentant une parcelle de terrain vague
 */
public class Land extends Parcel {

    /**
     * Constructeur
     */
    public Land() {
        super(true, new LandView());
    }
}
