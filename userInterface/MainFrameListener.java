package userInterface;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrameListener extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}
