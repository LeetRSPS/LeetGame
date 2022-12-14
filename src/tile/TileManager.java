package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    public TileManager(GamePanel gp){

        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image =
                    ImageIO.read(TileManager.class.getResourceAsStream("/pipe.png"));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        //g2.drawImage(tile[0].image, gp.pipeX, gp.pipeY, gp.tileSize, gp.tileSize, null);
    }
    public void update() {
    }
}
