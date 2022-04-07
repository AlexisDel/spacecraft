package Launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Random;
import java.io.*;

import static Game.View.RessourceManager.FontManager.zeroTwos_12;

public class SettingsPanel extends JPanel {

    private JLabel nbSpaceMarineText;
    private JTextField nbSpaceMarineField;
    private JLabel nbAlienText;
    private JTextField nbAlienField;
    private JLabel nbMeteoriteText;
    private JTextField nbMeteoriteField;
    private JLabel nbMountainText;
    private JTextField nbMountainField;
    private JLabel DifficultyText;
    private JSlider DifficultySlider;
    private JLabel playerNameText;
    private JTextField playerNameField;
    private JLabel seedText;
    private JTextField seedField;
    private JLabel clearScore;
    private JButton clearScoreButton;

    public SettingsPanel() {

        this.setOpaque(false);
        this.setLayout(new GridLayout(8,2));

        playerNameText = new JLabel("Player : ", JLabel.CENTER);
        playerNameText.setForeground(Color.WHITE);
        playerNameText.setFont(zeroTwos_12);
        playerNameField = new JTextField("Pepito");
        playerNameField.setFont(zeroTwos_12);
        playerNameField.setForeground(Color.WHITE);
        playerNameField.setBorder(BorderFactory.createEmptyBorder());
        playerNameField.setCaretColor(Color.WHITE);
        playerNameField.setOpaque(false);
        this.add(playerNameText);
        this.add(playerNameField);

        nbSpaceMarineText = new JLabel("SpaceMarines : ", JLabel.CENTER);
        nbSpaceMarineText.setForeground(Color.WHITE);
        nbSpaceMarineText.setFont(zeroTwos_12);
        nbSpaceMarineField = new JTextField("3");
        nbSpaceMarineField.setFont(zeroTwos_12);
        nbSpaceMarineField.setForeground(Color.WHITE);
        nbSpaceMarineField.setBorder(BorderFactory.createEmptyBorder());
        nbSpaceMarineField.setCaretColor(Color.WHITE);
        nbSpaceMarineField.setOpaque(false);
        this.add(nbSpaceMarineText);
        this.add(nbSpaceMarineField);

        nbAlienText = new JLabel("Aliens : ", JLabel.CENTER);
        nbAlienText.setForeground(Color.WHITE);
        nbAlienText.setFont(zeroTwos_12);
        nbAlienField = new JTextField("3");
        nbAlienField.setFont(zeroTwos_12);
        nbAlienField.setForeground(Color.WHITE);
        nbAlienField.setBorder(BorderFactory.createEmptyBorder());
        nbAlienField.setCaretColor(Color.WHITE);
        nbAlienField.setOpaque(false);
        this.add(nbAlienText);
        this.add(nbAlienField);

        nbMeteoriteText = new JLabel("Meteorites : ", JLabel.CENTER);
        nbMeteoriteText.setForeground(Color.WHITE);
        nbMeteoriteText.setFont(zeroTwos_12);
        nbMeteoriteField = new JTextField("3");
        nbMeteoriteField.setFont(zeroTwos_12);
        nbMeteoriteField.setForeground(Color.WHITE);
        nbMeteoriteField.setBorder(BorderFactory.createEmptyBorder());
        nbMeteoriteField.setCaretColor(Color.WHITE);
        nbMeteoriteField.setOpaque(false);
        this.add(nbMeteoriteText);
        this.add(nbMeteoriteField);

        nbMountainText = new JLabel("Mountains : ", JLabel.CENTER);
        nbMountainText.setForeground(Color.WHITE);
        nbMountainText.setFont(zeroTwos_12);
        nbMountainField = new JTextField("5");
        nbMountainField.setFont(zeroTwos_12);
        nbMountainField.setForeground(Color.WHITE);
        nbMountainField.setBorder(BorderFactory.createEmptyBorder());
        nbMountainField.setCaretColor(Color.WHITE);
        nbMountainField.setOpaque(false);
        this.add(nbMountainText);
        this.add(nbMountainField);

        DifficultyText = new JLabel(" Difficulty : ", JLabel.CENTER);
        DifficultyText.setForeground(Color.WHITE);
        DifficultyText.setFont(zeroTwos_12);
        DifficultySlider = new JSlider(4, 32);
        DifficultySlider.setMinorTickSpacing(4);
        DifficultySlider.setMajorTickSpacing(4);
        DifficultySlider.setPaintTicks(true);
        DifficultySlider.setSnapToTicks(true);
        DifficultySlider.setOpaque(false);
        this.add(DifficultyText);
        this.add(DifficultySlider);

        seedText = new JLabel("Seed : ", JLabel.CENTER);
        seedText.setForeground(Color.WHITE);
        seedText.setFont(zeroTwos_12);
        seedField = new JTextField(Long.toString(new Random().nextLong()));
        seedField.setFont(zeroTwos_12);
        seedField.setForeground(Color.WHITE);
        seedField.setBorder(BorderFactory.createEmptyBorder());
        seedField.setCaretColor(Color.WHITE);
        seedField.setOpaque(false);
        this.add(seedText);
        this.add(seedField);

        clearScore = new JLabel("Reset leaderboard:", JLabel.CENTER);
        clearScore.setForeground(Color.WHITE);
        clearScore.setFont(zeroTwos_12);
        clearScoreButton= new JButton("[CLEAR]");
        clearScoreButton.setForeground(Color.WHITE);
        clearScoreButton.setFont(zeroTwos_12);
        clearScoreButton.setBorderPainted(false);
        clearScoreButton.setContentAreaFilled(false);
        clearScoreButton.setFocusPainted(false);
        clearScoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter writer = new FileWriter(new File("./resources/score.txt"));
                    writer.write("");
                    writer.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                clearScoreButton.setEnabled(false);

            }
        });
        this.add(clearScore);
        this.add(clearScoreButton);

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

    public int getDifficulty(){
        return DifficultySlider.getValue();
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
