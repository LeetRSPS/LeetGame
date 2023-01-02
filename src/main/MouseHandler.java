package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    //Initialize Variables for MouseHandler
    public boolean mouse1Pressed;
    public boolean mouse3Pressed;
    public boolean canMove = true;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int button = e.getButton();

        if(button == MouseEvent.BUTTON1 && canMove) {
            mouse1Pressed = true;
        }

        if(button == MouseEvent.BUTTON3 && canMove) {
            mouse3Pressed = true;
        }

        if(GamePanel.debugModeOn) {
            int x = e.getX();
            int y = e.getY();
            System.out.println("Mouse location | X = " +x+ " | Y = " +y);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int button = e.getButton();

        if(button == MouseEvent.BUTTON1 && canMove) {
            mouse1Pressed = false;
        }

        if(button == MouseEvent.BUTTON3 && canMove) {
            mouse3Pressed = false;
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