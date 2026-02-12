package entity;

import main.GamePanel;
import main.KeyHandler;
import util.ResourceUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static util.ResourceUtil.getResource;
import static util.constants.KeyConstants.*;
import static util.constants.KeyConstants.DIRECTION_RIGHT;

public class Entity {

    GamePanel gp;
    KeyHandler keyH;
    public int worldX, worldY;
    public int speed;
    boolean idle = true;
    public String direction;

    public final int screenX;
    public final int screenY;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    // Collision
    public Rectangle solidArea = new Rectangle(4, 6, 40, 40);
    public int solidAreaDefaultX, solidAreaDefaultY;

    public boolean collisionOn = false;

    public int actionLockCounter = 0;

    String[] dialogues = new String[20];
    int dialogueIndex = 0;

    public BufferedImage

            // Face sprite
            face_down1,
            face_down2,
            face_up1,
            face_up2,
            face_left1,
            face_left2,
            face_right1,
            face_right2,

    // Armor sprite
    armor_idle_down1,
            armor_idle_up1,
            armor_idle_left1,
            armor_idle_right1,
            armor_walking_down1, armor_walking_down2, armor_walking_down3, armor_walking_down4,
            armor_walking_up1, armor_walking_up2, armor_walking_up3, armor_walking_up4,
            armor_walking_left1, armor_walking_left2, armor_walking_left3, armor_walking_left4,
            armor_walking_right1, armor_walking_right2, armor_walking_right3, armor_walking_right4,

    // Idle sprites
    idle_up1,
            idle_down1,
            idle_left1,
            idle_right1,

    // Walking
    up1, up2, up3, up4,
            down1, down2, down3, down4,
            left1, left2, left3, left4,
            right1, right2, right3, right4;

    public Entity(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        // Screen location
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        // Collision
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public BufferedImage setup(String imagePath){
        ResourceUtil resUtil = new ResourceUtil();
        BufferedImage image = null;

        try{
            image = getResource(imagePath);
            image = resUtil.scaleImage(image, gp.tileSize, gp.tileSize);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return image;
    }

    public void speak(){

        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch(gp.player.direction){
            case DIRECTION_UP:
                direction = DIRECTION_DOWN;
                break;
            case DIRECTION_DOWN:
                direction = DIRECTION_UP;
                break;
            case DIRECTION_LEFT:
                direction = DIRECTION_RIGHT;
                break;
            case DIRECTION_RIGHT:
                direction = DIRECTION_LEFT;
                break;

        }

    }

    public void setAction(){

    }

    public void update(){
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);

        // If collision is false, player can move
        if(collisionOn == false){
            switch(direction){
                case DIRECTION_UP:
                    worldY -= speed;
                    break;
                case DIRECTION_DOWN:
                    worldY += speed;
                    break;
                case DIRECTION_LEFT:
                    worldX -= speed;
                    break;
                case DIRECTION_RIGHT:
                    worldX += speed;
                    break;
            }
        }

        spriteCounter++;
        if(spriteCounter > 16){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 3;
            }
            else if(spriteNum == 3){
                spriteNum = 4;
            }
            else if(spriteNum == 4){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        BufferedImage image_face = null;
        BufferedImage image_armor = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            if (!idle) {
                switch (direction) {
                    case DIRECTION_UP:
                        if (spriteNum == 1) {
                            image = up1;
                            image_face = face_up1;
                            image_armor = armor_walking_up1;
                        }
                        if (spriteNum == 2) {
                            image = up2;
                            image_face = face_up2;
                            image_armor = armor_walking_up2;
                        }
                        if (spriteNum == 3) {
                            image = up3;
                            image_face = face_up1;
                            image_armor = armor_walking_up3;
                        }
                        if (spriteNum == 4) {
                            image = up4;
                            image_face = face_up2;
                            image_armor = armor_walking_up4;
                        }
                        break;
                    case DIRECTION_DOWN:
                        if (spriteNum == 1) {
                            image = down1;
                            image_face = face_down1;
                            image_armor = armor_walking_down1;
                        }
                        if (spriteNum == 2) {
                            image = down2;
                            image_face = face_down2;
                            image_armor = armor_walking_down2;
                        }
                        if (spriteNum == 3) {
                            image = down3;
                            image_face = face_down1;
                            image_armor = armor_walking_down3;
                        }
                        if (spriteNum == 4) {
                            image = down4;
                            image_face = face_down2;
                            image_armor = armor_walking_down4;
                        }
                        break;
                    case DIRECTION_LEFT:
                        if (spriteNum == 1) {
                            image = left1;
                            image_face = face_left1;
                            image_armor = armor_walking_left1;
                        }
                        if (spriteNum == 2) {
                            image = left2;
                            image_face = face_left2;
                            image_armor = armor_walking_left2;
                        }
                        if (spriteNum == 3) {
                            image = left3;
                            image_face = face_left1;
                            image_armor = armor_walking_left3;
                        }
                        if (spriteNum == 4) {
                            image = left4;
                            image_face = face_left2;
                            image_armor = armor_walking_left4;
                        }
                        break;
                    case DIRECTION_RIGHT:
                        if (spriteNum == 1) {
                            image = right1;
                            image_face = face_right1;
                            image_armor = armor_walking_right1;
                        }
                        if (spriteNum == 2) {
                            image = right2;
                            image_face = face_right2;
                            image_armor = armor_walking_right2;
                        }
                        if (spriteNum == 3) {
                            image = right3;
                            image_face = face_right1;
                            image_armor = armor_walking_right3;
                        }
                        if (spriteNum == 4) {
                            image = right4;
                            image_face = face_right2;
                            image_armor = armor_walking_right4;
                        }
                        break;
                    default:
                        break;
                }

            } else {
                // Idle
                switch (direction) {
                    case DIRECTION_UP:
                        image = idle_up1;
                        image_face = face_up1;
                        image_armor = armor_idle_up1;
                        break;
                    case DIRECTION_DOWN:
                        image = idle_down1;
                        image_face = face_down1;
                        image_armor = armor_idle_down1;
                        break;
                    case DIRECTION_LEFT:
                        image = idle_left1;
                        image_face = face_left1;
                        image_armor = armor_idle_left1;
                        break;
                    case DIRECTION_RIGHT:
                        image = idle_right1;
                        image_face = face_right1;
                        image_armor = armor_idle_right1;
                        break;
                    default:
                        break;
                }

            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            g2.drawImage(image_face, screenX, screenY, gp.tileSize, gp.tileSize, null);
            g2.drawImage(image_armor, screenX, screenY, gp.tileSize, gp.tileSize, null);

            // Show collision mask
            if(keyH.debugMode){
                g2.setColor(Color.RED);
                g2.drawRect(
                        worldX - gp.player.worldX + gp.player.screenX + solidArea.x,
                        worldY - gp.player.worldY + gp.player.screenY + solidArea.y,
                        solidArea.width,
                        solidArea.height
                );
            }
        }
    }
}
