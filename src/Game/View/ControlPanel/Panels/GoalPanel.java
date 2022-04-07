package Game.View.ControlPanel.Panels;

import Game.View.RessourceManager;

import javax.swing.*;
import java.awt.*;
/** Cette méthode sert à afficher le texte qui décrit l'objectif du jeu*/
public class GoalPanel extends JPanel {
    /** Constructeur*/
    public GoalPanel() {
        //On prépare le panneau
        this.setDoubleBuffered(true);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(this.getPreferredSize());

        JLabel title = new JLabel("Goals");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        //On choisi la police d'écrite
        title.setFont(RessourceManager.FontManager.Dune2000_15.deriveFont(20));
        this.add(title);
        // Separator
        this.add(Box.createRigidArea(new Dimension(1, 10)));
        //Création de l'objet qui contient le texte à afficher
        JTextArea text = new JTextArea("Capture all the rocks in the map by mining the meteorites in the surface of Mars or killing the aliens that have captured rocks AS SOON AS POSSIBLE. The fastest player will be placed at the top of the leaderboard (visible after the game ends)");
        //Préparation du texte (font, color)
        text.setFont(RessourceManager.FontManager.Dune2000_15.deriveFont(13));
        text.setOpaque(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        //Affichage du texte
        this.add(text);
    }
}
