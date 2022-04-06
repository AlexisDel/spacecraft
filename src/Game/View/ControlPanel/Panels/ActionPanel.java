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

public class ActionPanel extends JPanel {

    public ActionPanel(GameControlPanel gameControlPanel) {

        this.setDoubleBuffered(true);

        this.setPreferredSize(new Dimension(300,64));
        this.setLayout(null);

        JButton move = new JButton("Move");
        move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameControlPanel.setWaitingAction(MOVE);
            }
        });
        move.setBounds(12, 30, 84, 40);
        this.add(move);

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

        JButton attack = new JButton("Attack");
        attack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Fight((SpaceMarine) gameControlPanel.getSelectedItem(), gameControlPanel.getGameEngine().getGameBoard());
            }
        });
        attack.setBounds(204, 30, 84, 40);
        this.add(attack);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(20, 10, this.getWidth()-20, 10);
    }
}
