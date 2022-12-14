package entity;

import main.GamePanel;

import java.awt.image.BufferedImage;

public class Entity {

    GamePanel gp;
    Thread gameThread;

    public int x,y,x2,y2;
    public int speed;

    public int pipePosX;

    public BufferedImage playerframe0, playerframe1, playerframe2;
    public int spriteCounter = 0;
    public int spriteNumber = 1;

    public int score = 0;

    public void update() {

    }
}
