package View.ControlPanel;

import Model.GameEngine;
import Model.Layer1.Entities.Alien;
import Model.Layer1.Entities.Entity;
import Model.Layer1.Entities.SpaceMarine;
import Model.Layer1.InteractiveItem;
import Model.Layer1.Structures.Spaceship;
import Model.Layer1.Structures.Structure;
import View.ControlPanel.Panels.AlienPanel;
import View.ControlPanel.Panels.DefaultPanel;
import View.ControlPanel.Panels.SpaceMarinePanel;
import View.ControlPanel.Panels.SpaceshipPanel;

import javax.swing.*;
import java.awt.*;

import static View.ViewConstants.*;

//TODO: Correct bug causing the requirement of double clicking
/** this class creates the control panel with two sub panels : the one for the image and the one for the actions */
public class ControlPanel extends JPanel {

    //Control panel related attributes
    private boolean waitingForCoord;
    private GameEngine gameEngine;
    private InteractiveItem selectedItem;

    private CardLayout cardLayout = new CardLayout();

    public ControlPanel(GameEngine gameEngine){
        this.gameEngine = gameEngine;

        this.waitingForCoord=false;
        /**init selected entity to first entity on the board, it needs a non null displayPanel*/
        this.selectedItem =this.gameEngine.getGameBoard().getEntities().get(0);

        /**set own dimensions and initialize the sizes of sub JPanels: displayPanel and statsPanel*/
        this.setLayout(cardLayout);
        this.setPreferredSize(new Dimension(CONTROL_PANEL_WIDTH, CONTROL_PANEL_HEIGHT));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);

        /**adds the display panel*/
        this.add(new DefaultPanel(), "DEFAULT");
        this.add(new AlienPanel(), Alien.class.getName());
        this.add(new SpaceMarinePanel(), SpaceMarine.class.getName());
        this.add(new SpaceshipPanel(), Spaceship.class.getName());
        cardLayout.show(this, "DEFAULT");
    }

    /**
     * Sélectionne l'entité qui se trouve sur la case de coordonnées p
     * S'il n'y a pas d'entité sur cette case alors rien ne se passe
     * @param p Coordonnées de la case
     */
    public void SelectItem(Point p){
        //if we are not waiting for coordinates then set as new selected entity/building
        if(!this.waitingForCoord){
            // Cherche dans la liste des structures s'il y en a une à cette coordonnée
            for (Structure structure : this.gameEngine.getGameBoard().getStructures()){
                // TODO : a modifier si structure avec forme particulière
                // Si la case sur laquelle on a cliqué "appartient" au bâtiment
                if (p.x >= structure.getCoordinate().x &&
                    p.x < structure.getCoordinate().x + structure.getDimension().width &&
                    p.y >= structure.getCoordinate().y &&
                    p.y < structure.getCoordinate().y + structure.getDimension().height
                ) {
                    setSelectedItem(structure);
                    System.out.println("select structure");
                }
            }
            // Cherche dans la liste des entités s'il y en a une à cette coordonnée
            for (Entity e : this.gameEngine.getGameBoard().getEntities()){
                if(e.getCoordinate().equals(p)){
                    setSelectedItem(e);
                    System.out.println("select set to entity");
                }
            }
        }
        else{
            //TODO bug prevention: make sure you have an entity to move !
            //this.statsPanel.coordinatesArrived(p);
        }
    }

    /**
     * Sets the selected Layer 1 object and updates the Display and Stats panels
     * @param objectL1
     */
    public void setSelectedItem(InteractiveItem objectL1){
        this.selectedItem = objectL1;
        this.cardLayout.show(this, selectedItem.getClass().getName());
        //this.statsPanel.update(this.selectedItem);
    }

    public void setWaitingForCoord(boolean waitingForCoord) {
        this.waitingForCoord = waitingForCoord;
    }

    public InteractiveItem getSelectedItem() {
        return selectedItem;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.repaint();
        //this.statsPanel.repaint();
    }
}
