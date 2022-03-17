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

    public static Image spaceShipTile;
    public static Image spaceMarineNorthTile;
    public static Image spaceMarineSouthTile;
    public static Image spaceMarineEastTile;
    public static Image spaceMarineWestTile;
    public static Image meteoriteTile;

    public static Image rock;

    public static Image heart;
    public static Image sword;

    public ImageManager() throws IOException {
        thumbnailAlien = ImageIO.read(new File("./resources/alien.jpeg")).getScaledInstance(THUMBNAIL_SIZE, THUMBNAIL_SIZE, Image.SCALE_SMOOTH);
        thumbnailSpaceMarine = ImageIO.read(new File("./resources/space_marine.jpg")).getScaledInstance(THUMBNAIL_SIZE, THUMBNAIL_SIZE, Image.SCALE_SMOOTH);
        thumbnailSpaceship = ImageIO.read(new File("./resources/spaceship.jpg")).getScaledInstance(THUMBNAIL_SIZE, THUMBNAIL_SIZE, Image.SCALE_SMOOTH);
        thumbnailMeteorite = ImageIO.read(new File("./resources/meteorite.jpg")).getScaledInstance(THUMBNAIL_SIZE, THUMBNAIL_SIZE, Image.SCALE_SMOOTH);

        spaceShipTile = ImageIO.read(new File("resources/BoardAssets/spaceship.png"));
        meteoriteTile = ImageIO.read(new File("resources/BoardAssets/meteorite.png"));
        spaceMarineNorthTile = ImageIO.read(new File("resources/BoardAssets/SpaceMarineN.png"));
        spaceMarineSouthTile = ImageIO.read(new File("resources/BoardAssets/SpaceMarineS.png"));
        spaceMarineEastTile = ImageIO.read(new File("resources/BoardAssets/SpaceMarineE.png"));
        spaceMarineWestTile = ImageIO.read(new File("resources/BoardAssets/SpaceMarineW.png"));

        rock=ImageIO.read(new File("resources/ScorePanelAssets/rock.png")).getScaledInstance(50,50,Image.SCALE_SMOOTH);

        heart = ImageIO.read(new File("resources/ControlPanelAssets/heart.png"));
        sword = ImageIO.read(new File("resources/ControlPanelAssets/sword.png"));

    }

    public static Image getTileImage(String ressourceName){
        switch (ressourceName){
            case "Spaceship" : return spaceShipTile;
            case "Meteorite" : return meteoriteTile;
            case "SpaceMarineNORTH": return spaceMarineNorthTile;
            case "SpaceMarineSOUTH": return spaceMarineSouthTile;
            case "SpaceMarineWEST": return spaceMarineWestTile;
            case "SpaceMarineEAST": return spaceMarineEastTile;
        }
        return null;
    }

    public static Image getThumbnail(String ressourceName){
        switch (ressourceName){
            case "Spaceship" : return thumbnailSpaceship;
            case "Meteorite" : return thumbnailMeteorite;
            case "SpaceMarine": return thumbnailSpaceMarine;
            case "Alien" : return thumbnailAlien;
        }
        return null;
    }

    public static Image getControlPanelAssets(String ressourceName){
        switch (ressourceName){
            case "Heart" : return heart;
            case "Sword" : return sword;
        }
        return null;
    }

}
