package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class CapePowerUp extends Entity {

    GamePanel gp;

    public CapePowerUp(GamePanel gp) {
        this.gp = gp;
        getPowerUpImage();
        initializePowerUp();
    }

    public void initializePowerUp() {
        int minimumHeight = (100 - gp.tileSize * 2 - 5);
        int maximumHeight = (175);
        Random rand = new Random();
        int rand2 = rand.nextInt((maximumHeight - minimumHeight) + 1) + minimumHeight;

        capePowerUpX = 256 + 120;
        capePowerUpY = rand2;
        speed = 3;
    }

    public void update() {
        //Move cape to the left
        capePowerUpX -= speed;
        resetCapePowerUpPosition(canSpawnPowerUp, powerUpDecider, gp);
    }

    public void getPowerUpImage() {
        //Gets powerup images for Cape Powerup
        try {
            cape = ImageIO.read(Entity.class.getResourceAsStream("/cape.png"));
            capeInactive = ImageIO.read(Entity.class.getResourceAsStream("/inactiveCape.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        //Top Left Icon showing if it's active
        if(Entity.capePowerUpEnabled) {
            g2.drawImage(cape, 16, 16, gp.tileSize * 2, gp.tileSize * 2, null);
        } else {
            g2.drawImage(capeInactive, 16, 16, gp.tileSize * 2, gp.tileSize * 2, null);
        }

        //if powerUps can spawn && powerUpDecider == 1; "capePowerUp", draw powerUp in game world
        if(canSpawnPowerUp && powerUpDecider == 1) {
            g2.drawImage(cape, capePowerUpX, capePowerUpY, gp.tileSize, gp.tileSize, null);
        }
    }

    public static void resetCapePowerUpPosition(boolean canSpawnPowerUp, int powerUpDecider, GamePanel gp) {
        int minimumHeight = (100 - gp.tileSize * 2 - 5);
        int maximumHeight = (175);
        Random rand = new Random();
        int rand2 = rand.nextInt(maximumHeight - minimumHeight) + minimumHeight;

        if (canSpawnPowerUp && powerUpDecider == 1) {
            if (capePowerUpX <= 0) {
                capePowerUpX = 375 + 50;
                capePowerUpY = rand2;
            }
        } else {
            capePowerUpX = -20;
            capePowerUpY = -20;
        }
    }
}