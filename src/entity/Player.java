package entity;

import main.GamePanel;
import main.KeyHandler;
import util.ResourceUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static util.constants.KeyConstants.*;
import static util.constants.SpriteConstants.*;
import static util.ResourceUtil.*;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int hasKey = 0;

    public Player (GamePanel gp, KeyHandler keyH){

        // instantiating super class (Entity.java)
        super(gp, keyH);

        this.gp = gp;
        this.keyH = keyH;

        // Screen location
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        // Collision
        solidArea = new Rectangle(16, 28, 16, 18);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues(){
        // Player default values
        worldX = gp.tileSize * 10;
        worldY = gp.tileSize * 10;
        speed = 3;
        direction = "down";
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage(){

        // Faces
        face_down1 = setup(FACE_DOWN_1);
        face_down2 = setup(FACE_DOWN_2);

        face_up1 = setup(FACE_UP_1);
        face_up2 = setup(FACE_UP_2);

        face_left1 = setup(FACE_LEFT_1);
        face_left2 = setup(FACE_LEFT_2);

        face_right1 = setup(FACE_RIGHT_1);
        face_right2 = setup(FACE_RIGHT_2);


        // Armor Idle
        armor_idle_down1 = setup(ARMOR_IDLE_DOWN_1);
        armor_idle_up1 = setup(ARMOR_IDLE_UP_1);
        armor_idle_left1 = setup(ARMOR_IDLE_LEFT_1);
        armor_idle_right1 = setup(ARMOR_IDLE_RIGHT_1);


        // Base Idle
        idle_down1 = setup(IDLE_DOWN_1);
        idle_up1 = setup(IDLE_UP_1);
        idle_left1 = setup(IDLE_LEFT_1);
        idle_right1 = setup(IDLE_RIGHT_1);


        // Armor Walking
        armor_walking_down1 = setup(ARMOR_WALK_DOWN_1);
        armor_walking_down2 = setup(ARMOR_WALK_DOWN_2);
        armor_walking_down3 = setup(ARMOR_WALK_DOWN_3);
        armor_walking_down4 = setup(ARMOR_WALK_DOWN_4);

        armor_walking_up1 = setup(ARMOR_WALK_UP_1);
        armor_walking_up2 = setup(ARMOR_WALK_UP_2);
        armor_walking_up3 = setup(ARMOR_WALK_UP_3);
        armor_walking_up4 = setup(ARMOR_WALK_UP_4);

        armor_walking_left1 = setup(ARMOR_WALK_LEFT_1);
        armor_walking_left2 = setup(ARMOR_WALK_LEFT_2);
        armor_walking_left3 = setup(ARMOR_WALK_LEFT_3);
        armor_walking_left4 = setup(ARMOR_WALK_LEFT_4);

        armor_walking_right1 = setup(ARMOR_WALK_RIGHT_1);
        armor_walking_right2 = setup(ARMOR_WALK_RIGHT_2);
        armor_walking_right3 = setup(ARMOR_WALK_RIGHT_3);
        armor_walking_right4 = setup(ARMOR_WALK_RIGHT_4);


        // Base Walking
        up1 = setup(WALK_UP_1);
        up2 = setup(WALK_UP_2);
        up3 = setup(WALK_UP_3);
        up4 = setup(WALK_UP_4);

        down1 = setup(WALK_DOWN_1);
        down2 = setup(WALK_DOWN_2);
        down3 = setup(WALK_DOWN_3);
        down4 = setup(WALK_DOWN_4);

        left1 = setup(WALK_LEFT_1);
        left2 = setup(WALK_LEFT_2);
        left3 = setup(WALK_LEFT_3);
        left4 = setup(WALK_LEFT_4);

        right1 = setup(WALK_RIGHT_1);
        right2 = setup(WALK_RIGHT_2);
        right3 = setup(WALK_RIGHT_3);
        right4 = setup(WALK_RIGHT_4);

    }

    public void getPlayerAttackImage(){

        attack_right1 = setup(ATTACK_RIGHT_1, gp.tileSize * 2, gp.tileSize * 2);
        attack_right2 = setup(ATTACK_RIGHT_2, gp.tileSize * 2, gp.tileSize * 2);
        attack_right3 = setup(ATTACK_RIGHT_3, gp.tileSize * 2, gp.tileSize * 2);
        attack_right4 = setup(ATTACK_RIGHT_4, gp.tileSize * 2, gp.tileSize * 2);
    }

    public void update(){

        if(attacking){
            idle = false;
            attacking();

        }
        else if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed){

            if(keyH.upPressed){
                direction = DIRECTION_UP;
            }
            else if(keyH.downPressed){
                direction = DIRECTION_DOWN;
            }
            else if(keyH.leftPressed){
                direction = DIRECTION_LEFT;
            }
            else if(keyH.rightPressed){
                direction = DIRECTION_RIGHT;
            }

            // COLLISION

            // Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // Check Object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickupObject(objIndex);

            // Check Npc collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // Check Npc collision
            int enemyIndex = gp.cChecker.checkEntity(this, gp.enemy);
            contactEnemy(enemyIndex);

            // Check event
            gp.eventHandler.checkEvent();

            // If collision is false, player can move
            if(!collisionOn && !keyH.enterPressed){
                idle = false;
                switch(direction){
                    case DIRECTION_UP:
                        direction = DIRECTION_UP;
                        worldY -= speed;
                        break;
                    case DIRECTION_DOWN:
                        direction = DIRECTION_DOWN;
                        worldY += speed;
                        break;
                    case DIRECTION_LEFT:
                        direction = DIRECTION_LEFT;
                        worldX -= speed;
                        break;
                    case DIRECTION_RIGHT:
                        direction = DIRECTION_RIGHT;
                        worldX += speed;
                        break;
                }
            }

            gp.keyH.enterPressed = false;

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
        else{
            idle = true;
            switch(direction){
                case DIRECTION_UP:
                    direction = DIRECTION_UP;
                    break;
                case DIRECTION_DOWN:
                    direction = DIRECTION_DOWN;
                    break;
                case DIRECTION_LEFT:
                    direction = DIRECTION_LEFT;
                    break;
                case DIRECTION_RIGHT:
                    direction = DIRECTION_RIGHT;
                    break;
                default:
                    break;
            }
        }

        // Invincible counter
        if(invincible){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }

    }

    public void interactNPC(int i){

        if(gp.keyH.enterPressed){
            if(i != 999){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
            else{
                attacking = true;
            }

        }

    }

    public void attacking(){

        spriteCounter++;
        if(spriteCounter <= 15) {
            spriteNum = 1;
        }
        if(spriteCounter > 15 && spriteCounter <= 30){
            spriteNum = 2;
        }
        if(spriteCounter > 30 && spriteCounter <= 45){
            spriteNum = 3;
        }
        if(spriteCounter > 45 && spriteCounter <= 60){
            spriteNum = 4;
        }
        if(spriteCounter > 60){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
            idle = true;
        }
    }

    public void pickupObject(int i){
        if(i != 999){
            String objectName = gp.obj[i].name;

            switch(objectName){
                case "Key":
                    hasKey++;
                    gp.playSoundEffect(2);
                    gp.obj[i] = null;
                    gp.ui.showMessage("You received a key");
                    break;
                case "Door":
                    if(hasKey> 0){
                        gp.playSoundEffect(3);
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("You opened the door");
                    }else{
                        gp.ui.showMessage("You need a key to open the door");
                    }
                    break;
                case "Boots":
                    gp.playSoundEffect(1);
                    speed += 1;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Your speed has increased");
                    break;
                case "Chest":
                    gp.stopMusic();
                    gp.playSoundEffect(1);
                    gp.ui.gameFinished = true;
                    break;
            }
        }
    }

    public void contactEnemy(int i){
        if(i != 999){
            if(!invincible){
                life -= 1;
                invincible = true;
            }
        }
    }

    public void draw(Graphics2D g2){

        // g2.setColor(Color.white);

        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        BufferedImage image_face = null;
        BufferedImage image_armor = null;

        int tempScreenX = screenX;
        int tempScreenY = screenY;


        if(!idle){
            switch(direction){
                case DIRECTION_UP:
                    if(spriteNum == 1){
                        image = up1;
                        image_face = face_up1;
                        image_armor = armor_walking_up1;
                    }
                    if(spriteNum == 2){
                        image = up2;
                        image_face = face_up2;
                        image_armor = armor_walking_up2;
                    }
                    if(spriteNum == 3){
                        image = up3;
                        image_face = face_up1;
                        image_armor = armor_walking_up3;
                    }
                    if(spriteNum == 4){
                        image = up4;
                        image_face = face_up2;
                        image_armor = armor_walking_up4;
                    }
                    break;
                case DIRECTION_DOWN:
                    if(spriteNum == 1){
                        image = down1;
                        image_face = face_down1;
                        image_armor = armor_walking_down1;
                    }
                    if(spriteNum == 2){
                        image = down2;
                        image_face = face_down2;
                        image_armor = armor_walking_down2;
                    }
                    if(spriteNum == 3){
                        image = down3;
                        image_face = face_down1;
                        image_armor = armor_walking_down3;
                    }
                    if(spriteNum == 4){
                        image = down4;
                        image_face = face_down2;
                        image_armor = armor_walking_down4;
                    }
                    break;
                case DIRECTION_LEFT:
                    if(spriteNum == 1){
                        image = left1;
                        image_face = face_left1;
                        image_armor = armor_walking_left1;
                    }
                    if(spriteNum == 2){
                        image = left2;
                        image_face = face_left2;
                        image_armor = armor_walking_left2;
                    }
                    if(spriteNum == 3){
                        image = left3;
                        image_face = face_left1;
                        image_armor = armor_walking_left3;
                    }
                    if(spriteNum == 4){
                        image = left4;
                        image_face = face_left2;
                        image_armor = armor_walking_left4;
                    }
                    break;
                case DIRECTION_RIGHT:
                    if(!attacking){
                        if(spriteNum == 1){
                            image = right1;
                            image_face = face_right1;
                            image_armor = armor_walking_right1;
                        }
                        if(spriteNum == 2){
                            image = right2;
                            image_face = face_right2;
                            image_armor = armor_walking_right2;
                        }
                        if(spriteNum == 3){
                            image = right3;
                            image_face = face_right1;
                            image_armor = armor_walking_right3;
                        }
                        if(spriteNum == 4){
                            image = right4;
                            image_face = face_right2;
                            image_armor = armor_walking_right4;
                        }

                    }
                    if(attacking){
                        tempScreenX = screenX - gp.tileSize / 2;
                        tempScreenY = screenY - gp.tileSize / 2;
                        if(spriteNum == 1){
                            image = attack_right1;
                            image_face = null;
                            image_armor = null;
                        }
                        if(spriteNum == 2){
                            image = attack_right2;
                            image_face = null;
                            image_armor = null;
                        }
                        if(spriteNum == 3){
                            image = attack_right3;
                            image_face = null;
                            image_armor = null;
                        }
                        if(spriteNum == 4){
                            image = attack_right4;
                            image_face = null;
                            image_armor = null;
                        }
                    }
                    break;
                default:
                    break;
            }

        }
        else{
            // Idle
            switch(direction){
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

        if(invincible){
            // Show invincible
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        if(!attacking){

            g2.drawImage(image, tempScreenX, tempScreenY, gp.tileSize, gp.tileSize, null);
            g2.drawImage(image_face, screenX, screenY, gp.tileSize, gp.tileSize, null);
            g2.drawImage(image_armor, screenX, screenY, gp.tileSize, gp.tileSize, null);

        }
        else{

            g2.drawImage(image, tempScreenX, tempScreenY, null);
            g2.drawImage(image_face, screenX, screenY, gp.tileSize, gp.tileSize, null);
            g2.drawImage(image_armor, screenX, screenY, gp.tileSize, gp.tileSize, null);

        }

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // Show collision mask
        if(keyH.debugMode){
            g2.setColor(Color.RED);
            g2.drawRect(
                    worldX - gp.player.worldX + gp.player.screenX + solidArea.x,
                    worldY - gp.player.worldY + gp.player.screenY + solidArea.y,
                    solidArea.width,
                    solidArea.height
            );

            g2.setFont(new Font("Arial", Font.PLAIN, 26));
            g2.setColor(Color.white);
            g2.drawString("Invincible: " + invincibleCounter, 10, 400);
        }
    }

}
