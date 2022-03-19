package Launcher;

import javax.swing.*;
import java.awt.*;

import static View.ImageManager.zeroTwosFont;

public class SettingsPanel extends JPanel {

    private JLabel nbSpaceMarineText;
    private JTextField nbSpaceMarineField;
    private JLabel nbAlienText;
    private JTextField nbAlienField;
    private JLabel nbMeteoriteText;
    private JTextField nbMeteoriteField;
    private JLabel nbMountainText;
    private JTextField nbMountainField;
    private JLabel pourcentMountainText;
    private JSlider pourcentMountainSlider;


    public SettingsPanel() {

        this.setOpaque(false);
        this.setLayout(new GridLayout(5,2));

        nbSpaceMarineText = new JLabel("SpaceMarines : ", JLabel.CENTER);
        nbSpaceMarineText.setForeground(Color.WHITE);
        nbSpaceMarineText.setFont(zeroTwosFont);
        nbSpaceMarineField = new JTextField("3");
        nbSpaceMarineField.setFont(zeroTwosFont);
        nbSpaceMarineField.setForeground(Color.WHITE);
        nbSpaceMarineField.setBorder(BorderFactory.createEmptyBorder());
        nbSpaceMarineField.setCaretColor(Color.WHITE);
        nbSpaceMarineField.setOpaque(false);
        this.add(nbSpaceMarineText);
        this.add(nbSpaceMarineField);

        nbAlienText = new JLabel("Aliens : ", JLabel.CENTER);
        nbAlienText.setForeground(Color.WHITE);
        nbAlienText.setFont(zeroTwosFont);
        nbAlienField = new JTextField("3");
        nbAlienField.setFont(zeroTwosFont);
        nbAlienField.setForeground(Color.WHITE);
        nbAlienField.setBorder(BorderFactory.createEmptyBorder());
        nbAlienField.setCaretColor(Color.WHITE);
        nbAlienField.setOpaque(false);
        this.add(nbAlienText);
        this.add(nbAlienField);

        nbMeteoriteText = new JLabel("Meteorites : ", JLabel.CENTER);
        nbMeteoriteText.setForeground(Color.WHITE);
        nbMeteoriteText.setFont(zeroTwosFont);
        nbMeteoriteField = new JTextField("3");
        nbMeteoriteField.setFont(zeroTwosFont);
        nbMeteoriteField.setForeground(Color.WHITE);
        nbMeteoriteField.setBorder(BorderFactory.createEmptyBorder());
        nbMeteoriteField.setCaretColor(Color.WHITE);
        nbMeteoriteField.setOpaque(false);
        this.add(nbMeteoriteText);
        this.add(nbMeteoriteField);

        nbMountainText = new JLabel("Montagnes : ", JLabel.CENTER);
        nbMountainText.setForeground(Color.WHITE);
        nbMountainText.setFont(zeroTwosFont);
        nbMountainField = new JTextField("5");
        nbMountainField.setFont(zeroTwosFont);
        nbMountainField.setForeground(Color.WHITE);
        nbMountainField.setBorder(BorderFactory.createEmptyBorder());
        nbMountainField.setCaretColor(Color.WHITE);
        nbMountainField.setOpaque(false);
        this.add(nbMountainText);
        this.add(nbMountainField);

        pourcentMountainText = new JLabel(" % Montagnes : ", JLabel.CENTER);
        pourcentMountainText.setForeground(Color.WHITE);
        pourcentMountainText.setFont(zeroTwosFont);
        pourcentMountainSlider = new JSlider(10, 50);
        pourcentMountainSlider.setOpaque(false);
        this.add(pourcentMountainText);
        this.add(pourcentMountainSlider);
    }

    public int getNbSpaceMarines(){
        return Integer.parseInt(nbSpaceMarineField.getText());
    }

    public int getNbAliens(){
        return Integer.parseInt(nbAlienField.getText());
    }

    public int getNbMeteorites(){
        return Integer.parseInt(nbMeteoriteField.getText());
    }

    public int getNbMountains(){
        return Integer.parseInt(nbMountainField.getText());
    }

    public int getPourcentMountain(){
        return pourcentMountainSlider.getValue();
    }
}
