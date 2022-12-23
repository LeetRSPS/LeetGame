package entity;

import java.util.Timer;
import java.util.TimerTask;
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

        if (playerRectangle.intersects(topPipeRectangle) || playerRectangle.intersects(bottomPipeRectangle)) {
            Entity.collisionOn = true;
        } else {
            if (playerRectangle.intersects(goalRectangle) && playerRectangle.x > (bottomPipeRectangle.x + 12)) {
                Entity.score++;
            }
        }
    }
}