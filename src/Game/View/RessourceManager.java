package Game.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RessourceManager {

    public RessourceManager() throws IOException, FontFormatException {
        new ImageManager();
        new FontManager();
    }

    public static class ImageManager {

        public static int THUMBNAIL_SIZE = 140;
        public static int BORDER_SIZE = 160;

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
        public static Image alienNorthTile;
        public static Image alienSouthTile;
        public static Image alienEastTile;
        public static Image alienWestTile;
        public static Image meteoriteTile;
        public static Image sandTile;

        public static Image rock;

        public static Image heart;
        public static Image sword;

        public static Image thumbnailBorder;

        public static Image launcherBackground;
        public static Image launcherLogo;
        public static Image startButton;
        public static Image clickedStartButton;


        public static Image mountain0;
        public static Image mountain1;
        public static Image mountain2;
        public static Image mountain3;
        public static Image mountain4;
        public static Image mountain5;
        public static Image mountain6;
        public static Image mountain7;
        public static Image mountain8;
        public static Image mountain9;
        public static Image mountain10;
        public static Image mountain11;
        public static Image mountain12;
        public static Image mountain13;
        public static Image mountain14;
        public static Image mountain15;

        public ImageManager() throws IOException {
            thumbnailAlien = ImageIO.read(new File("./resources/ControlPanelAssets/alien.jpeg")).getScaledInstance(THUMBNAIL_SIZE, THUMBNAIL_SIZE, Image.SCALE_SMOOTH);
            thumbnailSpaceMarine = ImageIO.read(new File("./resources/ControlPanelAssets/space_marine.jpg")).getScaledInstance(THUMBNAIL_SIZE, THUMBNAIL_SIZE, Image.SCALE_SMOOTH);
            thumbnailSpaceship = ImageIO.read(new File("./resources/ControlPanelAssets/spaceship.jpg")).getScaledInstance(THUMBNAIL_SIZE, THUMBNAIL_SIZE, Image.SCALE_SMOOTH);
            thumbnailMeteorite = ImageIO.read(new File("./resources/ControlPanelAssets/meteorite.jpg")).getScaledInstance(THUMBNAIL_SIZE, THUMBNAIL_SIZE, Image.SCALE_SMOOTH);
            rock = ImageIO.read(new File("resources/ControlPanelAssets/rock.png")).getScaledInstance(25,25,Image.SCALE_SMOOTH);


            spaceShipTile = ImageIO.read(new File("resources/BoardAssets/spaceship.png"));
            meteoriteTile = ImageIO.read(new File("resources/BoardAssets/meteorite.png"));
            sandTile = ImageIO.read(new File("resources/BoardAssets/sand.png"));
            spaceMarineNorthTile = ImageIO.read(new File("resources/BoardAssets/SpaceMarineN.png"));
            spaceMarineSouthTile = ImageIO.read(new File("resources/BoardAssets/SpaceMarineS.png"));
            spaceMarineEastTile = ImageIO.read(new File("resources/BoardAssets/SpaceMarineE.png"));
            spaceMarineWestTile = ImageIO.read(new File("resources/BoardAssets/SpaceMarineW.png"));

            alienNorthTile = ImageIO.read(new File("resources/BoardAssets/AlienN.png"));
            alienSouthTile = ImageIO.read(new File("resources/BoardAssets/AlienS.png"));
            alienEastTile = ImageIO.read(new File("resources/BoardAssets/AlienE.png"));
            alienWestTile = ImageIO.read(new File("resources/BoardAssets/AlienW.png"));

            heart = ImageIO.read(new File("resources/ControlPanelAssets/heart.png"));
            sword = ImageIO.read(new File("resources/ControlPanelAssets/sword.png"));
            thumbnailBorder = ImageIO.read(new File("resources/ControlPanelAssets/border.png"));

            launcherBackground = ImageIO.read(new File("resources/LauncherAssets/background.png"));
            launcherLogo = ImageIO.read(new File("resources/LauncherAssets/logo.png"));
            startButton = ImageIO.read(new File("resources/LauncherAssets/start.png"));
            clickedStartButton = ImageIO.read(new File("resources/LauncherAssets/start_clicked.png"));

            mountain0 = ImageIO.read(new File("resources/BoardAssets/Mountain_0.png"));
            mountain1 = ImageIO.read(new File("resources/BoardAssets/Mountain_1.png"));
            mountain2 = ImageIO.read(new File("resources/BoardAssets/Mountain_2.png"));
            mountain3 = ImageIO.read(new File("resources/BoardAssets/Mountain_3.png"));
            mountain4 = ImageIO.read(new File("resources/BoardAssets/Mountain_4.png"));
            mountain5 = ImageIO.read(new File("resources/BoardAssets/Mountain_5.png"));
            mountain6 = ImageIO.read(new File("resources/BoardAssets/Mountain_6.png"));
            mountain7 = ImageIO.read(new File("resources/BoardAssets/Mountain_7.png"));
            mountain8 = ImageIO.read(new File("resources/BoardAssets/Mountain_8.png"));
            mountain9 = ImageIO.read(new File("resources/BoardAssets/Mountain_9.png"));
            mountain10 = ImageIO.read(new File("resources/BoardAssets/Mountain_10.png"));
            mountain11 = ImageIO.read(new File("resources/BoardAssets/Mountain_11.png"));
            mountain12 = ImageIO.read(new File("resources/BoardAssets/Mountain_12.png"));
            mountain13 = ImageIO.read(new File("resources/BoardAssets/Mountain_13.png"));
            mountain14 = ImageIO.read(new File("resources/BoardAssets/Mountain_14.png"));
            mountain15 = ImageIO.read(new File("resources/BoardAssets/Mountain_15.png"));
        }

        public static Image getTileImage(String ressourceName){
            switch (ressourceName){
                case "Spaceship" : return spaceShipTile;
                case "Meteorite" : return meteoriteTile;
                case "Sand" : return sandTile;
                case "SpaceMarineNORTH": return spaceMarineNorthTile;
                case "SpaceMarineSOUTH": return spaceMarineSouthTile;
                case "SpaceMarineWEST": return spaceMarineWestTile;
                case "SpaceMarineEAST": return spaceMarineEastTile;
                case "AlienNORTH": return alienNorthTile;
                case "AlienSOUTH": return alienSouthTile;
                case "AlienWEST": return alienWestTile;
                case "AlienEAST": return alienEastTile;
                case "Mountain_0": return mountain0;
                case "Mountain_1": return mountain1;
                case "Mountain_2": return mountain2;
                case "Mountain_3": return mountain3;
                case "Mountain_4": return mountain4;
                case "Mountain_5": return mountain5;
                case "Mountain_6": return mountain6;
                case "Mountain_7": return mountain7;
                case "Mountain_8": return mountain8;
                case "Mountain_9": return mountain9;
                case "Mountain_10": return mountain10;
                case "Mountain_11": return mountain11;
                case "Mountain_12": return mountain12;
                case "Mountain_13": return mountain13;
                case "Mountain_14": return mountain14;
                case "Mountain_15": return mountain15;
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
                case "Rock": return rock;
            }
            return null;
        }
    }

    public static class FontManager {
        public static Font Dune2000_15;
        public static Font Dune2000_20;
        public static Font zeroTwos_12;


        public FontManager() throws IOException, FontFormatException {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            Dune2000_15 = Font.createFont(Font.TRUETYPE_FONT, new File("./resources/Fonts/Dune2000.ttf")).deriveFont(15f);
            //register the font
            ge.registerFont(Dune2000_15);

            Dune2000_20 = Font.createFont(Font.TRUETYPE_FONT, new File("./resources/Fonts/Dune2000.ttf")).deriveFont(20f);
            //register the font
            ge.registerFont(Dune2000_20);

            zeroTwos_12 = Font.createFont(Font.TRUETYPE_FONT, new File("./resources/Fonts/ZeroTwos.ttf")).deriveFont(12f);
            //register the font
            ge.registerFont(zeroTwos_12);
        }
    }
}
