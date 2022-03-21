package View.ItemsViews;

import View.Board.Tiles.ItemTile;

public abstract class ItemView {

    ItemTile tileView;
    String description;

    public ItemTile getTileView() {
        return tileView;
    }
}
