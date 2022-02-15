package View.ItemsViews;

import Model.Layer0.Mountain;
import View.Board.Tiles.MountainTile;

public class MountainView extends ItemView{

    public MountainView(Mountain mountain) {
        this.tileView = new MountainTile(mountain);
    }
}
