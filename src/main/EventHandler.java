package main;

import java.awt.*;
import java.sql.Array;
import java.util.Objects;

import static util.constants.KeyConstants.*;

public class EventHandler {

    GamePanel gp;
    EventRect[][] eventRect;
    KeyHandler keyH;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;

        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }


    }

    public void checkEvent(){

        // Check if the player character is more than 1 tile away from the last event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.tileSize){
            canTouchEvent = true;
        }

        if(canTouchEvent){

            if(hit(12, 10, Direction.RIGHT)) damagePit(gp.dialogueState);

        }


        if(hit(13, 10, Direction.UP)) healthPool(gp.dialogueState);

        if(hit(12, 11, Direction.UP)) teleport(gp.dialogueState);

    }

    public void draw(Graphics2D g2, int col, int row){


        int worldX = col * gp.tileSize + eventRect[col][row].eventRectDefaultX;
        int worldY = row * gp.tileSize + eventRect[col][row].eventRectDefaultY;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        g2.setColor(Color.RED);
        g2.drawRect(
                screenX,
                screenY,
                eventRect[col][row].width,
                eventRect[col][row].height
        );

    }

    public boolean hit(int col, int row, Direction reqDirection){
        boolean hit = false;

        // Getting player position
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row]) && !eventRect[col][row].eventDone){
            if(gp.player.direction == reqDirection){

                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;

    }

    public void teleport(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "TELEPORTED!";
        gp.player.worldX = gp.tileSize * 5;
        gp.player.worldY = gp.tileSize * 5;
    }

    public void damagePit(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fell into a pit!";
        gp.player.life -= 1;
        canTouchEvent = false;
    }

    public void healthPool(int gameState){
        if(gp.keyH.enterPressed){
            gp.gameState = gameState;
            gp.player.attackCancelled = true;
            gp.ui.currentDialogue = "You drink the water and restore life";
            gp.player.life = gp.player.maxLife;
        }
    }


}
