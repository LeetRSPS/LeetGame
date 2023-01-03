package entity;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Entity {

    //Global Variables
    public int speed;
    public static int score = 0;
    public static boolean collisionOn = false;

    //Background Variables
    public int[] progressionGates = {0, 5, 10, 15, 25, 40, 50, 75, 100};

    //Player Variables
    public static int x,y;

    //Pipe Variables
    public static int pipex, pipey, pipex2, pipey2;

    //PowerUp Variables
    public static int capePowerUpX, capePowerUpY;
    public static int helmetPowerUpX, helmetPowerUpY;
    public static int powerUpDecider = 0;
    public static int powerUpCounter = 0;
    public static boolean capePowerUpEnabled = false;
    public static boolean helmetPowerUpEnabled = false;
    public static int capePowerUpCharge = 0;
    public static boolean capePowerUpExists = true;
    public static boolean helmetPowerUpExists = true;
    public static int amtOfItemsThatExist = 0;
    public static boolean canSpawnPowerUp = true;
    public static boolean canSpawnCape = true;
    public static boolean canSpawnHelmet = true;
    public static boolean helmetLowerThanPlayer = false;
    public static boolean capeLowerThanPlayer = false;

    //Sprites & Sprite Counters
    public BufferedImage playerframe0, playerframe2, playercapeframe1, playercapeframe2, playerhelmetframe1, playerhelmetframe2, image;
    public BufferedImage background0, background1, background2, background3, background4, background5, background6, background7, background8;
    public BufferedImage pipe, upsideDownPipe, pipeExtension;
    public BufferedImage helmet, cape, helmetInactive, capeInactive, capefullcharge, cape75charge, cape50charge, cape25charge, cape10charge;
    public int spriteCounter = 0;
    public int spriteNumber = 1;

    //Interface Animation Counters/Trackers
    public BufferedImage pauseInterfaceIcon, bigBirdFrame, bigBlueBirdFrame1,
            bigBlueBirdFrame2, titleScreenIcon, changeBirdIcon, bluebird, blueBirdFrame0,
            blueBirdFrame1, redbird, redBirdFrame0, redBirdFrame1, selectedBird, bigRedBirdFrame1, bigRedBirdFrame2;
    public int interfaceSpriteCounter = 0;
    public int interfaceSpriteNumber = 1;

    //Debug Variables
    public static boolean canDrawDebugPanel = false;

    //Set Initial Entity Variables
    public Entity() {
        initializeEntity();
    }

    //The main method that's ran when entity is being initialized and sets the PowerUpDecider
    public void initializeEntity() {
        setRandomPowerUpDecider();
    }

    //Get's the amount of powerUps that are spawned already.
    public void update() {
        getAmountOfItemsThatExist();
    }

    //Gets and sets the appropriate amtOfItemsThatExist int that's used when checking when to spawn powerups
    public void getAmountOfItemsThatExist() {
        if(capePowerUpExists && !helmetPowerUpExists) {
            amtOfItemsThatExist = 1;
        }
        if(!capePowerUpExists && helmetPowerUpExists) {
            amtOfItemsThatExist = 1;
        }
        if(!capePowerUpExists && !helmetPowerUpExists) {
            amtOfItemsThatExist = 0;
        }
    }

    //Used as a part of the initializer for Entity. It sets a random powerup to be the starting powerup.
    public static void setRandomPowerUpDecider() {
        Random rand = new Random();
        int rand2 = rand.nextInt(2);

        Entity.powerUpDecider = rand2;
    }

    //Used to reset all powerUpVariables if needed
    public static void resetPowerUpVars() {
        canSpawnPowerUp = true;
        capePowerUpExists = true;
        helmetPowerUpExists = true;
        canSpawnCape = true;
        canSpawnHelmet = true;
        amtOfItemsThatExist = 0;
        Entity.setRandomPowerUpDecider();
    }
}
