package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Pipe extends Entity {

    GamePanel gp;

    public Pipe(GamePanel gp) {
        this.gp = gp;
        getPipeImage();
        initializePipe();
    }

    public void initializePipe() {
        int minimumHeight = (100 - gp.tileSize * 2 - 5);
        int maximumHeight = (175);
        Random rand = new Random();
        int rand2 = rand.nextInt((maximumHeight-minimumHeight) + 1) + minimumHeight;

        //Bottom Pipe Coords
        pipex = 256;
        pipey = rand2;
        //Top Pipe Coords
        pipex2 = 256;
        pipey2 = pipey - 48;

        speed = 3;
    }

    public void update() {
        pipex -= speed;
        pipex2 -= speed ;
        resetPipeLocations(gp);
    }

    public void getPipeImage() {
        //Gets images for the Pipes
        try {
            pipe = ImageIO.read(Entity.class.getResourceAsStream("/pipe.png"));
            pipeExtension = ImageIO.read(Entity.class.getResourceAsStream("/pipeExtension.png"));
            upsideDownPipe = ImageIO.read(Entity.class.getResourceAsStream("/upsideDownPipe.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        //Top Pipe
        g2.drawImage(upsideDownPipe, pipex2, pipey2, gp.tileSize, gp.tileSize, null);
        //Top Pipe Extension
        g2.drawImage(pipeExtension, pipex2, pipey2, gp.tileSize, -(gp.tileSize / gp.screenHeight + pipey), null);

       //BOTTOM PIPE
        g2.drawImage(pipe, pipex, pipey, gp.tileSize, gp.tileSize, null);
       //Bottom Pipe Extension
        g2.drawImage(pipeExtension, pipex, pipey + gp.tileSize, gp.tileSize, gp.tileSize * gp.screenHeight - pipey, null);
    }

    public static void resetPipeLocations(GamePanel gp) {
        int minimumHeight = (100 - gp.tileSize * 2 - 5);
        int maximumHeight = (175);
        Random rand = new Random();
        int rand2 = rand.nextInt(maximumHeight-minimumHeight) + minimumHeight;

        if(pipex <= 0) {
            pipex = 375;
            pipey = rand2;
        }
        if(pipex2 <= 0) {
            pipex2 = 375;
            pipey2 = pipey - 48;
        }
    }

}
