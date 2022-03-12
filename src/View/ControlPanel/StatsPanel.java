package View.ControlPanel;

import Model.Layer1.Entities.Entity;
import Model.Layer1.InteractiveItem;
import Model.Layer1.Structures.Structure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatsPanel extends JPanel implements ActionListener{
    /**Attributes*/
    private ControlPanel controlPanel;
    private JToolBar tools;

    /**Constructor*/
    public StatsPanel(ControlPanel mcp){
        this.controlPanel =mcp;

        /**Debugging only */
        this.setOpaque(true);

        //this.actionsDisplay.setBackground(Color.GREEN);
        /**Ajoute la bar à outils des actions de l'objet sélectionné*/
        tools = new JToolBar();
        tools.setFloatable(false);
        tools.setOrientation(SwingConstants.VERTICAL);
        this.add(tools);

        /**Ajout du Jlabel du nom de l'objet selectionné*/
        JLabel temp=new JLabel("SpaceCraft !");
        tools.add(temp);
        tools.addSeparator();

    }

    /**Methodes*/
    public void addEntityStats(Entity e){
        //Clear all actions first
        //TODO instead of delete try to change the components -> getComponents()
        this.tools.removeAll();
        //Set the name of the object
        JLabel temp=new JLabel(e.getName());
        tools.add(temp);
        tools.addSeparator();
    }

    public void addStructureStats(Structure s){
       this.tools.removeAll();
        JLabel temp=new JLabel(s.getName());
        tools.add(temp);
        tools.addSeparator();
        //TODO TEMP add struct actions ?
        this.tools.add(new JButton("Build"));
    }

    public void update(InteractiveItem object){
        //Cas entité
        if(object instanceof Entity){
            addEntityStats((Entity) object);
        }
        //Cas Structure
        else{
            addStructureStats((Structure)object);
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
    }
    /** executes the code depending on which action was waiting for the coordinates*/
    public void coordinatesArrived(Point newCoord){
        /*switch (this.actionWaitingForCoordinates){
            case MOVE -> { ((Entity) controlPanel.getSelectedItem()).move(newCoord);
                System.out.println("MOVINNNN"+ controlPanel.getSelectedItem().getName()+" TO "+newCoord.x +","+newCoord.y);}
        }*/
        this.controlPanel.setWaitingForCoord(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "MOVE":
                this.controlPanel.setWaitingForCoord(true);
                //this.actionWaitingForCoordinates=Action.MOVE;
        }

    }

}
