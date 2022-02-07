package Model.Squares.Containers.Buildings;

import Model.GameSquare;
import Model.Squares.Containers.Building;
import View.Squares.SquareView;

public class Rocket extends Building implements GameSquare {

    SquareView view;

    @Override
    public void clicked(int localX, int localY) {
        System.out.println("Rocket !");
    }

    @Override
    public void setView(SquareView view) {
        this.view = view;
    }

    @Override
    public SquareView getView() {
        return view;
    }
}
