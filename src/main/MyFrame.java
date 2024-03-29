package main;

import entity.Entity;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MyFrame extends JFrame {

    public BufferedImage playerframe0;
    GamePanel gamePanel;
    JButton resetButton;

    MyFrame() {
        //Gets and Sets icon for JFrame
        BufferedImage image;
        getImage();
        image = playerframe0;
        this.setIconImage(image);

        //JFrame Options/Settings
        this.setTitle("Flappy Leet");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(270,270);
        this.setResizable(false);

        //FlowLayout for Buttons
        FlowLayout experimentLayout = new FlowLayout(FlowLayout.TRAILING,0,0);
        this.setLayout(experimentLayout);

        //Reset Button Settings
        resetButton = new JButton("Reset");
        resetButton.setForeground(Color.white);
        resetButton.setBackground(Color.BLACK);
        resetButton.setSize(10, 10);
        resetButton.setLocation(25,25);
        resetButton.addActionListener(e -> gamePanel.initializeGameSettings());
        resetButton.setFocusable(false);

        gamePanel = new GamePanel();

        //Components to JFrame
        this.add(resetButton);
        this.add(gamePanel);
        this.setVisible(true);

        //Starts game
        gamePanel.startGameThread();
    }

    public void getImage() {
        //Gets the image for the JFrame
        try {
            playerframe0 = ImageIO.read(Entity.class.getResourceAsStream("/playerframe.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
