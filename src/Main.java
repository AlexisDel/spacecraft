import Launcher.LauncherWindow;
import View.ImageManager;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        // Chargement des images
        try {
            new ImageManager();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new LauncherWindow();
    }
}
