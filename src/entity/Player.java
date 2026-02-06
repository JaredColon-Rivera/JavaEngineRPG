package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int hasKey = 0;

    boolean idle = true;

    public Player (GamePanel gp, KeyHandler keyH){
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
    }

    public void setDefaultValues(){
        // Player default values
        worldX = gp.tileSize * 10;
        worldY = gp.tileSize * 10;
        speed = 3;
        direction = "down";
    }

    public void getPlayerImage(){
        try{

            // Faces
            // Face down
            face_down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_faces/spr_lemon_head_face_down/lemon_head_face_down1.png")));
            face_down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_faces/spr_lemon_head_face_down/lemon_head_face_down2.png")));

            // Face up
            face_up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_faces/spr_lemon_head_face_up/lemon_head_face_up1.png")));
            face_up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_faces/spr_lemon_head_face_up/lemon_head_face_up2.png")));

            // Face left
            face_left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_faces/spr_lemon_head_face_left/lemon_head_face_left1.png")));
            face_left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_faces/spr_lemon_head_face_left/lemon_head_face_left2.png")));

            // Face right
            face_right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_faces/spr_lemon_head_face_right/lemon_head_face_right1.png")));
            face_right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_faces/spr_lemon_head_face_right/lemon_head_face_right2.png")));

            // Armor Idle
            // Armor Down
            armor_idle_down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_armor/armor_idle/spr_armor_idle_down/armor_leather_idle_down1.png")));

            // Armor Up
            armor_idle_up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_armor/armor_idle/spr_armor_idle_up/armor_leather_idle_up1.png")));

            // Armor left
            armor_idle_left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_armor/armor_idle/spr_armor_idle_left/armor_leather_idle_left1.png")));

            // Armor right
            armor_idle_right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_armor/armor_idle/spr_armor_idle_right/armor_leather_idle_right1.png")));

            // Idle
            idle_down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_bases/spr_base_idle_down/base_idle_down1.png")));
            idle_up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_bases/spr_base_idle_up/base_idle_up1.png")));
            idle_left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_bases/spr_base_idle_left/base_idle_left1.png")));
            idle_right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_bases/spr_base_idle_right/base_idle_right1.png")));

            // Armor Walking
            // Armor walking down
            armor_walking_down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_armor/armor_walking/spr_armor_walking_down/armor_leather_walking_down1.png")));
            armor_walking_down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_armor/armor_walking/spr_armor_walking_down/armor_leather_walking_down2.png")));
            armor_walking_down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_armor/armor_walking/spr_armor_walking_down/armor_leather_walking_down3.png")));
            armor_walking_down4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_armor/armor_walking/spr_armor_walking_down/armor_leather_walking_down4.png")));

            // Armor walking up
            armor_walking_up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_armor/armor_walking/spr_armor_walking_up/armor_leather_walking_up1.png")));
            armor_walking_up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_armor/armor_walking/spr_armor_walking_up/armor_leather_walking_up2.png")));
            armor_walking_up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_armor/armor_walking/spr_armor_walking_up/armor_leather_walking_up3.png")));
            armor_walking_up4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_armor/armor_walking/spr_armor_walking_up/armor_leather_walking_up4.png")));

            // Armor walking left
            armor_walking_left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_armor/armor_walking/spr_armor_walking_left/armor_leather_walking_left1.png")));
            armor_walking_left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_armor/armor_walking/spr_armor_walking_left/armor_leather_walking_left2.png")));
            armor_walking_left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_armor/armor_walking/spr_armor_walking_left/armor_leather_walking_left3.png")));
            armor_walking_left4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_armor/armor_walking/spr_armor_walking_left/armor_leather_walking_left4.png")));

            // Armor walking right
            armor_walking_right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_armor/armor_walking/spr_armor_walking_right/armor_leather_walking_right1.png")));
            armor_walking_right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_armor/armor_walking/spr_armor_walking_right/armor_leather_walking_right2.png")));
            armor_walking_right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_armor/armor_walking/spr_armor_walking_right/armor_leather_walking_right3.png")));
            armor_walking_right4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_armor/armor_walking/spr_armor_walking_right/armor_leather_walking_right4.png")));


            // Walking
            // Walk up animation
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_bases/spr_base_walk_up/base_walk_up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_bases/spr_base_walk_up/base_walk_up2.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_bases/spr_base_walk_up/base_walk_up3.png")));
            up4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_bases/spr_base_walk_up/base_walk_up4.png")));

            // Walk down animation
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_bases/spr_base_walk_down/base_walk_down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_bases/spr_base_walk_down/base_walk_down2.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_bases/spr_base_walk_down/base_walk_down3.png")));
            down4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_bases/spr_base_walk_down/base_walk_down4.png")));

            // Walk left animation
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_bases/spr_base_walk_left/base_walk_left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_bases/spr_base_walk_left/base_walk_left2.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_bases/spr_base_walk_left/base_walk_left3.png")));
            left4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_bases/spr_base_walk_left/base_walk_left4.png")));

            // Walk right animation
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_bases/spr_base_walk_right/base_walk_right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_bases/spr_base_walk_right/base_walk_right2.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_bases/spr_base_walk_right/base_walk_right3.png")));
            right4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_bases/spr_base_walk_right/base_walk_right4.png")));



        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){

        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){

            idle = false;

            if(keyH.upPressed){
                direction = "up";
            }
            else if(keyH.downPressed){
                direction = "down";
            }
            else if(keyH.leftPressed){
                direction = "left";
            }
            else if(keyH.rightPressed){
                direction = "right";
            }

            // Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // Check Object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickupObject(objIndex);

            // If collision is false, player can move
            if(collisionOn == false){
                switch(direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
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
        else{
            idle = true;
            switch(direction){
                case "up":
                    break;
                case "down":
                    break;
                case "left":
                    break;
                case "right":
                    break;
                default:
                    break;
            }
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

    public void draw(Graphics2D g2){

        // g2.setColor(Color.white);

        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        BufferedImage image_face = null;
        BufferedImage image_armor = null;

        if(!idle){
            switch(direction){
                case "up":
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
                case "down":
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
                case "left":
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
                case "right":
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
                    break;
                default:
                    break;
            }

        }
        else{
            // Idle
            switch(direction){
                case "up":
                    image = idle_up1;
                    image_face = face_up1;
                    image_armor = armor_idle_up1;
                    break;
                case "down":
                    image = idle_down1;
                    image_face = face_down1;
                    image_armor = armor_idle_down1;
                    break;
                case "left":
                    image = idle_left1;
                    image_face = face_left1;
                    image_armor = armor_idle_left1;
                    break;
                case "right":
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
        g2.setColor(Color.RED);
        g2.drawRect(
                worldX - gp.player.worldX + gp.player.screenX + solidArea.x,
                worldY - gp.player.worldY + gp.player.screenY + solidArea.y,
                solidArea.width,
                solidArea.height
        );


    }

}
