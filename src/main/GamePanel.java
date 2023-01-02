package main;

import entity.*;
import entity.Background;

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
    public static boolean gameFinished = false;
    public static boolean gamePaused = false;
    public static boolean debugModeOn = false;

    public static int gameState;
    public static final int playState = 1;
    public static final int pauseState = 2;

    //Initialize Components
    Graphics2D g2;
    static MouseTracker mouseT = new MouseTracker();
    KeyHandler keyHandler = new KeyHandler(this);
    static MouseHandler mouseHandler = new MouseHandler();
    public Thread gameThread;
    Player player = new Player(this, this.keyHandler, this.mouseHandler);
    CapePowerUp capePowerUp = new CapePowerUp(this);
    HelmetPowerUp helmetPowerUp = new HelmetPowerUp(this);
    Pipe pipe = new Pipe(this);
    Background background = new Background(this);
    static Entity entity = new Entity();
    Interfaces interfaces = new Interfaces(this, this.keyHandler, this.mouseHandler);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseT);
        this.setFocusable(true);
        this.setVisible(true);
    }

    public void startGameThread() {
        gameState = playState;
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
                    remainingTime = remainingTime / 1000000;

                    if (remainingTime < 0) {
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

        if(gameState == pauseState) {
            interfaces.update();
        } else {
            player.update();
            pipe.update();
            helmetPowerUp.update();
            capePowerUp.update();
            entity.update();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        if(gameState == playState) {
            if(!gameFinished) {
                background.draw(g2);
                pipe.draw(g2);
                capePowerUp.draw(g2);
                helmetPowerUp.draw(g2);
                player.draw(g2);
                if(Entity.canDrawDebugPanel) {
                    interfaces.drawDebugPanel(g2);
                }
            }
        }
        if(gameFinished) {
            interfaces.drawDeath(g2);
        }
        if(gameState == pauseState) {
            interfaces.drawPauseScreen(g2);
            if(Entity.canDrawDebugPanel) {
                interfaces.drawDebugPanel(g2);
            }
        }
        g2.dispose();
    }

    public void initializeGameSettings() {
        //Set Variables Back to Default
        Entity.canSpawnPowerUp = true;
        Entity.canSpawnHelmet = true;
        Entity.canSpawnCape = true;
        Entity.powerUpCounter = 0;
        Entity.score = 0;
        Entity.capePowerUpEnabled = false;
        Entity.helmetPowerUpEnabled = false;
        Entity.collisionOn = false;
        Entity.canDrawDebugPanel = false;
        Entity.capePowerUpCharge = 0;
        Entity.amtOfItemsThatExist = 0;
        GamePanel.gameFinished = false;
        keyHandler.spacePressed = false;
        mouseHandler.mouse1Pressed = false;
        mouseHandler.mouse3Pressed = false;
        keyHandler.canMove = true;
        mouseHandler.canMove = true;
        debugModeOn = false;

        //Re-initialize components
        player.initializePlayer();
        pipe.initializePipe();
        capePowerUp.initializePowerUp();
        helmetPowerUp.initializePowerUp();
        entity.initializeEntity();
    }
}
