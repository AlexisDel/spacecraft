package Game.View.ControlPanel.Panels;

import Game.View.RessourceManager;

import javax.swing.*;
import java.awt.*;

/**
 * Cette méthode fait la même chose que GameInstructionPanel sauf que au lieu d'instructions,
 * elle affiche le texte qui décrit le gameplay
 */
public class GamePlayPanel extends JPanel {
    /** Constructeur */
    public GamePlayPanel() {
        //On prépare le panneau
        this.setDoubleBuffered(true);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Gameplay");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        //On choisi la police d'ecriture
        title.setFont(RessourceManager.FontManager.Dune2000_15.deriveFont(20));
        this.add(title);

        // Separator
        this.add(Box.createRigidArea(new Dimension(1, 10)));
        //On crée l'objet qui va afficher le texte: un JTextArea
        JTextArea text = new JTextArea(
                "3. To move a space marine click on it , click on move, and then click on an empty space in the map where you want to go \n" +
                "4. To mine a meteorite, place a space marine in a space adjacent to the Meteorite and then click on his mine button\n" +
                "5. To kill an alien you must place a space marine near enough from an alien and then click on his attack button");
        text.setFont(RessourceManager.FontManager.Dune2000_15.deriveFont(13));
        text.setOpaque(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        //On ajoute le text pour qu'il s'affiche
        this.add(text);
    }
}
