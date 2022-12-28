package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    public boolean mousePressed;
    public boolean canMove = true;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int button = e.getButton();

        if(button == MouseEvent.BUTTON1 && canMove) {
            mousePressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int button = e.getButton();

        if(button == MouseEvent.BUTTON1 && canMove) {
            mousePressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // This method is called when the mouse enters a component.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // This method is called when the mouse exits a component.
    }
}