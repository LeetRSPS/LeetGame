package entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Entity {

    public static int x,y;
    public static int pipex, pipey, pipex2, pipey2, pipeHeight;
    public static int powerUpX, powerUpY;
    public int speed;
    public static boolean collisionOn = false;
    public static boolean displayPowerUp = true;
    public static int powerUpCounter = 0;
    public static boolean powerUpOn = false;
    public BufferedImage playerframe0, playerframe1, playerframe2;
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
