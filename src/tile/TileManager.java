package tile;

import main.GamePanel;
import main.KeyHandler;
import util.ResourceUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InaccessibleObjectException;

import static util.ResourceUtil.getResource;

public class TileManager {

    GamePanel gp;
    KeyHandler keyH;
    public Tile[] tile;
    public int mapTileNum[][];
    int printedTileNumber;
    String mapPath = "/maps/biggerMap.txt";

    public TileManager(GamePanel gp, KeyHandler keyH){
        this.keyH = keyH;
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap(mapPath);

    }

    public void setup(int index, String imagePath, boolean collision){
        ResourceUtil resUtil = new ResourceUtil();
        try{
            tile[index] = new Tile();
            tile[index].image = getResource(imagePath);
            tile[index].collision = collision;
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void getTileImage() {

        // Grass
        setup(0, "/tiles/grass.png", false);

        // Walls
        setup(1, "/tiles/wall.png", true);

        // Water
        setup(2, "/tiles/water.png", true);

        // Earth
        setup(3, "/tiles/earth.png", false);

        // Tree
        setup(4, "/tiles/tree.png", true);

        // Sand
        setup(5, "/tiles/sand.png", false);

    }

    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();

                while(col < gp.maxWorldCol){
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;

                }

                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }

            }
            br.close();

        }catch(Exception e){

        }
    }

    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

                if(keyH.debugMode){

                    // Show tiles
                    g2.drawRect(screenX, screenY, gp.tileSize, gp.tileSize);

                    printedTileNumber = tileNum;
                    // Show coords
                    g2.drawString("X:" + String.valueOf(worldCol) + "Y:" + String.valueOf(worldRow), screenX, screenY);

                }


            }
            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }


    }

}
