package View;

import Model.Board;
import Model.Entities.Character;
import Model.Entities.Entity;
import Model.Entities.Mountain;
import Model.Entities.SpaceShip;
import Model.GameConstants;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel {

    // Tableau stockant les informations du modèle
    private Entity[][] board;
    // Tableau stockant les tiles
    private Tile[][] tiles;
    // Distance entre chaque Tiles
    public final static int GAP = 2;

    public BoardView(Board board) {
        // Initialisation de board
        this.board = board.getArray();
        // Initialisation de tiles
        this.tiles = new Tile[GameConstants.BOARD_COLS][GameConstants.BOARD_ROWS];

        // Initialisation de la grille
        setPreferredSize(new Dimension(600, 600));
        setLayout(new BorderLayout(GAP, GAP));
        setBorder(BorderFactory.createLineBorder(Color.PINK, GAP));
        GridLayout layout = new GridLayout(GameConstants.BOARD_ROWS, GameConstants.BOARD_COLS);
        setLayout(layout);

        // Initialisation du contenue de la grille
        for (int i = 0; i < GameConstants.BOARD_ROWS; i++) {
            for (int j = 0; j < GameConstants.BOARD_COLS; j++) {
                // Création d'une case
                Tile temporaryTile = new Tile();
                // Ajout de la case à la grille
                this.add(temporaryTile);
                // Ajout de la case au tablau stockant les cases
                this.tiles[i][j] = temporaryTile;
            }
        }
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (int i = 0; i < GameConstants.BOARD_ROWS; i++) {
            for (int j = 0; j < GameConstants.BOARD_COLS; j++) {
                Entity localEntity = this.board[i][j];
                if(localEntity instanceof Character) {
                    Image img = Images.SpaceMarine.getImage();
                    Image newImg = img.getScaledInstance(GameConstants.BOARD_SQUARESIZE,GameConstants.BOARD_SQUARESIZE,java.awt.Image.SCALE_SMOOTH );
                    ImageIcon ic = new ImageIcon(newImg);
                    this.tiles[i][j].setIcon(ic);
                }

                else if(localEntity instanceof SpaceShip){
                    Image img = Images.SpaceShip.getImage();
                    Image newImg = img.getScaledInstance(GameConstants.BOARD_SQUARESIZE,GameConstants.BOARD_SQUARESIZE,java.awt.Image.SCALE_SMOOTH );
                    ImageIcon ic = new ImageIcon(newImg);
                    this.tiles[i][j].setIcon(ic);
                }
                else if(localEntity instanceof Mountain){
                    this.tiles[i][j].setOpaque(true);
                    this.tiles[i][j].setBackground(new Color(139,69,19));
                }
            }
        }
    }

    class Tile extends JLabel {

        Tile() {
            setPreferredSize(new Dimension(GameConstants.BOARD_SQUARESIZE, GameConstants.BOARD_SQUARESIZE));
            setBorder(BorderFactory.createLineBorder(Color.BLACK, GAP));
        }
    }

}
