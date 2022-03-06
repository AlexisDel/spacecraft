package View.ItemsViews;

import Model.Layer1.Structures.Meteorite;
import View.Board.Tiles.MeteoriteTile;

public class MeteoriteView extends ItemView{

    public MeteoriteView(Meteorite meteorite) {
        this.tileView = new MeteoriteTile(meteorite);
    }
}