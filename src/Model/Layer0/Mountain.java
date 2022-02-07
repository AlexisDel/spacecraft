package Model.Layer0;

import View.Tiles.MountainView;

/**
 * Classe représentant une parcelle de montagne
 */
public class Mountain extends Parcel {

    /**
     * Constructeur
     */
    public Mountain() {
        super(false, new MountainView());
    }
}
