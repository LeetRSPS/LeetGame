package entity;

import main.GamePanel;
import main.KeyHandler;
import org.w3c.dom.css.Rect;
import tile.Tile;
import tile.TileManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Pipe extends Entity {

    GamePanel gp;
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
        int rand2 = rand.nextInt((maximumHeight-minimumHeight) + 1) + minimumHeight;


        pipex = 256;
        pipey = rand2;
        pipex2 = 256;
        pipey2 = pipey - 48;
        speed = 3;
    }

    public void update() {
        int minimumHeight = (100 - gp.tileSize * 2 - 5);
        int maximumHeight = (175);

        Random rand = new Random();
        int rand2 = rand.nextInt(maximumHeight-minimumHeight) + minimumHeight;

        pipex -= speed;
        pipex2 -= speed ;
        if(pipex <= 0) {
            pipex = 375;
            pipey = rand2;
        }
        if(pipex2 <= 0) {
            pipex2 = 375;
            pipey2 = pipey - 48;
        }

        pipeHeight = pipey + gp.tileSize * 3;

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
        g2.drawImage(tile[2].image, pipex2, pipey2, gp.tileSize, gp.tileSize, null);
        //Top Pipe Extension
        g2.drawImage(tile[1].image, pipex2, pipey2, gp.tileSize, -(gp.tileSize / gp.screenHeight + pipey), null);

       //BOTTOM PIPE
        g2.drawImage(tile[0].image, pipex, pipey, gp.tileSize, gp.tileSize, null);
       //Bottom Pipe Extension
        g2.drawImage(tile[1].image, pipex, pipey + gp.tileSize, gp.tileSize, gp.tileSize * gp.screenHeight - pipey, null);
    }

}
