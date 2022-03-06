package View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageManager {

    public static int THUMBNAIL_SIZE = 128;

    public static Image thumbnailMars;
    public static Image thumbnailAlien;
    public static Image thumbnailSpaceMarine;
    public static Image thumbnailSpaceship;
    public static Image thumbnailMeteorite;

    public ImageManager() throws IOException {
        thumbnailMars = ImageIO.read(new File("./resources/mars.jpg")).getScaledInstance(THUMBNAIL_SIZE,THUMBNAIL_SIZE,Image.SCALE_SMOOTH);
        thumbnailAlien = ImageIO.read(new File("./resources/alien.jpeg")).getScaledInstance(THUMBNAIL_SIZE,THUMBNAIL_SIZE,Image.SCALE_SMOOTH);
        thumbnailSpaceMarine = ImageIO.read(new File("./resources/space_marine.jpg")).getScaledInstance(THUMBNAIL_SIZE,THUMBNAIL_SIZE,Image.SCALE_SMOOTH);
        thumbnailSpaceship = ImageIO.read(new File("./resources/spaceship.jpg")).getScaledInstance(THUMBNAIL_SIZE,THUMBNAIL_SIZE,Image.SCALE_SMOOTH);
        thumbnailMeteorite = ImageIO.read(new File("./resources/meteorite.jpg")).getScaledInstance(THUMBNAIL_SIZE,THUMBNAIL_SIZE,Image.SCALE_SMOOTH);
    }

}
