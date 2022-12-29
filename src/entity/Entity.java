package entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Entity {

    public static int x,y;
    public static int pipex, pipey, pipex2, pipey2, pipeHeight;
    public static int powerUpX, powerUpY;
    public int speed;
    public static boolean collisionOn = false;
    public static int powerUpCounter = 0;
    public static boolean capePowerUpEnabled = false;
    public static boolean canSpawnPowerUp = true;
    public BufferedImage playerframe0, playerframe2, playercapeframe1, playercapeframe2;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public static int score = 0;

    private static ArrayList<Entity> entities = new ArrayList<>();

    public Entity() {
        entities.add(this);
    }

    public void update() {
        // Update the position of the entity
        x += speed;
    }

    public static void updateAll() {
        for (Entity entity : entities) {
            entity.update();
        }
    }
}
