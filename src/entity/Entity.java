package entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Entity {


    //Entity Variables
    public static int x,y;
    public static int pipex, pipey, pipex2, pipey2, pipeHeight;
    public static int capePowerUpX, capePowerUpY;
    public static int helmetPowerUpX, helmetPowerUpY;
    public static int powerUpDecider = 0;
    public int speed;
    public static boolean collisionOn = false;

    //Sprites & Sprite Counters
    public BufferedImage playerframe0, playerframe2, playercapeframe1, playercapeframe2, playerhelmetframe1, playerhelmetframe2;
    public int spriteCounter = 0;
    public int spriteNumber = 1;


    //Powerup Settings
    public static int powerUpCounter = 0;
    public static boolean capePowerUpEnabled = false;
    public static boolean helmetPowerUpEnabled = false;
    public static boolean canSpawnPowerUp = true;
    public static int score = 0;

    public Entity() {
    }

    public void update() {
    }

    public static void updateAll() {
    }
}
