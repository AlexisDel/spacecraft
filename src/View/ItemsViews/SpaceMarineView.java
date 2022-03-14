package View.ItemsViews;

import Model.Layer1.Entities.SpaceMarine;
import View.Board.Tiles.SpaceMarineTile;

public class SpaceMarineView extends ItemView {

    public SpaceMarineView(SpaceMarine spaceMarine) {
        this.tileView = new SpaceMarineTile(spaceMarine);
    }
}
