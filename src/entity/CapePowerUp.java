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

        checkIfCapeExists();
        resetCapePowerUpPosition();
    }

    public void getPowerUpImage() {
        //Gets powerup images for Cape Powerup
        try {
            //Default Cape Images
            cape = ImageIO.read(Entity.class.getResourceAsStream("/cape.png"));
            capeInactive = ImageIO.read(Entity.class.getResourceAsStream("/inactiveCape.png"));

            //Charged Cape Images
            capefullcharge = ImageIO.read(Entity.class.getResourceAsStream("/capeFullCharge.png"));
            cape75charge = ImageIO.read(Entity.class.getResourceAsStream("/cape75Charge.png"));
            cape50charge = ImageIO.read(Entity.class.getResourceAsStream("/cape50charge.png"));
            cape25charge = ImageIO.read(Entity.class.getResourceAsStream("/cape25charge.png"));
            cape10charge = ImageIO.read(Entity.class.getResourceAsStream("/cape10charge.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        //Bottom Left Icon showing if it's active
        if(Entity.capePowerUpEnabled) {
            g2.drawImage(getCapeChargeImage(), 2, 172, gp.tileSize * 2, gp.tileSize * 2, null);
        } else {
            g2.drawImage(capeInactive, 2, 172, gp.tileSize * 2, gp.tileSize * 2, null);
        }

        //if powerUps can spawn && powerUpDecider == 1; "capePowerUp", draw powerUp in game world
        if(canSpawnPowerUp && powerUpDecider == 1) {
            g2.drawImage(cape, capePowerUpX, capePowerUpY, gp.tileSize, gp.tileSize, null);
        }
    }

    public static void resetCapePowerUpPosition() {
        int minimumHeight = (100 - 16 * 2 - 5);
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

    public Image getCapeChargeImage() {
        if(capePowerUpCharge >= 0 && capePowerUpCharge <= 74) {
            image = capefullcharge;
        }
        if(capePowerUpCharge >= 75 && capePowerUpCharge <= 150) {
            image = cape75charge;
        }
        if(capePowerUpCharge > 151 &&  capePowerUpCharge <= 224) {
            image = cape50charge;
        }
        if(capePowerUpCharge > 225 && capePowerUpCharge <= 270) {
            image = cape25charge;
        }
        if(capePowerUpCharge > 270) {
            image = cape10charge;
        }

        return image;
    }

    public void checkIfCapeExists() {
        if(Entity.capePowerUpX > 0 && Entity.powerUpDecider == 1) {
            Entity.capePowerUpExists = true;
        } else {
            Entity.capePowerUpExists = false;
        }
    }
}