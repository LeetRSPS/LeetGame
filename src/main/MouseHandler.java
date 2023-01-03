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
            //Quit Game
            if(e.getX() > 180 && e.getX() < 246 && e.getY() > 180 && e.getY() < 200) {
                System.exit(0);
            }
        }

        if(GamePanel.gameState == GamePanel.titleState) {
            //Play Button
            if(e.getX() > 94 && e.getX() < 161 && e.getY() > 124 && e.getY() < 157) {
                GamePanel.gameState = GamePanel.playState;
                GamePanel.switchGameState = true;
            }
            //Change Bird
            if(e.getX() > 10 && e.getX() < 102 && e.getY() > 180 && e.getY() < 198) {
                GamePanel.gameState = GamePanel.changeBirdState;
            }
            //Change Name
            if(e.getX() > 154 && e.getX() < 247 && e.getY() > 180 && e.getY() < 198) {
                System.out.println("Change Name Button Clicked");
            }
        }

        if(GamePanel.gameState == GamePanel.changeBirdState) {
            //Back Button
            if(e.getX() > 1 && e.getX() < 32 && e.getY() > 0 && e.getY() < 30) {
                GamePanel.gameState = GamePanel.titleState;
            }

            //Click Blue Bird Box
            if(e.getX() > 58 && e.getX() < 118 && e.getY() > 113 && e.getY() < 168) {
                GamePanel.selectedBird = GamePanel.blueBird;
            }

            //Click Red Bird Box
            if(e.getX() > 153 && e.getX() < 213 && e.getY() > 113 && e.getY() < 168) {
                GamePanel.selectedBird = GamePanel.redBird;
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