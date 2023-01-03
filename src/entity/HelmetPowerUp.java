package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class HelmetPowerUp extends Entity {

    GamePanel gp;

    public HelmetPowerUp(GamePanel gp) {
        this.gp = gp;
        getPowerUpImage();
        initializePowerUp();
    }

    public void initializePowerUp() {
        int minimumHeight = (100 - gp.tileSize * 2 - 5);
        int maximumHeight = (175);
        Random rand = new Random();
        int rand2 = rand.nextInt((maximumHeight - minimumHeight) + 1) + minimumHeight;

        helmetPowerUpX = 256 + 120;
        helmetPowerUpY = rand2;
        speed = 3;
    }

    public void update() {
        helmetPowerUpX -= speed;

        checkIfHelmetExists();
        resetHelmetPowerUpPosition();
    }

    public void getPowerUpImage() {
        //Get helmet powerup images
        try {
            helmet = ImageIO.read(Entity.class.getResourceAsStream("/helmetPowerUp.png"));
            helmetInactive = ImageIO.read(Entity.class.getResourceAsStream("/helmetPowerUpOff.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        //Top Left Icon showing if it's active
        if(Entity.helmetPowerUpEnabled) {
            g2.drawImage(helmet, 2, 141, gp.tileSize * 2, gp.tileSize * 2, null);
        } else {
            g2.drawImage(helmetInactive, 2, 141, gp.tileSize * 2, gp.tileSize * 2, null);
        }

        //if powerUps can spawn && powerUpDecider == 0; "helmetPowerUp", draw powerUp in game world
        if(canSpawnPowerUp && powerUpDecider == 0) {
            g2.drawImage(helmet, helmetPowerUpX, helmetPowerUpY, gp.tileSize, gp.tileSize, null);
        }
    }

    public void resetHelmetPowerUpPosition() {
        int minimumHeight = (100 - gp.tileSize * 2 - 5);
        int maximumHeight = (175);
        Random rand = new Random();
        int rand2 = rand.nextInt(maximumHeight - minimumHeight) + minimumHeight;

        if (canSpawnPowerUp && powerUpDecider == 0) {
            if (helmetPowerUpX <= 0) {
                helmetPowerUpX = 375 + 50;
                helmetPowerUpY = rand2;
            }
        } else {
            helmetPowerUpX = -20;
            helmetPowerUpY = -20;
        }
    }

    public void checkIfHelmetExists() {
        if(helmetPowerUpX > 0 && powerUpDecider == 0) {
            helmetPowerUpExists = true;
        } else {
            helmetPowerUpExists = false;
        }
    }
}