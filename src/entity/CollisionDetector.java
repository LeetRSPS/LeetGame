package entity;

import java.util.Timer;
import java.util.TimerTask;

import com.sun.javafx.scene.control.skin.EmbeddedTextContextMenuContent;
import main.GamePanel;

import javax.xml.stream.events.EntityReference;
import java.awt.*;

public class CollisionDetector extends Entity {

    GamePanel gamePanel;

    public CollisionDetector(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

    }

    public static void checkTile(Entity entity) {

        Rectangle playerRectangle = new Rectangle(Entity.x, Entity.y, 8, 8);

        Rectangle bottomPipeRectangle = new Rectangle(Entity.pipex, Entity.pipey, 16, 256 - Entity.pipey);
        Rectangle topPipeRectangle = new Rectangle(Entity.pipex2, Entity.pipey2 / 120, 16, Entity.pipey2 + 10);
        Rectangle goalRectangle = new Rectangle(Entity.pipex, 0, 16, 360);
        Rectangle capePowerUpRectangle = new Rectangle(Entity.capePowerUpX, Entity.capePowerUpY, 16, 16);
        Rectangle helmetPowerUpRectangle = new Rectangle(Entity.helmetPowerUpX, Entity.helmetPowerUpY, 16, 16);

        //Helmet Power Up Collisions
        if(helmetPowerUpRectangle.intersects(bottomPipeRectangle)) {
            Entity.capePowerUpX =  Entity.capePowerUpX+ 25;
        }
        if(capePowerUpRectangle.intersects(topPipeRectangle)) {
            Entity.capePowerUpX =  Entity.capePowerUpX + 25;
        }


        //Cape Power Up Collisions
        if(capePowerUpRectangle.intersects(bottomPipeRectangle)) {
            Entity.capePowerUpX =  Entity.capePowerUpX+ 25;
        }
        if(capePowerUpRectangle.intersects(topPipeRectangle)) {
            Entity.capePowerUpX =  Entity.capePowerUpX + 25;
        }



        //Player Rectangle Collisions
        if(playerRectangle.intersects(capePowerUpRectangle)) {
            Entity.capePowerUpEnabled = true;
            Entity.canSpawnPowerUp = false;
        }
        if(playerRectangle.intersects(helmetPowerUpRectangle)) {
            Entity.helmetPowerUpEnabled = true;
            Entity.canSpawnPowerUp = false;
        }

        if (playerRectangle.intersects(topPipeRectangle) || playerRectangle.intersects(bottomPipeRectangle)) {
            if (helmetPowerUpEnabled) {
                Entity.pipex = Entity.x - 16;
                Entity.pipex2 = Entity.x - 16;
                Entity.helmetPowerUpEnabled = false;
            } else {
                Entity.collisionOn = true;
            }

        } else {
            if (playerRectangle.intersects(goalRectangle) && playerRectangle.x > (bottomPipeRectangle.x + 12)) {
                if(Entity.capePowerUpEnabled) {
                    if(Entity.powerUpCounter == 4) {
                        Entity.capePowerUpEnabled = false;
                    }
                }
                if(Entity.powerUpCounter == 10) {
                    if(Entity.powerUpDecider == 0) {
                        Entity.powerUpDecider = 1;
                    } else {
                        Entity.powerUpDecider = 0;
                    }
                    Entity.powerUpCounter = 0;
                    Entity.canSpawnPowerUp = true;
                }
                Entity.powerUpCounter++;
                Entity.score++;
                System.out.println("powerUpCounter = " + Entity.powerUpCounter);
                System.out.println("capePowerUpEnabled = " + Entity.helmetPowerUpEnabled);

            }
        }
    }
}