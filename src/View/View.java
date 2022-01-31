package View;
import Model.Board;
import Model.GameConstants;

import java.awt.*;
import javax.swing.*;

/** Cette classe permet de dessiner */
public class View extends JPanel{

    private final static int GAP = 2;
    Board board;
    private MainPanel mainPanel;

    public View(Board board){
        this.board = board;
        createAndShowGUI();
    }

    public void refresh() {
        mainPanel.repaint();
    }

    private void createAndShowGUI() {
        JFrame f = new JFrame("SpaceCraft");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new MainPanel();
        f.add(mainPanel);
        f.pack();
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    JButton getButton() {   return mainPanel.getButton();   }

    class MainPanel extends JPanel {

        private RightPanel rPanel;

        MainPanel() {
            setLayout(new BorderLayout(GAP,GAP));
            add(new TopPanel(), BorderLayout.PAGE_START);
            add(new BoardPanel(), BorderLayout.CENTER);
            rPanel = new RightPanel();
            add(rPanel, BorderLayout.EAST);
        }

        JButton getButton() {   return rPanel.getButton();  }
    }

    class TopPanel extends JPanel {
        TopPanel() {
            setLayout(new FlowLayout(FlowLayout.LEADING));
            add(new JLabel("StarCraft "));
        }
    }

    class BoardPanel extends JPanel {

        //Player player;

         BoardPanel() {

            setBorder(BorderFactory.createLineBorder(Color.BLACK, GAP));
            GridLayout layout = new GridLayout(GameConstants.BOARD_ROWS,
                    GameConstants.BOARD_COLS);
            setLayout(layout);

            for (int i = 0; i < GameConstants.BOARD_ROWS; i++) {

                for (int j = 0; j < GameConstants.BOARD_COLS; j++) {
                    add(new Tile());
                }
            }

            //player = new Player();
            //player.setBounds(new Rectangle(100, 100, 100, 100));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //player.paint(g);
    }

    class Tile extends JLabel {

        Tile() {
            setPreferredSize(new Dimension(GameConstants.BOARD_SQUARESIZE, GameConstants.BOARD_SQUARESIZE));
            setBorder(BorderFactory.createLineBorder(Color.BLACK, GAP));
        }
    }

    class RightPanel extends JPanel {

        JButton button = new JButton("Move Player");

        RightPanel(){
            add(button);
        }

        JButton getButton() {   return button;  }
    }



}