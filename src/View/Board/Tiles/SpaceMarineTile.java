package View.Board.Tiles;

import Model.Layer1.Entities.SpaceMarine;

public class SpaceMarineTile extends ItemTile {

    SpaceMarine spaceMarine;

    public SpaceMarineTile(SpaceMarine spaceMarine) {
        super(spaceMarine);
        this.spaceMarine = spaceMarine;
    }

    @Override
    public String getImageName() {
        return super.getImageName()+spaceMarine.getDirection().toString();
    }
}