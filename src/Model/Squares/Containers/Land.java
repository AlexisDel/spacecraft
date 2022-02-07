package Model.Squares.Containers;

import Model.GameSquare;
import Model.Squares.Container;
import View.Squares.SquareView;

/**
 * Case représentant un terrain
 */
public class Land extends Container implements GameSquare {

    SquareView view;

    @Override
    public void clicked(int localX, int localY) {
        System.out.println("Land !");

        // TODO : discuter de l'implémentation (click direct sur entity?)
        if(getEntities()[localX][localY] != null){
            System.out.println("entity");
        }
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
