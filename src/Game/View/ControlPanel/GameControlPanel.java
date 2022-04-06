package Game.View.ControlPanel;

import Game.Model.GameEngine;
import Game.Model.GameElements.Layer1.Entities.Actions.Action;
import Game.Model.GameElements.Layer1.Entities.Actions.Mouvements.Movement;
import Game.Model.GameElements.Layer1.Entities.Alien;
import Game.Model.GameElements.Layer1.Entities.Entity;
import Game.Model.GameElements.Layer1.Entities.SpaceMarine;
import Game.Model.GameElements.Layer1.InteractiveGameElement;
import Game.Model.GameElements.Layer1.Structures.Meteorite;
import Game.Model.GameElements.Layer1.Structures.Spaceship;
import Game.Model.GameElements.Layer1.Structures.Structure;
import Game.View.ControlPanel.ElementsPanels.*;

import javax.swing.*;
import java.awt.*;

import static Game.View.ViewConstants.CONTROL_PANEL_HEIGHT;
import static Game.View.ViewConstants.CONTROL_PANEL_WIDTH;

/** this class creates the control panel with two sub panels : the one for the image and the one for the actions */
public class GameControlPanel extends JPanel {

    //Control panel related attributes
    private GameEngine gameEngine;
    private InteractiveGameElement selectedItem;
    private InteractiveGameElement tagetItem;
    private Point targetCoord;
    private Action waitingAction;
    private Action executingAction;

    private CardLayout cardLayout = new CardLayout();

    public GameControlPanel(GameEngine gameEngine){
        this.gameEngine = gameEngine;
        this.setDoubleBuffered(true);

        this.setBackground(Color.GRAY);

        this.waitingAction=Action.NONE;
        /**init selected entity to first entity on the board, it needs a non null displayPanel*/
        this.selectedItem = this.gameEngine.getGameBoard().getEntities().get(0);

        /**set own dimensions and initialize the sizes of sub JPanels: displayPanel and statsPanel*/
        this.setLayout(cardLayout);
        this.setPreferredSize(new Dimension(CONTROL_PANEL_WIDTH, CONTROL_PANEL_HEIGHT));
        this.setDoubleBuffered(true);

        /**adds the display panel*/
        this.add(new DefaultPanel(), "DEFAULT");
        this.add(new AlienPanel(this), Alien.class.getSimpleName());
        this.add(new SpaceMarinePanel(this), SpaceMarine.class.getSimpleName());
        this.add(new SpaceshipPanel(this), Spaceship.class.getSimpleName());
        this.add(new MeteoritePanel(this), Meteorite.class.getSimpleName());

        cardLayout.show(this, "DEFAULT");
    }

    /**
     * Sélectionne l'entité qui se trouve sur la case de coordonnées p
     * S'il n'y a pas d'entité sur cette case alors on notifie l'action en attente que des coordonnées sont là
     * @param p Coordonnées de la case
     */
    public void SelectItem(Point p) throws InterruptedException {
        //if we are not waiting for coordinates then set as new selected entity/building
        if(this.waitingAction==Action.NONE||this.waitingAction==Action.MINE||this.waitingAction==Action.ATTACK){
            /**Cas objet cliqué est une structure*/
            // Cherche dans la liste des structures s'il y en a une à cette coordonnée
            for (Structure structure : this.gameEngine.getGameBoard().getStructures()){
                // Si la case sur laquelle on a cliqué "appartient" au bâtiment
                if (p.x >= structure.getCoordinate().x &&
                    p.x < structure.getCoordinate().x + structure.getDimension().width &&
                    p.y >= structure.getCoordinate().y &&
                    p.y < structure.getCoordinate().y + structure.getDimension().height
                ) {
                   if(this.waitingAction==Action.NONE) setSelectedItem(structure);
                   else{this.tagetItem=structure;
                   notifyWaitingAction();}
                }
            }
            /**Cas objet cliqué est une entité*/
            // Cherche dans la liste des entités s'il y en a une à cette coordonnée
            for (Entity e : this.gameEngine.getGameBoard().getEntities()){
                if(e.getCoordinate().equals(p)){
                    if(this.waitingAction==Action.NONE) setSelectedItem(e);
                    else{this.tagetItem=e;
                    notifyWaitingAction();}
                }
            }
        }
        /**Cas il n'y a rien dans l'objet cliqué*/
        else{
            this.targetCoord=p;
            if(this.waitingAction==Action.MOVE) notifyWaitingAction();
        }
    }

    /**
     * Cette méthode notifie le panneau de contrôle quand des nouvelles coordonnées ont été cliquées sur la map
     * @throws InterruptedException
     */
    public void notifyWaitingAction() throws InterruptedException {

        switch(this.waitingAction){

            case MOVE -> {
                Entity entity = (Entity) this.selectedItem;
                entity.setIsMoving(false);
                Movement walk = new Movement(entity, this.targetCoord, gameEngine.getGameBoard(), false);
            }
        }
        this.waitingAction=Action.NONE;

    }

    /**
     * Sets the selected Layer 1 object and updates the Display and Stats panels
     * @param objectL1
     */
    public void setSelectedItem(InteractiveGameElement objectL1){
        this.selectedItem = objectL1;
        this.cardLayout.show(this, selectedItem.getClass().getSimpleName());
    }

    public void setWaitingAction(Action waitingAction) {
        this.waitingAction = waitingAction;
    }

    public void setExecutingAction(Action executingAction) {
        this.executingAction = executingAction;
    }

    public InteractiveGameElement getSelectedItem() {
        return selectedItem;
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
