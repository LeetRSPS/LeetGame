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

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    MouseHandler mouseH;

    public Player(GamePanel gp, KeyHandler keyHandler, MouseHandler mouseHandler) {
        this.gp = gp;
        this.keyH = keyHandler;
        this.mouseH = mouseHandler;
        getPlayerImage();
        initializePlayer();
    }

    public void initializePlayer() {
        x = 100;
        y = 100;
        speed = 3;
    }

    public void update() {
        checkPlayerInput();
        checkPlayerPosition();
        animatePlayerFrames();
    }

    public void getPlayerImage() {
        try {
            playerframe0 = ImageIO.read(Entity.class.getResourceAsStream("/playerframe.png"));
            playerframe2 = ImageIO.read(Entity.class.getResourceAsStream("/playerframe2.png"));
            playercapeframe1 = ImageIO.read(Entity.class.getResourceAsStream("/playercapeframe.png"));
            playercapeframe2 = ImageIO.read(Entity.class.getResourceAsStream("/playercapeframe2.png"));
            playerhelmetframe1 = ImageIO.read(Entity.class.getResourceAsStream("/playerhelmetframe1.png"));
            playerhelmetframe2 = ImageIO.read(Entity.class.getResourceAsStream("/playerhelmetframe2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        switch (spriteNumber) {
            case 1:
                //SetFrame1Sprites
                if(capePowerUpEnabled) {
                    image = playercapeframe1;
                } else if(helmetPowerUpEnabled) {
                    image = playerhelmetframe1;
                } else {
                    image = playerframe0;
                }
                break;

            case 2:
                //SetFrame2Sprites
                if(capePowerUpEnabled) {
                    image = playercapeframe2;
                } else if(helmetPowerUpEnabled) {
                    image = playerhelmetframe2;
                } else {
                    image = playerframe2;
                }
                break;
        }

        //Draw player sprite
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

        //Draw Score Tab
        g2.setColor(Color.darkGray);
        g2.fillRect(0, 0, gp.tileSize * 4 + 10, gp.tileSize);
        g2.setColor(Color.white);
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 13));
        g2.drawString(("Score: " + Entity.score), 5, 12);
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

    public void checkPlayerInput() {
        debugInput();

        if (keyH.spacePressed) {
            y -= speed;
        } else if (mouseH.mouse1Pressed) {
            y -= speed;
        } else {
            if (capePowerUpEnabled && mouseH.mouse3Pressed || capePowerUpEnabled && keyH.shiftPressed) {
                y += speed / 2;
                capePowerUpCharge++;
                if(capePowerUpCharge >= 300) {
                    capePowerUpCharge = 0;
                    capePowerUpEnabled = false;
                }
            } else {
                y += speed;
            }
        }
    }

    public void checkPlayerPosition() {
        if (y > gp.screenHeight - 40 || y < -gp.screenHeight + 250) {
            gp.gameFinished = true;
        }
        if (collisionOn) {
            gp.gameFinished = true;
        }
        CollisionDetector.checkCollisions(this);
    }

    public void animatePlayerFrames() {
        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNumber == 1) {
                spriteNumber = 2;
            } else if (spriteNumber == 2) {
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }
    }

    public void debugInput() {
        //Enable and Disable with Q
        if (keyH.qPressed) {
            canDrawDebugPanel = !canDrawDebugPanel;
            GamePanel.debugModeOn = !GamePanel.debugModeOn;
            keyH.qPressed = false;
        }

        //Makes the JFrame Debug button sync with Q toggle
        if(GamePanel.debugModeOn) {
            canDrawDebugPanel = true;
        } else {
            canDrawDebugPanel = false;
        }

        //Power Ups when needed for testing over periods of time.
        //Debug needs to be on and ctrl + tilda + key to enable the powerup.
        if (GamePanel.debugModeOn) {
            if (keyH.ctrlPressed && keyH.tildaPressed) {
                if (keyH.wPressed) {
                    capePowerUpEnabled = true;
                }
                if (keyH.ePressed) {
                    helmetPowerUpEnabled = true;
                }
            }
        }
    }

    public void drawDebugPanel(Graphics2D g4) {
        if(canDrawDebugPanel) {
            g4.setColor(Color.DARK_GRAY);
            g4.fillRect(0, 15,gp.tileSize * 10, gp.tileSize * 3);
            g4.setColor(Color.GREEN);
            g4.drawString("X: " + MouseTracker.x, 2, 28);
            g4.drawString("Y: " + MouseTracker.y, 2, 44);
            g4.drawString("I#: " + amtOfItemsThatExist, 2, 60);
            //g4.drawString("canSpawnHelmet: " + canSpawnHelmet, 2, 44);
            //g4.drawString("canSpawnCape: " + canSpawnCape, 2, 60);
            //g4.drawString("Cape Exists: " + capePowerUpExists, 2, 76);
            //g4.drawString("Helmet Exists: " + helmetPowerUpExists, 2, 92);
            //g4.drawString("CanSpawnPowerUp: " +canSpawnPowerUp, 2, 108);
        }
    }
}