package View.ItemsViews;

import Model.Layer1.Structures.Spaceship;
import View.Board.Tiles.SpaceshipTile;

public class SpaceshipView extends ItemView{

    public SpaceshipView(Spaceship spaceship) {
        this.tileView = new SpaceshipTile(spaceship);
    }
}
