package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Background {

    GamePanel gp;
    BufferedImage image;

    public Background(GamePanel gp){
            this.gp = gp;
            getBackgroundImage();
    }

    public void getBackgroundImage() {
        //Gets Background Image
        try {
            image = ImageIO.read(Entity.class.getResourceAsStream("/background.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);
    }
}
