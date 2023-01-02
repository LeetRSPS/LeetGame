package main;

import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    GamePanel gp;

    //Initialize Variables for MouseHandler
    public boolean mouse1Pressed;
    public boolean mouse3Pressed;
    public boolean canMove = true;
    public boolean mouseOver = false;
    public boolean mousePressed = false;

    @Override
    public void mouseClicked(MouseEvent e) {
        if(GamePanel.gameState == GamePanel.pauseState) {

            gp = new GamePanel();

            //Continue Game
            if (e.getX() > 94 && e.getX() < 160 && e.getY() > 180 && e.getY() < 200) {
               GamePanel.gameState = GamePanel.playState;
            }

            //Reset Game
            if(e.getX() > 10 && e.getX() < 75 && e.getY() > 180 && e.getY() < 200) {
                gp.initializeGameSettings();
                GamePanel.gameState = GamePanel.playState;
            }
        }
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