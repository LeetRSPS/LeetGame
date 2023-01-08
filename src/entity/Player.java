package entity;

import main.GamePanel;
import main.KeyHandler;
import main.MouseHandler;

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
        y = 50;
        speed = 3;
    }

    public void update() {
        if(GamePanel.gameState == GamePanel.playState) {
            checkPlayerInput();
            checkPlayerPosition();
            animatePlayerFrames();
        }
    }

    public void getPlayerImage() {
        try {
            playerframe0 = ImageIO.read(Entity.class.getResourceAsStream("/playerframe.png"));
            playerframe2 = ImageIO.read(Entity.class.getResourceAsStream("/playerframe2.png"));
            playercapeframe1 = ImageIO.read(Entity.class.getResourceAsStream("/playercapeframe.png"));
            playercapeframe2 = ImageIO.read(Entity.class.getResourceAsStream("/playercapeframe2.png"));
            playerhelmetframe1 = ImageIO.read(Entity.class.getResourceAsStream("/playerhelmetframe1.png"));
            playerhelmetframe2 = ImageIO.read(Entity.class.getResourceAsStream("/playerhelmetframe2.png"));

            blueBirdFrame0 = ImageIO.read(Entity.class.getResourceAsStream("/playerframe.png"));
            blueBirdFrame1 = ImageIO.read(Entity.class.getResourceAsStream("/playerframe2.png"));
            redBirdFrame0 = ImageIO.read(Entity.class.getResourceAsStream("/redBird0.png"));
            redBirdFrame1 = ImageIO.read(Entity.class.getResourceAsStream("/redBird1.png"));
            playerredcapeframe1 = ImageIO.read(Entity.class.getResourceAsStream("/playerredcapeframe.png"));
            playerredcapeframe2 = ImageIO.read(Entity.class.getResourceAsStream("/playerredcapeframe2.png"));
            playerredhelmetframe1 = ImageIO.read(Entity.class.getResourceAsStream("/playerredhelmetframe1.png"));
            playerredhelmetframe2 = ImageIO.read(Entity.class.getResourceAsStream("/playerredhelmetframe2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        if(GamePanel.selectedBird == GamePanel.blueBird) {
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
        }
        if(GamePanel.selectedBird == GamePanel.redBird) {
            switch (spriteNumber) {
                case 1:
                    //SetFrame1Sprites
                    if(capePowerUpEnabled) {
                        image = playerredcapeframe1;
                    } else if(helmetPowerUpEnabled) {
                        image = playerredhelmetframe1;
                    } else {
                        image = redBirdFrame0;
                    }
                    break;
                case 2:
                    //SetFrame2Sprites
                    if(capePowerUpEnabled) {
                        image = playerredcapeframe2;
                    } else if(helmetPowerUpEnabled) {
                        image = playerredhelmetframe2;
                    } else {
                        image = redBirdFrame1;
                    }
                    break;
            }
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
            keyH.canMove = false;
            mouseH.canMove = false;
            GamePanel.gameState = GamePanel.deadBirdState;
        }
        if (collisionOn) {
            gp.gameFinished = true;
            keyH.canMove = false;
            mouseH.canMove = false;
            GamePanel.gameState = GamePanel.deadBirdState;
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
}