package View;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    // Taille d'une case en pixel case avant upscaling (resolution ressource)
    private final int originalTileSize = 16;
    // Échelle de l'affichage
    private final int scale = 3;
    // Taille d'une case en pixel case après upscaling (resolution affichage)
    private final int tileSize = originalTileSize * scale;

    // Nombre de cases par colonne
    private final int maxBoardViewColumn = 16;
    // Nombre de cases par ligne
    private final int maxBoardViewRow = 12;

    private final int BoardViewWidth = tileSize * maxBoardViewColumn;
    private final int BoardViewHeight = tileSize * maxBoardViewRow;

    public BoardPanel() {
        this.setPreferredSize(new Dimension(BoardViewWidth, BoardViewHeight));
        this.setBackground(Color.BLACK);
        // All the drawing from this component will be done in an offscreen painting buffer
        // tl;dr : improves game's rendering performance
        this.setDoubleBuffered(true);
    }
}
