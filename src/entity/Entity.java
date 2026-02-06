package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY;
    public int speed;

    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

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


}
