package View;

import Model.GameEngine;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    // Taille d'une case en pixel (unité de base)
    public static final int tileSize = 16;
    public static final int scale = 4;
    // Taille d'une case du tableau en pixel
    public static final int boardTileSize = tileSize * scale;

    // Nombre de cases par colonne
    private final int maxBoardViewColumn = 10;
    // Nombre de cases par ligne
    private final int maxBoardViewRow = 10;

    // 1024 pixels display
    private final int BoardViewWidth = boardTileSize * maxBoardViewColumn;
    private final int BoardViewHeight = boardTileSize * maxBoardViewRow;

    private GameEngine gameEngine;

    public BoardPanel(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.setPreferredSize(new Dimension(BoardViewWidth, BoardViewHeight));
        this.setBackground(Color.BLACK);
        // All the drawing from this component will be done in an offscreen painting buffer
        // tl;dr : improves game's rendering performance
        this.setDoubleBuffered(true);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // Parcours le plateau du jeu
        for(int i = 0; i < maxBoardViewRow; i++){
            for(int j = 0; j < maxBoardViewColumn; j++){
                gameEngine.getGameBoard().getGameSquare(i,j).draw(g2, i* boardTileSize, j* boardTileSize);
            }
        }
        // Dispose of this graphics context and release any system ressources that it is using
        g2.dispose();
    }
}
