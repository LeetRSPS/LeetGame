package main;

import tile.TileManager;

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
        FlowLayout experimentLayout = new FlowLayout(FlowLayout.TRAILING,0,0);

        BufferedImage image;
        getImage();
        image = playerframe0;

        this.setIconImage(image);
        this.setTitle("Flappy Leet");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(270,270);
        this.setResizable(false);
        this.setLayout(experimentLayout);

        resetButton = new JButton("Reset");
        resetButton.setForeground(Color.white);
        resetButton.setBackground(Color.BLACK);
        resetButton.setSize(10, 10);
        resetButton.setLocation(25,25);
        resetButton.addActionListener(e -> gamePanel.initializeGameSettings());
        resetButton.setFocusable(false);

        gamePanel = new GamePanel();

        this.add(resetButton);
        this.add(gamePanel);

        this.setVisible(true);

        gamePanel.startGameThread();
    }

    public void getImage() {
        try {
            playerframe0 = ImageIO.read(TileManager.class.getResourceAsStream("/playerframe.png"));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
