package entity;

import main.GamePanel;
import tile.Tile;
import tile.TileManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;


public class CapePowerUp extends Entity {

    GamePanel gp;
    Tile[] tile;

    public CapePowerUp(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
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
        int minimumHeight = (100 - gp.tileSize * 2 - 5);
        int maximumHeight = (175);

        Random rand = new Random();
        int rand2 = rand.nextInt(maximumHeight - minimumHeight) + minimumHeight;

        capePowerUpX -= speed;

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

    public void getPowerUpImage() {
        try {
            tile[3] = new Tile();
            tile[3].image =
                    ImageIO.read(TileManager.class.getResourceAsStream("/cape.png"));

            tile[4] = new Tile();
            tile[4].image =
                    ImageIO.read(TileManager.class.getResourceAsStream("/inactiveCape.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        //Top Left Icon showing if it's active
        if(Entity.capePowerUpEnabled) {
            g2.drawImage(tile[3].image, 16, 16, gp.tileSize * 2, gp.tileSize * 2, null);
        } else {
            g2.drawImage(tile[4].image, 16, 16, gp.tileSize * 2, gp.tileSize * 2, null);
        }

        ////
        if(canSpawnPowerUp && powerUpDecider == 1) {
            g2.drawImage(tile[3].image, capePowerUpX, capePowerUpY, gp.tileSize, gp.tileSize, null);
        }
    }

}