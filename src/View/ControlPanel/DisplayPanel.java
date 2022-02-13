package View.ControlPanel;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DisplayPanel extends JPanel {
    private Image displayImage;
    private MainControlPanel mainControlPanel;
    public static final int IMAGE_WIDTH=128;
    public static final int IMAGE_HEIGHT=128;

    public DisplayPanel(MainControlPanel mcp){
        //init by default to space marine
        this.mainControlPanel=mcp;
        //iD.setLayout(new BoxLayout(iD,BoxLayout.PAGE_AXIS));
        /**Debugging only */
        this.setOpaque(true);
        this.setBackground(Color.BLUE);
        /**Border work*/
        //ImageIcon icon = new ImageIcon("./resources/ControlPanel_Icons/f7.png");
        //Image resize=icon.getImage

        try {
            this.displayImage = ImageIO.read(new File("./resources/mars.jpg"));
            this.displayImage= this.displayImage.getScaledInstance(IMAGE_WIDTH,IMAGE_HEIGHT,Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateDisplayImage(Image i){
       this.displayImage=i;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D)g;
        g.drawImage(displayImage, this.getWidth()/2-IMAGE_WIDTH/2,this.getHeight()/2-IMAGE_HEIGHT/2,this);
    }

}
