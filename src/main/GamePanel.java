package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 30;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;

    // SYSTEM SETTINGS
    public KeyHandler keyH = new KeyHandler(this);
    public EventHandler eventHandler = new EventHandler(this, keyH);
    TileManager tileM = new TileManager(this, keyH);
    Sound music = new Sound();
    Sound soundEffect = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this, keyH);
    Thread gameThread;

    // UI
    public UI ui = new UI(this);

    // ENTITY AND OBJECTS
    public Player player = new Player(this, keyH);
    public Entity[] obj = new Entity[10];
    public Entity[] npc = new Entity[10];
    public Entity[] enemy = new Entity[20];
    ArrayList<Entity> entityList = new ArrayList<>();

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

    // Set Player's default position (Not used)
    int playerX = 10;
    int playerY = 10;
    int playerSpeed = 4;

    // OPEN THE GAME WINDOW
    public GamePanel() throws IOException, FontFormatException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    // START THE GAME
    public void setupGame(){
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setEnemy();

        // Play theme song
        // playMusic(0);
        // Change to title state to have title happen first
        gameState = playState;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    // GAME LOOP
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; // 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){

            // 1 Update: Update info such as character positions
            update();

            // 2 Draw: Draw the screen with the updated information
            repaint();


            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    // TRACK ACTIONS BY PLAYER AND UPDATE SPRITES
    public void update(){


        if(gameState == playState){
            player.update();

            // NPC
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].update();
                }
            }

            // ENEMY
            for(int i = 0; i < enemy.length; i++){
                if(enemy[i] != null){
                    enemy[i].update();
                }
            }

        }
        if(gameState == pauseState){

        }
    }

    // DRAW ON SCREEN
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // DEBUG
        long drawStart = 0;
        if(keyH.debugMode){
            drawStart = System.nanoTime();
        }

        // TITLE SCREEN
        if(gameState == titleState){

            ui.draw(g2);

        }
        else{

            // Drawn first

            // Tile
            tileM.draw(g2);

            // Player
            entityList.add(player);

            // NPCS
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    entityList.add(npc[i]);
                }
            }

            // OBJs
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    entityList.add(obj[i]);
                }
            }

            // Enemies
            for(int i = 0; i < enemy.length; i++){
                if(enemy[i] != null){
                    entityList.add(enemy[i]);
                }
            }

            // SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {

                    int result = Integer.compare(e1.worldY, e2.worldY);

                    return result;
                }
            });

            // DRAW Entities
            for(int i = 0; i < entityList.size(); i++){
                entityList.get(i).draw(g2);
            }

            // EMPTY Entity List
            entityList.clear();

            // UI
            ui.draw(g2);

            //
            eventHandler.draw(g2, 13, 10);

            // Debug
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            if(keyH.debugMode){
                g2.setColor(Color.RED);
                g2.drawString("Draw Time: " + passed, 10, 400);
            }

        }

        g2.dispose();

    }

    // MUSIC AND SOUND
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSoundEffect(int i){
        soundEffect.setFile(i);
        soundEffect.play();
    }

}
