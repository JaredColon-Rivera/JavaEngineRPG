package entity;

import main.GamePanel;
import main.KeyHandler;
import util.ResourceUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static util.ResourceUtil.getResource;
import static util.constants.KeyConstants.*;
import static util.constants.KeyConstants.DIRECTION_RIGHT;

public class Entity {

    GamePanel gp;
    KeyHandler keyH;
    public int worldX, worldY;
    public int speed;
    public boolean idle = true;
    public Direction direction = Direction.DOWN;

    public final int screenX;
    public final int screenY;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;

    public String base_template;
    public String base_action;
    public String image_path;

    public String armor_base_template;
    public String armor_base_action;
    public String armor_image_path;

    // Combat
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    int dyingCounter = 0;
    boolean hpBarOn = false;
    int hpBarCounter = 0;

    // Collision
    public Rectangle solidArea = new Rectangle(4, 20, 40, 20);
    public int solidAreaDefaultX, solidAreaDefaultY;

    // Combat
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);

    public boolean collisionOn = false;

    public int actionLockCounter = 0;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    public int type; // 0 = player, 1 = npc, 2 = enemy;

    String[] dialogues = new String[20];
    int dialogueIndex = 0;

    // Animation
    Map<Direction, BufferedImage[]> walkAnimation = new HashMap<>();
    Map<Direction, BufferedImage[]> faceAnimation = new HashMap<>();
    Map<Direction, BufferedImage[]> armorWalkAnimation = new HashMap<>();
    Map<Direction, BufferedImage[]> baseCombatAnimation = new HashMap<>();
    Map<Direction, BufferedImage[]> baseCombatSlashAnimation = new HashMap<>();
    Map<Direction, BufferedImage[]> baseCombatSwordSlashAnimation = new HashMap<>();

    Map<Direction, BufferedImage> idleImages = new HashMap<>();

    public BufferedImage[] walkFrames = new BufferedImage[4];
    public BufferedImage[] faceFrames = new BufferedImage[2];
    public BufferedImage[] armorFrames = new BufferedImage[4];
    public BufferedImage[] combatFrames = new BufferedImage[4];
    public BufferedImage[] slashFrames = new BufferedImage[4];
    public BufferedImage[] swordSlashFrames = new BufferedImage[4];

    public Map<Direction, BufferedImage> faceIdle = new HashMap<>();
    public Map<Direction, BufferedImage> armorIdle = new HashMap<>();
    public Map<Direction, BufferedImage> baseIdle = new HashMap<>();


    // CHARACTER STATUS
    public int maxLife;
    public int life;

    public BufferedImage[] walk_left = new BufferedImage[4];
    public BufferedImage[] walk_right = new BufferedImage[4];
    public BufferedImage[] walk_up = new BufferedImage[4];
    public BufferedImage[] walk_down = new BufferedImage[4];

    BufferedImage[] armor_walking_left = new BufferedImage[4];
    BufferedImage[] armor_walking_right = new BufferedImage[4];
    BufferedImage[] armor_walking_up = new BufferedImage[4];
    BufferedImage[] armor_walking_down = new BufferedImage[4];

    BufferedImage[] face_left = new BufferedImage[2];
    BufferedImage[] face_right = new BufferedImage[2];
    BufferedImage[] face_up = new BufferedImage[2];
    public BufferedImage[] face_down = new BufferedImage[2];

    public BufferedImage

    // Idle Armor sprites
    armor_idle_down1, armor_idle_up1, armor_idle_left1, armor_idle_right1,

    // Idle Base sprites
    idle_up1, idle_down1, idle_left1, idle_right1;

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

    public BufferedImage setup(String imagePath, int width, int height){
        ResourceUtil resUtil = new ResourceUtil();
        BufferedImage image = null;

        try{
            image = getResource(imagePath);
            image = resUtil.scaleImage(image, width, height);
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
            case Direction.UP:
                direction = Direction.DOWN;
                break;
            case Direction.DOWN:
                direction = Direction.UP;
                break;
            case Direction.LEFT:
                direction = Direction.RIGHT;
                break;
            case Direction.RIGHT:
                direction = Direction.LEFT;
                break;

        }

    }

    public void setAction(){

    }
    public void damageReaction(){}

    public void update(){
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.enemy);

        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == 2 && contactPlayer){
            if(!gp.player.invincible){
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }

        // If collision is false, player can move
        if(!collisionOn){
            switch(direction){
                case Direction.UP:
                    worldY -= speed;
                    break;
                case Direction.DOWN:
                    worldY += speed;
                    break;
                case Direction.LEFT:
                    worldX -= speed;
                    break;
                case Direction.RIGHT:
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

        // Invincible counter
        if(invincible){
            invincibleCounter++;
            if(invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
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
                    case Direction.UP:
                        if (spriteNum == 1) {
                            image = walk_up[0];
                            image_face = face_up[0];
                            image_armor = armor_walking_up[0];
                        }
                        if (spriteNum == 2) {
                            image = walk_up[1];
                            image_face = face_up[1];
                            image_armor = armor_walking_up[1];
                        }
                        if (spriteNum == 3) {
                            image = walk_up[2];
                            image_face = face_up[0];
                            image_armor = armor_walking_up[2];
                        }
                        if (spriteNum == 4) {
                            image = walk_up[3];
                            image_face = face_up[1];
                            image_armor = armor_walking_up[3];
                        }
                        break;
                    case Direction.DOWN:
                        if (spriteNum == 1) {
                            image = walk_down[0];
                            image_face = face_down[0];
                            image_armor = armor_walking_down[0];
                        }
                        if (spriteNum == 2) {
                            image = walk_down[1];
                            image_face = face_down[1];
                            image_armor = armor_walking_down[1];
                        }
                        if (spriteNum == 3) {
                            image = walk_down[2];
                            image_face = face_down[0];
                            image_armor = armor_walking_down[2];
                        }
                        if (spriteNum == 4) {
                            image = walk_down[3];
                            image_face = face_down[1];
                            image_armor = armor_walking_down[3];
                        }
                        break;
                    case Direction.LEFT:
                        if (spriteNum == 1) {
                            image = walk_left[0];
                            image_face = face_left[0];
                            image_armor = armor_walking_left[0];
                        }
                        if (spriteNum == 2) {
                            image = walk_left[1];
                            image_face = face_left[1];
                            image_armor = armor_walking_left[1];
                        }
                        if (spriteNum == 3) {
                            image = walk_left[2];
                            image_face = face_left[0];
                            image_armor = armor_walking_left[2];
                        }
                        if (spriteNum == 4) {
                            image = walk_left[3];
                            image_face = face_left[1];
                            image_armor = armor_walking_left[3];
                        }
                        break;
                    case Direction.RIGHT:
                        if (spriteNum == 1) {
                            image = walk_right[0];
                            image_face = face_right[0];
                            image_armor = armor_walking_right[0];
                        }
                        if (spriteNum == 2) {
                            image = walk_right[1];
                            image_face = face_right[1];
                            image_armor = armor_walking_right[1];
                        }
                        if (spriteNum == 3) {
                            image = walk_right[2];
                            image_face = face_right[0];
                            image_armor = armor_walking_right[2];
                        }
                        if (spriteNum == 4) {
                            image = walk_right[3];
                            image_face = face_right[1];
                            image_armor = armor_walking_right[3];
                        }
                        break;
                    default:
                        break;
                }

            } else {
                // Idle
                switch (direction) {
                    case Direction.UP:
                        image = idle_up1;
                        image_face = face_up[0];
                        image_armor = armor_idle_up1;
                        break;
                    case Direction.DOWN:
                        image = idle_down1;
                        image_face = face_down[0];
                        image_armor = armor_idle_down1;
                        break;
                    case Direction.LEFT:
                        image = idle_left1;
                        image_face = face_left[0];
                        image_armor = armor_idle_left1;
                        break;
                    case Direction.RIGHT:
                        image = idle_right1;
                        image_face = face_right[0];
                        image_armor = armor_idle_right1;
                        break;
                    default:
                        break;
                }

            }

            // ENEMY HEALTH BAR
            if(type == 2 && hpBarOn == true){

                double oneScale = (double)gp.tileSize/maxLife;
                double hpBarValue = oneScale * life;


                g2.setColor(new Color(35, 25, 25));
                g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);
                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);

                hpBarCounter++;

                if(hpBarCounter > 600){
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }


            if(invincible){
                // Show invincible
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4f);
            }

            if(dying){
                dyingAnimation(g2);
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            g2.drawImage(image_face, screenX, screenY, gp.tileSize, gp.tileSize, null);
            g2.drawImage(image_armor, screenX, screenY, gp.tileSize, gp.tileSize, null);

            changeAlpha(g2, 1f);
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

    public void dyingAnimation(Graphics2D g2){

        dyingCounter++;

        int i = 5;

        if(dyingCounter <= i) changeAlpha(g2, 0f);
        if(dyingCounter > i && dyingCounter <= i*2) changeAlpha(g2, 1f);
        if(dyingCounter > i*2 && dyingCounter <= i*3) changeAlpha(g2, 0f);
        if(dyingCounter > i*3 && dyingCounter <= i*4) changeAlpha(g2, 1f);
        if(dyingCounter > i*4 && dyingCounter <= i*5) changeAlpha(g2, 0f);
        if(dyingCounter > i*5 && dyingCounter <= i*6) changeAlpha(g2, 1f);
        if(dyingCounter > i*6 && dyingCounter <= i*7) changeAlpha(g2, 0f);
        if(dyingCounter > i*7 && dyingCounter <= i*8) changeAlpha(g2, 0f);

        if(dyingCounter > i*8){
            dying = false;
            alive = false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValues){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValues));

    }
}
