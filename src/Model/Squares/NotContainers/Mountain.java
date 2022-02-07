package Model.Squares.NotContainers;

import Model.GameSquare;
import Model.Squares.NotContainer;
import View.Squares.SquareView;

/**
 * Case représentant une montagne
 */
public class Mountain extends NotContainer implements GameSquare {

    SquareView view;

    @Override
    public void clicked(int localX, int localY) {
        System.out.println("Mountain");
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