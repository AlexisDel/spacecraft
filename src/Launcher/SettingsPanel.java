package Launcher;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.io.*;

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
    private JLabel playerNameText;
    private JTextField playerNameField;
    private JLabel seedText;
    private JTextField seedField;

    public SettingsPanel() {

        this.setOpaque(false);
        this.setLayout(new GridLayout(7,2));

        playerNameText = new JLabel("Player : ", JLabel.CENTER);
        playerNameText.setForeground(Color.WHITE);
        playerNameText.setFont(zeroTwosFont);
        playerNameField = new JTextField("Pepito");
        playerNameField.setFont(zeroTwosFont);
        playerNameField.setForeground(Color.WHITE);
        playerNameField.setBorder(BorderFactory.createEmptyBorder());
        playerNameField.setCaretColor(Color.WHITE);
        playerNameField.setOpaque(false);
        this.add(playerNameText);
        this.add(playerNameField);

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

        nbMountainText = new JLabel("Mountains : ", JLabel.CENTER);
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

        pourcentMountainText = new JLabel(" % Mountain : ", JLabel.CENTER);
        pourcentMountainText.setForeground(Color.WHITE);
        pourcentMountainText.setFont(zeroTwosFont);
        pourcentMountainSlider = new JSlider(0, 40);
        pourcentMountainSlider.setOpaque(false);
        this.add(pourcentMountainText);
        this.add(pourcentMountainSlider);

        seedText = new JLabel("Seed : ", JLabel.CENTER);
        seedText.setForeground(Color.WHITE);
        seedText.setFont(zeroTwosFont);
        seedField = new JTextField(Long.toString(new Random().nextLong()));
        seedField.setFont(zeroTwosFont);
        seedField.setForeground(Color.WHITE);
        seedField.setBorder(BorderFactory.createEmptyBorder());
        seedField.setCaretColor(Color.WHITE);
        seedField.setOpaque(false);
        this.add(seedText);
        this.add(seedField);
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

    public String getPlayerName() {
        return playerNameField.getText();
    }

    public long getSeed(){
        // Don't try to understand
        if(Integer.valueOf(seedField.getText().hashCode()).hashCode() == 679121765)
            return 3;
        return Long.parseLong(seedField.getText());
    }
}
