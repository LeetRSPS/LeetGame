package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Background extends Entity {

    GamePanel gp;

    public Background(GamePanel gp) {
        this.gp = gp;
        getBackgroundImage();
    }

    public void getBackgroundImage() {
        //Initialize and declared backgrounds. It's used by the getBackground() method to get the correct background
        try {
            background0 = ImageIO.read(Entity.class.getResourceAsStream("/background0.png"));
            background1 = ImageIO.read(Entity.class.getResourceAsStream("/background1.png"));
            background2 = ImageIO.read(Entity.class.getResourceAsStream("/background2.png"));
            background3 = ImageIO.read(Entity.class.getResourceAsStream("/background3.png"));
            background4 = ImageIO.read(Entity.class.getResourceAsStream("/background4.png"));
            background5 = ImageIO.read(Entity.class.getResourceAsStream("/background5.png"));
            background6 = ImageIO.read(Entity.class.getResourceAsStream("/background6.png"));
            background7 = ImageIO.read(Entity.class.getResourceAsStream("/background7.png"));
            background8 = ImageIO.read(Entity.class.getResourceAsStream("/background8.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        //Gets the image to be drawn for the Background Image
        getBackground();

        //Draws Background Image
        g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);

        //Draws the Text Background and Text when player enters new area
        drawEnterAreaText(g2);
    }

    public Image getBackground() {
        //Used by the getBackgroundImage method to return the correct image.

        if (score == progressionGates[0]) {
            image = background0;
        }
        if (score == progressionGates[1]) {
            image = background1;
        }
        if (score == progressionGates[2]) {
            image = background2;
        }
        if (score == progressionGates[3]) {
            image = background3;
        }
        if (score == progressionGates[4]) {
            image = background4;
        }
        if (score == progressionGates[5]) {
            image = background5;
        }
        if (score == progressionGates[6]) {
            image = background6;
        }
        if (score == progressionGates[7]) {
            image = background7;
        }
        if (score == progressionGates[8]) {
            image = background8;
        }
        return image;
    }

    public String getFlownText() {
        //Sets the text for when a player enters the respective progressionGates and returns it
        String flownText = "";

        if (score == progressionGates[0]) {
            flownText = "You've taken flight";
        }
        if (score == progressionGates[1]) {
            flownText = "Flown onto the beach";
        }
        if (score == progressionGates[2]) {
            flownText = "Entered mystical forest";
        }
        if (score == progressionGates[3]) {
            flownText = "Mystical forest basin";
        }
        if (score == progressionGates[4]) {
            flownText = "Flying towards new land";
        }
        if (score == progressionGates[5]) {
            flownText = "Made it near Volcano";
        }
        if (score == progressionGates[6]) {
            flownText = "Burning pace to space";
        }
        if (score == progressionGates[7]) {
            flownText = "Exiting atmosphere";
        }
        if (score == progressionGates[8]) {
            flownText = "Left this world";
        }
        return flownText;
    }

    public void drawEnterAreaText(Graphics2D g2) {
        //Draws the Enter Area Text Box, Text Box Outline, and Text when entering the respective area.
        String travelText = getFlownText();

        for (int i = 0; i < progressionGates.length; i++) {

            if (score == progressionGates[i]) {
                //TextBox Background
                g2.setColor(Color.BLACK);
                g2.fillRect(getEnterRegionTextRectangleX(), 198, getEnterRegionTextRectangleWidth(), 16);
                g2.setColor(Color.MAGENTA);
                g2.drawRect(getEnterRegionTextRectangleX(), 198, getEnterRegionTextRectangleWidth(), 16);

                //Text
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Comic Sans", Font.BOLD, 16));
                g2.drawString(travelText, getEnterRegionTextRectangleX() + 1, 213);
            }
        }
    }

    public int getEnterRegionTextRectangleWidth() {
        int rectWid = 0;

        for (int i = 0; i < progressionGates.length; i++) {

            //Enchanted Forest
            if (score == progressionGates[0]) {
                rectWid = 145;
            }
            //Beach
            if (score == progressionGates[1]) {
                rectWid = 167;
            }
            //Mystical Forest Thick
            if (score == progressionGates[2]) {
                rectWid = 177;
            }
            //Mystical Forest Basin
            if (score == progressionGates[3]) {
                rectWid = 160;
            }
            //Faraway Lands
            if (score == progressionGates[4]) {
                rectWid = 187;
            }
            //Close to Volcano
            if (score == progressionGates[5]) {
                rectWid = 160;
            }
            //On Volcano surface
            if (score == progressionGates[6]) {
                rectWid = 175;
            }
            //Rocketship
            if (score == progressionGates[7]) {
                rectWid = 150;
            }
            //Space
            if (score == progressionGates[8]) {
                rectWid = 110;
            }

        }
        return rectWid;
    }

    public int getEnterRegionTextRectangleX() {
        int rectX = (gp.screenWidth / 4) - 10;

        for (int i = 0; i < progressionGates.length; i++) {

            //Beach
            if (score == progressionGates[1]) {
                rectX = (gp.screenWidth / 4) - 15;
            }
            //Mystical Forest Thick
            if (score == progressionGates[2]) {
                rectX = (gp.screenWidth / 4) - 22;
            }
            //Faraway Lands
            if (score == progressionGates[4]) {
                rectX = (gp.screenWidth / 4) - 30;
            }
            //On Volcano surface
            if (score == progressionGates[6]) {
                rectX = (gp.screenWidth / 4) - 20;
            }
            //Rocketship
            if (score == progressionGates[7]) {
                rectX = (gp.screenWidth / 4) - 12;
            }
            //Space
            if (score == progressionGates[8]) {
                rectX = gp.screenWidth / 3;
            }
        }
        return rectX;
    }

}
