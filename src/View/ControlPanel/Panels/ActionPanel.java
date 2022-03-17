package View.ControlPanel.Panels;

import Model.Layer1.Entities.Actions.Mine;
import Model.Layer1.Entities.Entity;
import View.ControlPanel.ControlPanel;
import View.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Model.Layer1.Entities.Actions.Action.MINE;
import static Model.Layer1.Entities.Actions.Action.MOVE;

public class ActionPanel extends JPanel {

    public ActionPanel(ControlPanel controlPanel) {

        JButton move = new JButton("Move");
        move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("test");
                controlPanel.setWaitingAction(MOVE);
            }
        });
        this.add(move);

        JButton mine = new JButton("Mine");
        mine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlPanel.setExecutingAction(MINE);
                //On mine le meteorite le plus proche
                Mine m= new Mine((Entity) controlPanel.getSelectedItem(), controlPanel.getGameEngine().getGameBoard());
                //lock mouvement button
                //proc progress bar
            }
        });
        this.add(mine);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ImageManager.controlPanelBGMid2, 0, 0, null);
    }
}
