package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    //Initialize Variables for Keyhandler
    public boolean spacePressed;
    public boolean shiftPressed;
    public boolean canMove = true;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_SPACE && canMove) {
            spacePressed = true;
        }

        if (keyCode == KeyEvent.VK_SHIFT && canMove) {
            shiftPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_SPACE && canMove) {
            spacePressed = false;
        }

        if (keyCode == KeyEvent.VK_SHIFT && canMove) {
            shiftPressed = false;
        }
    }
}
