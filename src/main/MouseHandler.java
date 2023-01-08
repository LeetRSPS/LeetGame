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

        if(GamePanel.gameState == GamePanel.deadBirdState) {
            //Reset Button
            if(e.getX() > 96 && e.getX() < 161 && e.getY() > 159 && e.getY() < 191) {
                GamePanel gamePanel = new GamePanel();
                gamePanel.initializeGameSettings();
            }
            //Main Menu Button
            if(e.getX() > 89 && e.getX() < 166 && e.getY() > 192 && e.getY() < 209) {
                GamePanel.gameState = GamePanel.titleState;
            }
        }

        if(GamePanel.gameState == GamePanel.titleState) {
            //Play Button
            if(e.getX() > 94 && e.getX() < 161 && e.getY() > 124 && e.getY() < 157) {
                GamePanel.gameState = GamePanel.playState;
                GamePanel gamePanel = new GamePanel();
                gamePanel.initializeGameSettings();
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
        if(GamePanel.gameState == GamePanel.pauseState) {

            //Reset Game Button
            if (e.getX() > 9 && e.getX() < 76 && e.getY() > 178 && e.getY() < 198) {
                GamePanel.gameState = GamePanel.playState;
                GamePanel gamePanel = new GamePanel();
                gamePanel.initializeGameSettings();
            }
            //Resume Game Button
            if (e.getX() > 94 && e.getX() < 162 && e.getY() > 179 && e.getY() < 196) {
                GamePanel.gameState = GamePanel.playState;
            }
            //Main Menu Button
            if (e.getX() > 179 && e.getX() < 255 && e.getY() > 179 && e.getY() < 199) {
                GamePanel.gameState = GamePanel.titleState;
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