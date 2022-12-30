package entity;

import java.awt.image.BufferedImage;

public class Entity {

    //Global Variables
    public int speed;
    public static int score = 0;
    public static boolean collisionOn = false;

    //Player Variables
    public static int x,y;

    //Pipe Variables
    public static int pipex, pipey, pipex2, pipey2;

    //PowerUp Variables
    public static int capePowerUpX, capePowerUpY;
    public static int helmetPowerUpX, helmetPowerUpY;
    public static int powerUpDecider = 0;
    public static int powerUpCounter = 0;
    public static boolean capePowerUpEnabled = false;
    public static boolean helmetPowerUpEnabled = false;
    public static boolean canSpawnPowerUp = true;

    //Sprites & Sprite Counters
    public BufferedImage playerframe0, playerframe2, playercapeframe1, playercapeframe2, playerhelmetframe1, playerhelmetframe2, image;
    public BufferedImage pipe, upsideDownPipe, pipeExtension;
    public BufferedImage helmet, cape, helmetInactive, capeInactive;
    public int spriteCounter = 0;
    public int spriteNumber = 1;

    public Entity() {
    }

    public void update() {
    }

    public static void updateAll() {
    }
}
