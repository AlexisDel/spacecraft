package Game.View.Board.Views;

import Game.Model.GameElements.Layer1.Entities.Alien;

public class AlienView extends ItemView {

    Alien alien;

    public AlienView(Alien alien) {
        super(alien);
        this.alien = alien;
    }

    @Override
    public String getImageName() {
        return super.getImageName()+alien.getDirection().toString();
    }
}
