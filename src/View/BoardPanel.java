package View;

import Model.GameConstants;
import Model.GameEngine;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    // Taille d'une case en pixel (unité de base = taille d'des entités)
    private int tileSize = 16;
    // Nombre de tile pour un carré du jeu (GameSquare)
    private int tileToSquare = 4;
    // Taille d'une case du tableau en pixel
    private int boardTileSize = tileSize * tileToSquare;

    // Nombre de cases par colonne
    private int maxBoardViewColumn = 10;
    // Nombre de cases par ligne
    private int maxBoardViewRow = 10;

    private final int BoardViewWidth = boardTileSize * maxBoardViewColumn;
    private final int BoardViewHeight = boardTileSize * maxBoardViewRow;

    private GameEngine gameEngine;

    private int currentX = 0;
    private int currentY = 0;

    public BoardPanel(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.setPreferredSize(new Dimension(640, 640));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        // All the drawing from this component will be done in an offscreen painting buffer
        // tl;dr : improves game's rendering performance
        this.setDoubleBuffered(true);
    }

    public void moveUp(){
        if(currentY > 0){
            currentY--;
        }
    }

    public void moveDown(){
        if(currentY < GameConstants.BOARD_WIDTH - maxBoardViewRow){
            currentY++;
        }
    }

    public void moveLeft() {
        if (currentX > 0){
            currentX--;
        }
    }

    public void moveRight() {
        if (currentX < GameConstants.BOARD_HEIGHT - maxBoardViewColumn){
            currentX++;
        }
    }

    public void ClickTile(int mouseX, int mouseY) {
        gameEngine.getGameBoard().getSquare(mouseX/boardTileSize, mouseY/boardTileSize).clicked((mouseX%boardTileSize)/tileSize, (mouseY%boardTileSize)/tileSize);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // Parcours le plateau du jeu
        for(int i = currentX; i < maxBoardViewRow+currentX; i++){
            for(int j = currentY; j < maxBoardViewColumn+currentY; j++){
                gameEngine.getGameBoard().getSquare(i,j).draw(g2, (i-currentX)*boardTileSize, (j-currentY)*boardTileSize, boardTileSize, tileSize);
            }
        }
        // Dispose of this graphics context and release any system ressources that it is using
        g2.dispose();
    }

}
