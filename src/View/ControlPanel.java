package View;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/** this class creates the control panel with two sub panels : the one for the image and the one for the actions */
public class ControlPanel extends JPanel {

    private int CONTROLPANEL_WIDTH= ViewConstants.WINDOW_WIDTH-ViewConstants.BOARDPANEL_WITDH;
    private int CONTROLPANEL_HEIGHT= ViewConstants.WINDOW_HEIGHT;

    public ControlPanel(){
        this.setLayout(new BorderLayout(10,10));
        this.setPreferredSize(new Dimension(CONTROLPANEL_WIDTH, CONTROLPANEL_HEIGHT));


        this.add(actionsDisplay());
        this.add(imageDisplay(), BorderLayout.NORTH);


    }


    private JPanel imageDisplay(){
        JPanel iD = new JPanel();
        //iD.setLayout(new BoxLayout(iD,BoxLayout.PAGE_AXIS));
        /**Debugging only */
        iD.setOpaque(true);
        iD.setBackground(Color.BLUE);
        /**Forces the image display to be half of the control panel */
        iD.setPreferredSize(new Dimension(CONTROLPANEL_WIDTH,CONTROLPANEL_HEIGHT/4 ));

        /**Ajoute l'image de l'objet selectioné c'est un buton en cas de vouloir ajouter plusieurs images par personnage :
         *  quand on click une nouvelle image du meme personnage s'affiche
         *  */
        JButton imageEntity= new JButton();
        imageEntity.setPreferredSize(new Dimension(110,128));

        imageEntity.setBorderPainted(false);
        imageEntity.setOpaque(true);

        //imageEntity.setBackground(Color.cyan);
        //ImageIcon icon = new ImageIcon(new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB));

        Image spaceMarineToResize;
        try {
            spaceMarineToResize = ImageIO.read(new File("./resources/space_marine.jpg"));
            Image i= spaceMarineToResize.getScaledInstance(128,128,Image.SCALE_SMOOTH);
            Icon spaceMarine= new ImageIcon(i);
            imageEntity.setIcon(spaceMarine);
            imageEntity.setText("image");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //imageEntity.setAlignmentX(Component.CENTER_ALIGNMENT);
        iD.add(imageEntity);

        return iD;
    }

    private JPanel actionsDisplay(){
        JPanel aD= new JPanel();

        /**Debugging only */
        aD.setOpaque(true);

        //this.actionsDisplay.setBackground(Color.GREEN);
        /**Ajoute la bar à outils des actions de l'objet selectioné*/
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        tools.setOrientation(SwingConstants.VERTICAL);
        JLabel temp=new JLabel("Space Marine");
        tools.add(temp);
        tools.addSeparator();


        tools.add(new JButton("Move")); // TODO - add functionality!
        tools.add(new JButton("Mine")); // TODO - add functionality!
        tools.add(new JButton("Farm")); // TODO - add functionality!

        tools.addSeparator();
        tools.add(new JButton("Attack")); // TODO - add functionality!

        aD.add(tools);
        return aD;

    }
}
