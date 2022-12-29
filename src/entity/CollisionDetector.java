package entity;

import java.util.Timer;
import java.util.TimerTask;

import com.sun.javafx.scene.control.skin.EmbeddedTextContextMenuContent;
import main.GamePanel;

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
        Rectangle powerUpRectangle = new Rectangle(Entity.powerUpX, Entity.powerUpY, 16, 16);

        if(powerUpRectangle.intersects(bottomPipeRectangle)) {
            Entity.powerUpX =  Entity.powerUpX + 25;
        }
        if(powerUpRectangle.intersects(topPipeRectangle)) {
            Entity.powerUpX =  Entity.powerUpX + 25;
        }

        if(playerRectangle.intersects(powerUpRectangle)) {
            Entity.capePowerUpEnabled = true;
            Entity.canSpawnPowerUp = false;
        }
        if (playerRectangle.intersects(topPipeRectangle) || playerRectangle.intersects(bottomPipeRectangle)) {
            Entity.collisionOn = true;
        } else {
            if (playerRectangle.intersects(goalRectangle) && playerRectangle.x > (bottomPipeRectangle.x + 12)) {
                if(Entity.capePowerUpEnabled) {
                    if(Entity.powerUpCounter == 4) {
                        Entity.capePowerUpEnabled = false;
                    }
                }
                if(Entity.powerUpCounter == 10) {
                    Entity.powerUpCounter = 0;
                    Entity.canSpawnPowerUp = true;
                }
                Entity.powerUpCounter++;
                Entity.score++;
                System.out.println("powerUpCounter = " + Entity.powerUpCounter);
                System.out.println("capePowerUpEnabled = " + Entity.capePowerUpEnabled);

            }
        }
    }
}