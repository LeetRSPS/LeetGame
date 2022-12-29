package entity;

import main.GamePanel;
import tile.Tile;
import tile.TileManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;


public class HelmetPowerUp extends Entity {

    GamePanel gp;
    Tile[] tile;

    public HelmetPowerUp(GamePanel gp) {
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


        helmetPowerUpX = 256 + 120;
        helmetPowerUpY = rand2;
        speed = 3;
    }

    public void update() {
        int minimumHeight = (100 - gp.tileSize * 2 - 5);
        int maximumHeight = (175);

        Random rand = new Random();
        int rand2 = rand.nextInt(maximumHeight - minimumHeight) + minimumHeight;

        helmetPowerUpX -= speed;

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

    public void getPowerUpImage() {
        try {
            tile[5] = new Tile();
            tile[5].image =
                    ImageIO.read(TileManager.class.getResourceAsStream("/helmetPowerUp.png"));

            tile[6] = new Tile();
            tile[6].image =
                    ImageIO.read(TileManager.class.getResourceAsStream("/helmetPowerUpOff.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        //Top Left Icon showing if it's active
        if(Entity.helmetPowerUpEnabled) {
            g2.drawImage(tile[5].image, 16, 48, gp.tileSize * 2, gp.tileSize * 2, null);
        } else {
            g2.drawImage(tile[6].image, 16, 48, gp.tileSize * 2, gp.tileSize * 2, null);
        }

        //
        if(canSpawnPowerUp && powerUpDecider == 0) {
            g2.drawImage(tile[5].image, helmetPowerUpX, helmetPowerUpY, gp.tileSize, gp.tileSize, null);
        }


    }

}