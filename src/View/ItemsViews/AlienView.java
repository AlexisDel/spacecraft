package View.ItemsViews;

import Model.Layer1.Entities.Alien;
import View.Board.Tiles.AlienTile;

public class AlienView extends ItemView{

    public AlienView(Alien alien) {
        this.tileView = new AlienTile(alien);
    }
}
