package Model.Squares.NotContainers;

import Model.GameSquare;

/**
 * Case représentant une montagne
 */
public class Mountain implements GameSquare {

    @Override
    public boolean isContainer() {
        return false;
    }
}
