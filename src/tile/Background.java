package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Background {

    GamePanel gp;
    Tile[] tile;

public Background(GamePanel gp){

        this.gp = gp;
        tile = new Tile[10];
        getBackgroundImage();
    }

    public void getBackgroundImage() {
        try {
            tile[0] = new Tile();
            tile[0].image =
                    ImageIO.read(TileManager.class.getResourceAsStream("/bg.png"));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(tile[0].image, 0, 0, gp.screenWidth, gp.screenHeight, null);
    }

    public void update() {
    }
}
