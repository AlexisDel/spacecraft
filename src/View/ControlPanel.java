package View;

import javax.swing.*;

/** this class creates the control panel with two sub panels : the one for the image and the one for the actions */
public class ControlPanel extends JPanel {
    JPanel imageDisplay;
    JPanel actionsDisplay;

    public ControlPanel(){
        this.imageDisplay = new JPanel();
        this.actionsDisplay= new JPanel();

    }
}
