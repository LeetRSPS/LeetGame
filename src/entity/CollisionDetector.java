package entity;

import main.GamePanel;
import java.awt.*;

public class CollisionDetector extends Entity {

    GamePanel gamePanel;

    public CollisionDetector(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public static void checkCollisions(Entity entity) {
            /***Method called when checking collisions***/

        //Initializing Rectangles for Collision
        Rectangle playerRectangle = new Rectangle(Entity.x, Entity.y, 8, 8);
        Rectangle bottomPipeRectangle = new Rectangle(Entity.pipex, Entity.pipey, 16, 256 - Entity.pipey);
        Rectangle topPipeRectangle = new Rectangle(Entity.pipex2, Entity.pipey2 / 120, 16, Entity.pipey2 + 10);
        Rectangle goalRectangle = new Rectangle(Entity.pipex, 0, 16, 360);
        Rectangle capePowerUpRectangle = new Rectangle(Entity.capePowerUpX, Entity.capePowerUpY, 16, 16);
        Rectangle helmetPowerUpRectangle = new Rectangle(Entity.helmetPowerUpX, Entity.helmetPowerUpY, 16, 16);

        collisionManager(playerRectangle, bottomPipeRectangle, topPipeRectangle,
                goalRectangle, capePowerUpRectangle, helmetPowerUpRectangle);
    }

    public static void collisionManager(Rectangle playerRectangle, Rectangle bottomPipeRectangle,
        Rectangle topPipeRectangle, Rectangle goalRectangle, Rectangle capePowerUpRectangle,
        Rectangle helmetPowerUpRectangle) {

        /*** This method handles all collisions***/

        powerUpCollisions(helmetPowerUpRectangle, bottomPipeRectangle,
                capePowerUpRectangle, topPipeRectangle, playerRectangle);

                        /*** Player on Pipe Collision ***/
            if (playerRectangle.intersects(topPipeRectangle) || playerRectangle.intersects(bottomPipeRectangle)) {
                playerPipeCollision();
            } else {
                        /***Player on Goals & PowerUps Collision && Counters***/
                if (playerRectangle.intersects(goalRectangle) && playerRectangle.x > (bottomPipeRectangle.x + 12)) {
                    powerUpsAndTrackers();
                    counter();
                }
            }
    }

    public static void playerPipeCollision() {
        /***This method runs when the Player and Pipe collide***/

        if (helmetPowerUpEnabled) {
            Entity.pipex = Entity.x - 16;
            Entity.pipex2 = Entity.x - 16;
            Entity.helmetPowerUpEnabled = false;
        } else {
            //Turns on collision, ending game.
            Entity.collisionOn = true;
        }
    }

    public static void powerUpCollisions(Rectangle helmetPowerUpRectangle,
     Rectangle bottomPipeRectangle, Rectangle capePowerUpRectangle,
     Rectangle topPipeRectangle, Rectangle playerRectangle) {
        /*** This method handles all collisions with PowerUps ***/


                /***Helmet on Pipe collisions***/
        if(helmetPowerUpRectangle.intersects(bottomPipeRectangle)) {
            Entity.helmetPowerUpX =  Entity.helmetPowerUpX+ 50;
        }
        if(helmetPowerUpRectangle.intersects(topPipeRectangle)) {
            Entity.helmetPowerUpX =  Entity.helmetPowerUpX + 50;
        }
                /***Cape on Pipe collisions***/
        if(capePowerUpRectangle.intersects(bottomPipeRectangle)) {
            Entity.capePowerUpX =  Entity.capePowerUpX+ 50;
        }
        if(capePowerUpRectangle.intersects(topPipeRectangle)) {
            Entity.capePowerUpX =  Entity.capePowerUpX + 50;
        }
                /***Powerup on Player collisions***/
        if(playerRectangle.intersects(capePowerUpRectangle)) {
            Entity.capePowerUpEnabled = true;
            Entity.canSpawnPowerUp = false;
        }
        if(playerRectangle.intersects(helmetPowerUpRectangle)) {
            Entity.helmetPowerUpEnabled = true;
            Entity.canSpawnPowerUp = false;
        }
    }

    public static void powerUpsAndTrackers() {
        /***Keeps track of all PowerUps and Trackers needed for collision***/

        //If capePowerUpEnabled = true, check for powerUpCounter to be 4, if it is...
        //Diable the cape powerup.
        if(Entity.capePowerUpEnabled) {
            if(Entity.powerUpCounter == 4) {
                Entity.capePowerUpEnabled = false;
            }
        }

        //If powerUpCounter == 10,
        //if powerUpDecider == 0, set powerUpDecider to 1; "capePowerUp"
        //else powerUpDecider = 0, set powerUpDecider to 0; "helmetPowerUp"
        if(Entity.powerUpCounter == 10) {
            if(Entity.powerUpDecider == 0) {
                Entity.powerUpDecider = 1;
            } else {
                Entity.powerUpDecider = 0;
            }
            //if powerUpCounter == 10, reset powerUpCounter to 0, and canSpawnPowerUp to true;
            Entity.powerUpCounter = 0;
            Entity.canSpawnPowerUp = true;
        }
    }

    public static void counter() {
        //Increment game counters
        Entity.powerUpCounter++;
        Entity.score++;
    }
}