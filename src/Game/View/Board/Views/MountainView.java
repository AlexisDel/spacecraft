package Game.View.Board.Views;

import Game.Model.GameElements.Layer0.Mountain;

public class MountainView extends ItemView {

    Mountain mountain;

    public MountainView(Mountain mountain) {
        super(mountain);
        this.mountain = mountain;
    }


    @Override
    public String getImageName() {
        return super.getImageName()+"_"+mountain.getType();
    }
}
