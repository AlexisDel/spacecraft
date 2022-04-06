package Game.View.Board.Views;

import Game.Model.GameElements.Layer1.Entities.SpaceMarine;

public class SpaceMarineView extends ItemView {

    SpaceMarine spaceMarine;

    public SpaceMarineView(SpaceMarine spaceMarine) {
        super(spaceMarine);
        this.spaceMarine = spaceMarine;
    }

    @Override
    public String getImageName() {
        return super.getImageName()+spaceMarine.getDirection().toString();
    }
}