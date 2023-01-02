package entity;

import main.GamePanel;
import highscores.HighScoreReader;
import highscores.HighScoreWriter;
import main.KeyHandler;
import main.MouseHandler;
import main.MouseTracker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Interfaces extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    MouseHandler mouseH;

    public Interfaces(GamePanel gp, KeyHandler keyH, MouseHandler mouseH) {
        this.gp = gp;
        this.keyH = keyH;
        this.mouseH = mouseH;
    }

    public void update() {
        animateInterfaceFrames();
        getInterfaceSpriteImage();
    }

    public void drawPauseScreen(Graphics2D g2) {

        switch (interfaceSpriteNumber) {
            case 1:
                birdFrame = birdFrame1;
                break;
            case 2:
                birdFrame = birdFrame2;
                break;
        }

            //Main Interface Sprite
            g2.drawImage(pauseInterfaceIcon,0, 0, null);
            g2.drawImage(birdFrame, 60, 60, null);

            //Button Area Middle
            Color myGoldColor = new Color(234, 245, 0);
            g2.setColor(Color.BLACK);
            g2.fillRect(95, 180, 65, 16);
            g2.setColor(myGoldColor);
            g2.drawRect(95, 180, 65, 16);
            g2.setColor(myGoldColor);
            g2.drawString("Resume", 105, 193);

            //Button Area Middle
            g2.setColor(Color.BLACK);
            g2.fillRect(10, 180, 65, 16);
            g2.setColor(myGoldColor);
            g2.drawRect(10, 180, 65, 16);
            g2.setColor(myGoldColor);
            g2.drawString("Reset", 25, 193);

            //Button Area Right
            g2.setColor(Color.BLACK);
            g2.fillRect(180, 180, 65, 16);
            g2.setColor(myGoldColor);
            g2.drawRect(180, 180, 65, 16);
            g2.setColor(myGoldColor);
            g2.drawString("Quit", 200, 193);

            //Main Text
            g2.setColor(Color.BLACK);
            g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
            g2.drawString("Flappy Leet", gp.screenWidth / 6 + 2, gp.screenHeight / 2 - 80 + 2);
            g2.setColor(Color.BLUE);
            g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
            g2.drawString("Flappy Leet", gp.screenWidth / 6, gp.screenHeight / 2 - 80);
    }

    public void drawDeath(Graphics2D g3) {
        HighScoreReader.readHighScore();
        HighScoreWriter.writeFile();

        gp.gameFinished = false;
        keyH.canMove = false;
        mouseH.canMove = false;

        //Death message
        g3.setColor(Color.white);
        g3.drawString("Oh dear... you have died.", gp.screenWidth / 4, gp.screenHeight / 2);

        //Death Box
        g3.setColor(Color.red);
        g3.fillRect(65, 50, gp.screenWidth / 2, gp.screenHeight / 4);

        //Death Box Score Text
        g3.setColor(Color.white);
        g3.drawString("You scored: " + score, 85, 88);

        //High Scores Box + Text
        g3.fillRect(gp.screenWidth / 5 - 5, gp.screenHeight / 2 + 10, gp.tileSize * 10, gp.tileSize * 4);
        g3.setColor(Color.BLACK);
        g3.drawString("High Scores", 95, 150);

        //Highscores List
        g3.setFont(new Font("SansSerif", Font.PLAIN, 10));
        g3.drawString("1st: " + HighScoreReader.firstPlaceValue, 50, 160);
        g3.drawString("2nd: " + HighScoreReader.secondPlaceValue, 50, 170);
        g3.drawString("3rd: " + HighScoreReader.thirdPlaceValue, 50, 180);

        HighScoreWriter.scoreWritten = false;
    }

    public void drawDebugPanel(Graphics2D g2) {
        if(canDrawDebugPanel) {
            g2.setColor(Color.DARK_GRAY);
            g2.fillRect(0, 15,gp.tileSize * 10, gp.tileSize * 3);
            g2.setColor(Color.GREEN);
            g2.drawString("X: " + MouseTracker.x, 2, 28);
            g2.drawString("Y: " + MouseTracker.y, 2, 44);
            g2.drawString("I#: " + amtOfItemsThatExist, 2, 60);
            //g4.drawString("canSpawnHelmet: " + canSpawnHelmet, 2, 44);
            //g4.drawString("canSpawnCape: " + canSpawnCape, 2, 60);
            //g4.drawString("Cape Exists: " + capePowerUpExists, 2, 76);
            //g4.drawString("Helmet Exists: " + helmetPowerUpExists, 2, 92);
            //g4.drawString("CanSpawnPowerUp: " +canSpawnPowerUp, 2, 108);
        }
    }

    public void getInterfaceSpriteImage() {
        try {
            birdFrame1 = ImageIO.read(Entity.class.getResourceAsStream("/pauseBird.png"));
            birdFrame2 = ImageIO.read(Entity.class.getResourceAsStream("/pauseBird2.png"));
            pauseInterfaceIcon = ImageIO.read(Entity.class.getResourceAsStream("/pauseInterfaceIcon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void animateInterfaceFrames() {
        interfaceSpriteCounter++;
        if (interfaceSpriteCounter > 10) {
            if (interfaceSpriteNumber == 1) {
                interfaceSpriteNumber = 2;
            } else if (interfaceSpriteNumber == 2) {
                interfaceSpriteNumber = 1;
            }
            interfaceSpriteCounter = 0;
        }
    }
}