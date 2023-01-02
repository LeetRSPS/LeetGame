package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    //Initialize Variables for Keyhandler
    public boolean spacePressed;
    public boolean shiftPressed;
    public boolean qPressed = false;
    public boolean wPressed;
    public boolean ePressed;
    public boolean ctrlPressed;
    public boolean tildaPressed;
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
        if (keyCode == KeyEvent.VK_CONTROL) {
            ctrlPressed = true;
        }
        if (keyCode == KeyEvent.VK_BACK_QUOTE) {
            tildaPressed = true;
        }
        if(keyCode == KeyEvent.VK_Q) {
            qPressed = true;
        }
        if(keyCode == KeyEvent.VK_W) {
            wPressed = true;
        }
        if(keyCode == KeyEvent.VK_E) {
            ePressed = true;
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
        if (keyCode == KeyEvent.VK_CONTROL) {
            ctrlPressed = false;
        }
        if (keyCode == KeyEvent.VK_BACK_QUOTE) {
            tildaPressed = false;
        }
        if (keyCode == KeyEvent.VK_W) {
            wPressed = false;
        }
        if (keyCode == KeyEvent.VK_E) {
            ePressed = false;
        }
        if(keyCode == KeyEvent.VK_Q) {
            qPressed = false;
        }
    }
}
