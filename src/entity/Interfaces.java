package entity;

import main.GamePanel;
import highscores.HighScoreReader;
import highscores.HighScoreWriter;
import main.KeyHandler;
import main.MouseHandler;
import main.MouseTracker;

import javax.imageio.ImageIO;
import java.awt.*;
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

    public void drawChangeBirdInterface(Graphics2D g2) {
        getPlayerOnInterfaceImage();

        //Main Interface Sprite
        g2.drawImage(changeBirdIcon,0, 0, null);

        //Birds To Select From
        g2.drawImage(bluebird, 80, 135, null);
        g2.drawImage(redbird, 175, 135, null);

        //Bird Selected
        if(GamePanel.selectedBird == GamePanel.blueBird) {
            selectedBird = bluebird;
        }
        if(GamePanel.selectedBird == GamePanel.redBird) {
            selectedBird = redbird;
        }

        g2.drawImage(selectedBird, 127, 68, null);
    }

    public void drawTitleInterface(Graphics2D g2) {
        getPlayerOnInterfaceImage();

        //Main Interface Sprite
        g2.drawImage(titleScreenIcon,0, 0, null);
        g2.drawImage(bigBirdFrame, 60, 0, null);

        //Play Button
        Color myGoldColor = new Color(234, 245, 0);
        g2.setColor(Color.BLACK);
        g2.fillRect(95, 124, 64, 32);
        g2.setColor(myGoldColor);
        g2.drawRect(95, 124, 64, 32);
        g2.setColor(myGoldColor);
        g2.drawString("PLAY", 113, 145);

        //Change Bird Button
        g2.setColor(Color.BLACK);
        g2.fillRect(10, 180, 90, 16);
        g2.setColor(myGoldColor);
        g2.drawRect(10, 180, 90, 16);
        g2.setColor(myGoldColor);
        g2.drawString("Change Bird", 18, 193);

        //Change Name
        g2.setColor(Color.BLACK);
        g2.fillRect(155, 180, 90, 16);
        g2.setColor(myGoldColor);
        g2.drawRect(155, 180, 90, 16);
        g2.setColor(myGoldColor);
        g2.drawString("Change Name", 159, 193);
    }

    public void drawPauseScreen(Graphics2D g2) {
        getPlayerOnInterfaceImage();

        //Main Interface Sprite
        g2.drawImage(pauseInterfaceIcon,0, 0, null);
        g2.drawImage(bigBirdFrame, 60, 60, null);

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
        g2.drawString("Main Menu", 184, 193);

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

        //
        g3.drawImage(deathScreenIcon, 0, 0, null);

        //Death Box
        g3.setColor(Color.red);
        g3.fillRect(65, 50, gp.screenWidth / 2, gp.screenHeight / 8);

        //Death Box Score Text
        g3.setColor(Color.white);
        g3.drawString("You scored: " + score, 90, 71);

        //High Scores Box + Text
        g3.fillRect(gp.screenWidth / 5 - 5, gp.screenHeight / 3 + 10, gp.tileSize * 10, gp.tileSize * 4);
        g3.setColor(Color.BLACK);
        g3.drawString("High Scores", 95, 112);

        //Highscores List
        g3.setFont(new Font("SansSerif", Font.PLAIN, 12));
        g3.drawString("1st: " + HighScoreReader.firstPlaceValue, 50, 128);
        g3.setFont(new Font("SansSerif", Font.PLAIN, 11));
        g3.drawString("2nd: " + HighScoreReader.secondPlaceValue, 50, 142);
        g3.setFont(new Font("SansSerif", Font.PLAIN, 10));
        g3.drawString("3rd: " + HighScoreReader.thirdPlaceValue, 50, 155);

        HighScoreWriter.scoreWritten = false;

        //Button Area Middle
        Color myGoldColor = new Color(234, 245, 0);
        g3.setColor(Color.BLACK);
        g3.fillRect(130, 160, 65, 30);
        g3.setColor(myGoldColor);
        g3.drawRect(130, 160, 65, 30);
        g3.setColor(myGoldColor);
        g3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        g3.drawString("Reset", 142, 178);

        //Button Area Right
        g3.setColor(Color.BLACK);
        g3.fillRect(90, 192, 75, 16);
        g3.setColor(myGoldColor);
        g3.drawRect(90, 192, 75, 16);
        g3.setColor(myGoldColor);
        g3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        g3.drawString("Main Menu", 98, 204);

        //Save High Score
        g3.setColor(Color.BLACK);
        g3.fillRect(51, 160, 65, 30);
        g3.setColor(myGoldColor);
        g3.drawRect(51, 160, 65, 30);
        g3.setColor(myGoldColor);
        g3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        g3.drawString("Save", 63, 178);
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
            //Title Screen Birds
            bigBlueBirdFrame1 = ImageIO.read(Entity.class.getResourceAsStream("/pauseBird.png"));
            bigBlueBirdFrame2 = ImageIO.read(Entity.class.getResourceAsStream("/pauseBird2.png"));
            bigRedBirdFrame1 = ImageIO.read(Entity.class.getResourceAsStream("/pauseBirdRed.png"));
            bigRedBirdFrame2 = ImageIO.read(Entity.class.getResourceAsStream("/pauseBirdRed2.png"));

            //Main Interface Backgrounds
            pauseInterfaceIcon = ImageIO.read(Entity.class.getResourceAsStream("/pauseInterfaceIcon.png"));
            titleScreenIcon = ImageIO.read(Entity.class.getResourceAsStream("/titleScreenBackground.png"));
            changeBirdIcon = ImageIO.read(Entity.class.getResourceAsStream("/selectBirdBackground.png"));
            deathScreenIcon = ImageIO.read(Entity.class.getResourceAsStream("/deathScreenIcon.png"));

            //Misc Sprites
            blueBirdFrame0 = ImageIO.read(Entity.class.getResourceAsStream("/playerframe.png"));
            blueBirdFrame1 = ImageIO.read(Entity.class.getResourceAsStream("/playerframe2.png"));
            redBirdFrame0 = ImageIO.read(Entity.class.getResourceAsStream("/redBird0.png"));
            redBirdFrame1 = ImageIO.read(Entity.class.getResourceAsStream("/redBird1.png"));

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

    public void getPlayerOnInterfaceImage() {

        if(GamePanel.selectedBird == GamePanel.blueBird) {
            switch (interfaceSpriteNumber) {
                case 1:
                    bigBirdFrame = bigBlueBirdFrame1;
                    bluebird = blueBirdFrame0;
                    redbird = redBirdFrame0;
                    break;
                case 2:
                    bigBirdFrame = bigBlueBirdFrame2;
                    bluebird = blueBirdFrame1;
                    redbird = redBirdFrame1;
                    break;
            }
        }
        if(GamePanel.selectedBird == GamePanel.redBird) {
            switch (interfaceSpriteNumber) {
                case 1:
                    bigBirdFrame = bigRedBirdFrame1;
                    bluebird = blueBirdFrame0;
                    redbird = redBirdFrame0;
                    break;
                case 2:
                    bigBirdFrame = bigRedBirdFrame2;
                    bluebird = blueBirdFrame1;
                    redbird = redBirdFrame1;
                    break;
            }
        }
    }
}