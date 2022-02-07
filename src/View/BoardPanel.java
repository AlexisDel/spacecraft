package View;

import Model.GameConstants;
import Model.GameEngine;
import Model.GameSquare;
import Model.Squares.Containers.Buildings.Rocket;
import Model.Squares.Containers.Land;
import Model.Squares.NotContainers.Mountain;
import View.Squares.LandView;
import View.Squares.MountainView;
import View.Squares.RocketView;

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
    private int zoomScaleFactor = 1;

    public BoardPanel(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.setPreferredSize(new Dimension(640, 640));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        // All the drawing from this component will be done in an offscreen painting buffer
        // tl;dr : improves game's rendering performance
        this.setDoubleBuffered(true);

        // TODO : TileManager
        LandView landView = new LandView(this);
        MountainView mountainView = new MountainView(this);
        RocketView rocketView = new RocketView(this);

        for(int i = 0; i < GameConstants.BOARD_WIDTH; i++){
            for(int j = 0; j < GameConstants.BOARD_HEIGHT; j++){
                GameSquare square = gameEngine.getGameBoard().getSquare(i,j);
                if(square instanceof Land){
                    square.setView(landView);
                }
                else if (square instanceof Mountain){
                    square.setView(mountainView);
                }
                else if (square instanceof Rocket){
                    square.setView(rocketView);
                }
            }
        }

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

        int x = mouseX/boardTileSize + currentX;
        int y = mouseY/boardTileSize + currentY;
        int localX = (mouseX%boardTileSize)/tileSize;
        int localY = (mouseY%boardTileSize)/tileSize;

        gameEngine.getGameBoard().getSquare(x, y).clicked(localX, localY);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // Parcours le plateau du jeu
        for(int i = currentX; i < maxBoardViewRow+currentX; i++){
            for(int j = currentY; j < maxBoardViewColumn+currentY; j++){
                gameEngine.getGameBoard().getSquare(i,j).getView().draw(g2, (i-currentX)*boardTileSize, (j-currentY)*boardTileSize);
            }
        }
        // Dispose of this graphics context and release any system ressources that it is using
        g2.dispose();
    }


    public void zoomIn() {
        if(zoomScaleFactor > 1){
            zoomScaleFactor--;
            updateZoomScaleFactor();
        }
    }

    public void zoomOut(){
        if(zoomScaleFactor < 3){
            zoomScaleFactor++;
            updateZoomScaleFactor();
        }

    }

    private void updateZoomScaleFactor(){
        switch (zoomScaleFactor) {
            case 1 -> defaultZoom();
            case 2 -> halfScreen();
            case 3 -> fullScreen();
        }
    }

    private void defaultZoom(){
        tileSize = 16;
        boardTileSize = 64;
        maxBoardViewColumn = 10;
        maxBoardViewRow = 10;
        currentX = 0;
        currentY = 0;
    }

    private void halfScreen(){
        tileSize = 8;
        boardTileSize = 32;
        maxBoardViewColumn = 20;
        maxBoardViewRow = 20;
        currentX = 0;
        currentY = 0;
    }

    private void fullScreen() {
        tileSize = 4;
        boardTileSize = 16;
        maxBoardViewColumn = 40;
        maxBoardViewRow = 40;
        currentX = 0;
        currentY = 0;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getBoardTileSize() {
        return boardTileSize;
    }
}
