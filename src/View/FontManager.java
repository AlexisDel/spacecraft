package View;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontManager {
    public static Font Dune2000;
    public static Font Dune2000_20;

    public FontManager() throws IOException, FontFormatException {
        Dune2000 = Font.createFont(Font.TRUETYPE_FONT, new File("./resources/Fonts/Dune2000.ttf")).deriveFont(15f);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //register the font
        ge.registerFont(Dune2000);

        Dune2000_20 = Font.createFont(Font.TRUETYPE_FONT, new File("./resources/Fonts/Dune2000.ttf")).deriveFont(20f);
        //register the font
        ge.registerFont(Dune2000_20);
    }
}
