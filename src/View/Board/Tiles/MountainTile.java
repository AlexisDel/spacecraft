package View.Board.Tiles;

import Model.Layer0.Mountain;

import java.awt.*;

import static View.ViewConstants.TILE_SIZE;

public class MountainTile extends ItemTile {

    Mountain mountain;

    public MountainTile(Mountain mountain) {
        super(mountain);
        this.mountain = mountain;
    }


    @Override
    public String getImageName() {
        return super.getImageName()+"_"+mountain.getType();
    }
}
