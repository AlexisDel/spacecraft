package Game.View.ControlPanel.Panels;

import Game.Model.GameElements.Layer1.Entities.Actions.Mine;
import Game.Model.GameElements.Layer1.Entities.Actions.Mouvements.Fight;
import Game.Model.GameElements.Layer1.Entities.Entity;
import Game.Model.GameElements.Layer1.Entities.SpaceMarine;
import Game.View.ControlPanel.GameControlPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Game.Model.GameElements.Layer1.Entities.Actions.Action.MINE;
import static Game.Model.GameElements.Layer1.Entities.Actions.Action.MOVE;

/**
 * C'est le panneau contenant les boutons qui executent les actions du SpaceMarine
 * Il va etre placé dans le JPanel SpaceMarinePanel
 */
public class ActionPanel extends JPanel {
    /**
     * Constructeur du JPanel
     * @param gameControlPanel
     */
    public ActionPanel(GameControlPanel gameControlPanel) {

        this.setDoubleBuffered(true);
        //On met une taille spécifique pour que ça déborde pas dasn d'autres panels
        this.setPreferredSize(new Dimension(300,64));
        this.setLayout(null);
        //Création du bouton MOVE
        JButton move = new JButton("Move");
        move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Si on clique sur le bouton on notifie le Control Panel
                gameControlPanel.setWaitingAction(MOVE);
            }
        });
        move.setBounds(12, 30, 84, 40);
        this.add(move);
        //Création du bouton MINE
        JButton mine = new JButton("Mine");
        mine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameControlPanel.setExecutingAction(MINE);
                //On mine le meteorite le plus proche
                new Mine((Entity) gameControlPanel.getSelectedItem(), gameControlPanel.getGameEngine().getGameBoard());
                //lock mouvement button
                //proc progress bar
            }
        });
        mine.setBounds(108, 30, 84, 40);
        this.add(mine);
        //Création du bouton ATTACK
        JButton attack = new JButton("Attack");
        attack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Si on clique sur le bouton on attaque avec le SpaceMarine l'alien adjacent (s'il existe un)
                new Fight((SpaceMarine) gameControlPanel.getSelectedItem(), gameControlPanel.getGameEngine().getGameBoard());
            }
        });
        attack.setBounds(204, 30, 84, 40);
        this.add(attack);
    }

    /**
     * Cette méthode dessine sur le panneau les lignes qui séparent visuelment le panneau des autres panneaux
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        //On trace la ligne grise
        g.drawLine(20, 10, this.getWidth()-20, 10);
    }
}
