import Game.View.RessourceManager;
import Launcher.LauncherWindow;

import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        // Chargement des ressources
        try {
            new RessourceManager();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }


        new LauncherWindow();
    }
}
