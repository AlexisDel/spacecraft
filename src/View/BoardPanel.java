package View;

import Model.GameEngine;
import Model.Layer1.Entities.Entity;
import Model.Layer1.Structures.Structure;

import javax.swing.*;
import java.awt.*;

import static Model.GameConstants.BOARD_HEIGHT;
import static Model.GameConstants.BOARD_WIDTH;

public class BoardPanel extends JPanel {

    // Taille d'une case en pixel (unité de base = taille des entités)
    private int tileSize = 16;

    // Nombre de cases par colonne
    private int maxBoardViewColumn = 40;
    // Nombre de cases par ligne
    private int maxBoardViewRow = 40;

    private final int BoardViewWidth = tileSize * maxBoardViewColumn;
    private final int BoardViewHeight = tileSize * maxBoardViewRow;

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

    }

    public void moveUp(){
        if(currentY > 0){
            currentY--;
        }
    }

    public void moveDown(){
        if(currentY < BOARD_WIDTH - maxBoardViewRow){
            currentY++;
        }
    }

    public void moveLeft() {
        if (currentX > 0){
            currentX--;
        }
    }

    public void moveRight() {
        if (currentX < BOARD_HEIGHT - maxBoardViewColumn){
            currentX++;
        }
    }

    public void ClickTile(int mouseX, int mouseY) {

        int x = mouseX/tileSize + currentX;
        int y = mouseY/tileSize + currentY;

        //gameEngine.getGameBoard().getSquare(x, y).clicked(localX, localY);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // Layer 0
        // Dessine seulement ce qui est affiché
        for(int i = currentX; i < maxBoardViewRow+currentX; i++){
            for(int j = currentY; j < maxBoardViewColumn+currentY; j++){
                gameEngine.getGameBoard().getTerrain()[i][j].getView().draw(g2, (i-currentX), (j-currentY), tileSize);
            }
        }

        //TODO: optimisation ne pas dessiner si hors de l'écran (prendre en compte la taille)
        // Layer 1
        for(Structure structure : gameEngine.getGameBoard().getStructures()){
            structure.getView().draw(g2, tileSize, currentX, currentY);
        }
        // Layer 2
        for(Entity entity : gameEngine.getGameBoard().getEntities()){
            entity.getView().draw(g2, tileSize, currentX, currentY);
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
        maxBoardViewColumn = 40;
        maxBoardViewRow = 40;
        currentX = 0;
        currentY = 0;
    }

    private void halfScreen(){
        tileSize = 8;
        maxBoardViewColumn = 80;
        maxBoardViewRow = 80;
        currentX = 0;
        currentY = 0;
    }

    private void fullScreen() {
        tileSize = 4;
        maxBoardViewColumn = 160;
        maxBoardViewRow = 160;
        currentX = 0;
        currentY = 0;
    }
}
