package View.Board.Tiles;

import Model.Layer1.Entities.Alien;

public class AlienTile extends ItemTile {

    Alien alien;

    public AlienTile(Alien alien) {
        super(alien);
        this.alien = alien;
    }

    @Override
    public String getImageName() {
        return super.getImageName()+alien.getDirection().toString();
    }
}
