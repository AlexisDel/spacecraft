package View.ControlPanel;

import Model.GameEngine;
import Model.Layer1.Entities.Entity;
import Model.Layer1.Layer1Object;
import Model.Layer1.Structures.Structure;
import View.ViewConstants;


import javax.swing.*;
import java.awt.*;
//TODO: Correct bug causing the requirement of double clicking
/** this class creates the control panel with two sub panels : the one for the image and the one for the actions */
public class MainControlPanel extends JPanel {
    //Variables
    private int CONTROLPANEL_WIDTH= ViewConstants.WINDOW_WIDTH-ViewConstants.BOARDPANEL_WITDH;
    private int CONTROLPANEL_HEIGHT= ViewConstants.WINDOW_HEIGHT;
    //Control panel related attributes
    private boolean waitingForCoord;
    private Point selectedCoord;
    private GameEngine gameEngine;
    private Layer1Object selectedL1Object;
    //Sub panels
    private StatsPanel statsPanel;
    private DisplayPanel displayPanel;

    public MainControlPanel(GameEngine gameEngine){
        this.gameEngine=gameEngine;

        this.waitingForCoord=false;
        this.selectedCoord= new Point(-1,-1);
        /**init selected entity to first entity on the board, it needs a non null displayPanel*/
        this.selectedL1Object=this.gameEngine.getGameBoard().getEntities().get(0);

        /**set own dimensions and initialize the sizes of sub JPanels: displayPanel and statsPanel*/
        this.setLayout(new BorderLayout(10,10));
        this.setPreferredSize(new Dimension(CONTROLPANEL_WIDTH, CONTROLPANEL_HEIGHT));

        /**adds the display panel*/
        this.displayPanel=new DisplayPanel(this);
        displayPanel.setPreferredSize(new Dimension(CONTROLPANEL_WIDTH, CONTROLPANEL_WIDTH));

        /** Add the stats panel*/
        this.statsPanel= new StatsPanel(this);
        statsPanel.setPreferredSize(new Dimension(CONTROLPANEL_WIDTH, CONTROLPANEL_HEIGHT-CONTROLPANEL_WIDTH));

        /** add subpanels to main panel*/
        this.add(statsPanel);
        this.add(displayPanel, BorderLayout.NORTH);

    }

    /** method that receives the coordinates from the click on the screen*/
    public void setSelectedCoord(Point p){
        this.selectedCoord=p;
        //if we are not waiting for coordinates then set as new selected entity/ building
        if(!this.waitingForCoord){
            setSelectedL1ObjectFromPoint(p);
        }
        else{
            //TODO bug prevention: make sure you have an entity to move !
            this.selectedCoord=p;
            this.statsPanel.coordinatesArrived(p);
        }
    }
    /** method used when clicking*/
    public void setSelectedL1ObjectFromPoint(Point p){

        for (Structure s : this.gameEngine.getGameBoard().getStructures()){
            for(Point po: s.getPointsOccupied()){
                if(po.equals(p)){
                setSelectedL1Object(s);
                System.out.println("select set to building");
                }
            }
        }
        for (Entity e : this.gameEngine.getGameBoard().getEntities()){
            if(e.getCoordinate().equals(p)){
                setSelectedL1Object(e);
                System.out.println("select set to entity");

            }
        }
    }

    /**
     * Sets the selected Layer 1 object and updates the Display and Stats panels
     * @param objectL1
     */
    public void setSelectedL1Object(Layer1Object objectL1){
        this.selectedL1Object=objectL1;
        this.displayPanel.updateDisplayImage(this.selectedL1Object.getImage());
        this.statsPanel.update(this.selectedL1Object);
    }

    public void setWaitingForCoord(boolean waitingForCoord) {
        this.waitingForCoord = waitingForCoord;
    }

    public Layer1Object getSelectedL1Object() {
        return selectedL1Object;
    }

    /** Sets the selected coordinates to a Point, called by StatsDisplay*/
    public Point getSelectedCoord() {
        return selectedCoord;
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D)g;
        this.displayPanel.repaint();
        this.statsPanel.repaint();
    }
}
