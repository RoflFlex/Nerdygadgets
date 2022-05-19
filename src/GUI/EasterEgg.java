package GUI;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class EasterEgg {
    public EasterEgg(){
        OpenURL();
    }

    private void OpenURL() {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
