package main;

import entity.PowerUp;
import highscores.HighScoreReader;
import entity.Entity;
import entity.Pipe;
import entity.Player;
import tile.Background;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Panel Settings
    public final int originalTileSize = 16; //16x16 pixels per tile
    public final int scale = 1;

    public final int tileSize = originalTileSize * scale; //48x48 pixels per tile
    public final int screenColums = 16;
    public final int screenRows = 16;
    public final int screenWidth = tileSize * screenColums; //256 pixels width
    public final int screenHeight = tileSize * screenRows; //256 pixels width
    public final int FPS = 40;

    public boolean gameFinished = false;

    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    MouseHandler mouseHandler = new MouseHandler();
    public static Thread gameThread;
    Player player = new Player(this, this.keyHandler, this.mouseHandler);
    PowerUp powerUp = new PowerUp(this);
    Pipe pipe = new Pipe(this);
    Background background = new Background(this);

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);
        this.setFocusable(true);
        this.setVisible(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) {

            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update();
        pipe.update();
        tileManager.update();
        powerUp.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        if(!gameFinished) {
            background.draw(g2);
            player.draw(g2);
            pipe.draw(g2);
            powerUp.draw(g2);
        }
        if(gameFinished) {
            player.drawDeath(g2);
        }

        g2.dispose();
    }

    public void initializeGameSettings() {
        HighScoreReader.readHighScore();
        Entity.collisionOn = false;
        Entity.score = 0;
        Entity.displayPowerUp = true;
        Entity.powerUpOn = false;
        Entity.powerUpCounter = 0;
        gameFinished = false;
        keyHandler.spacePressed = false;
        mouseHandler.mouse1Pressed = false;
        mouseHandler.mouse3Pressed = false;
        keyHandler.canMove = true;
        mouseHandler.canMove = true;
        player.initializePlayer();
        pipe.initializePipe();
        powerUp.initializePowerUp();
    }
}
