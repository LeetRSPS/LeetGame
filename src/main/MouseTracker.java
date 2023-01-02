package main;

import java.awt.event.*;

public class MouseTracker implements MouseMotionListener {

    public static int x;
    public static int y;

    public void mouseMoved(MouseEvent event) {
        x = event.getX();
        y = event.getY();
    }

    public void mouseDragged(MouseEvent event) {
    }

}