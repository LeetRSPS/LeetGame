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

        if(playerRectangle.intersects(powerUpRectangle)) {
            Entity.powerUpOn = true;
            Entity.displayPowerUp = false;
        }
        if (playerRectangle.intersects(topPipeRectangle) || playerRectangle.intersects(bottomPipeRectangle)) {
            Entity.collisionOn = true;
        } else {
            if (playerRectangle.intersects(goalRectangle) && playerRectangle.x > (bottomPipeRectangle.x + 12)) {
                if(Entity.powerUpOn) {
                    if(Entity.powerUpCounter > 3) {
                        Entity.powerUpCounter = 0;
                        Entity.displayPowerUp = true;
                        Entity.powerUpOn = false;
                    }
                    Entity.powerUpCounter++;
                }
                Entity.score++;
                System.out.println("powerUpCounter = " + Entity.powerUpCounter);
                System.out.println("powerUpOnBoolean = " + Entity.powerUpOn);

            }
        }
    }
}