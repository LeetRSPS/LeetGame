package entity;

import main.GamePanel;
import main.KeyHandler;
import tile.Tile;
import tile.TileManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

     GamePanel gp;
     KeyHandler keyH;
     Tile[] tile;
     public int score;

    public Player(GamePanel gp, KeyHandler keyHandler) {

        this.gp = gp;
        this.keyH = keyHandler;
        tile = new Tile[10];
        score = 0;
        getPlayerImage();
        initializePlayer();
    }

    public void initializePlayer() {
        x = 100;
        y = 100;
        score = 0;
        speed = 3;
    }

    public void update() {
        checkPlayerInput();
        checkPlayerPosition();
        scoreHandler();
        animatePlayerFrames();
    }

    public void getPlayerImage() {
        try {
           playerframe0 = ImageIO.read(TileManager.class.getResourceAsStream("/playerframe.png"));
           playerframe1 = ImageIO.read(TileManager.class.getResourceAsStream("/playerframe1.png"));
           playerframe2 = ImageIO.read(TileManager.class.getResourceAsStream("/playerframe2.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch(spriteNumber) {
            case 1:
                image = playerframe0;
                break;
            case 2:
                image = playerframe2;
                break;
        }

        //Draw player sprite
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

        //Draw Score Tab
        g2.fillRect(0, 0, gp.tileSize * 4 + 10, gp.tileSize);
        g2.setColor(Color.white);
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 13));
        g2.drawString(("Score: " + score), 5 , 12);
    }

    public void drawDeath(Graphics2D g3){
        gp.gameFinished = false;
        keyH.canMove = false;

        //Death message
        g3.setColor(Color.white);
        g3.drawString("Oh dear... you have died.", gp.screenWidth / 4, gp.screenHeight / 2);

        //Death Box
        g3.setColor(Color.red);
        g3.fillRect(65,50,gp.screenWidth / 2,gp.screenHeight / 4);
        //Death Box Score Text
        g3.setColor(Color.white);
        g3.drawString("You scored: " + score,85, 88);
    }

    public void checkPlayerInput() {
        if (keyH.spacePressed) {
            y -= speed * 2;
        } else {
            y += speed;
        }
    }

    public void checkPlayerPosition() {
        if(y > gp.screenHeight - 40 || y < -gp.screenHeight + 250) {
            gp.gameFinished = true;
        }
    }

    public void scoreHandler() {
        if(!gp.gameFinished) {
            score++;
        }
    }

    public void animatePlayerFrames() {
        spriteCounter++;
        if(spriteCounter > 10) {
            if(spriteNumber == 1) {
                spriteNumber = 2;
            } else if(spriteNumber == 2) {
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }
    }
}
