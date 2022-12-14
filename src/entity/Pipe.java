package entity;

import main.GamePanel;
import main.KeyHandler;
import tile.Tile;
import tile.TileManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Pipe extends Entity {

    GamePanel gp;
    //KeyHandler keyH;
    Tile[] tile;

    public Pipe(GamePanel gp) {

        this.gp = gp;
        tile = new Tile[10];
        getPipeImage();
        initializePipe();
    }

    public void initializePipe() {
        int minimumHeight = (100 - gp.tileSize * 2 - 5);
        int maximumHeight = (175);

        Random rand = new Random();
        int rand2 = rand.nextInt(maximumHeight-minimumHeight) + minimumHeight;


        x = 256;
        y = rand2;
        x2 = x;
        y2 = y - gp.tileSize * 3;
        speed = 3;
    }

    public void update() {
        int minimumHeight = (100 - gp.tileSize * 2 - 5);
        int maximumHeight = (175);

        Random rand = new Random();
        int rand2 = rand.nextInt(maximumHeight-minimumHeight) + minimumHeight;

        score++;

        x -= speed;
        x2 -= speed ;
        if(x <= 0) {
            x = 375;
            y = rand2;
        }
        if(x2 <= 0) {
            x2 = 375;
            y2 = y - gp.tileSize * 3;
        }
        pipePosX = x;

    }

    public void getPipeImage() {
        try {
            tile[0] = new Tile();
            tile[0].image =
                    ImageIO.read(TileManager.class.getResourceAsStream("/pipe.png"));

            tile[1] = new Tile();
            tile[1].image =
                    ImageIO.read(TileManager.class.getResourceAsStream("/pipeExtension.png"));

            tile[2] = new Tile();
            tile[2].image =
                    ImageIO.read(TileManager.class.getResourceAsStream("/upsideDownPipe.png"));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        //Top Pipe
        g2.drawImage(tile[2].image, x2, y2, gp.tileSize, gp.tileSize, null);
        //Top Pipe Extension
        g2.drawImage(tile[1].image, x2, y2, gp.tileSize, -(gp.tileSize / gp.screenHeight + y), null);

       //BOTTOM PIPE
        g2.drawImage(tile[0].image, x, y, gp.tileSize, gp.tileSize, null);
       //Bottom Pipe Extension
        g2.drawImage(tile[1].image, x, y + gp.tileSize, gp.tileSize, gp.tileSize * gp.screenHeight - y, null);
    }

}
