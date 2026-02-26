package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static util.ResourceUtil.*;
import static util.constants.KeyConstants.*;

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

        // Combat
        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues(){
        // Player default values
        worldX = gp.tileSize * 10;
        worldY = gp.tileSize * 10;
        speed = 3;
        direction = Direction.DOWN;
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage() {

        for (Direction dir : Direction.values()) {

            // --- Idle ---
            armorIdle.put(dir, setup(getArmorPath(
                    "armor_idle",
                    "spr_armor_idle",
                    dir,
                    "armor_leather_idle",
                    dir,
                    1
            )));
             baseIdle.put(dir, setup(getBasePath(
                    "spr_base_idle",
                    "spr_base_idle",
                    dir,
                    "base_idle",
                    dir,
                    1
            )));

            // Walking Faces
            for (int i = 0; i < 2; i++) {
                base_template = "player";
                base_action = "spr_lemon_head_face";
                image_path = "lemon_head_face";

                faceFrames[i] = setup(getFacePath(base_template, base_action, dir, image_path, dir, i + 1));
                faceIdle.put(dir, setup(getFacePath(base_template, base_action, dir, image_path, dir, 1)));
            }

            for (int i = 0; i < 4; i++) {

                // Base Walking
                base_template = "spr_base_walking";
                base_action = "spr_base_walk";
                image_path = "base_walk";
                walkFrames[i] = setup(getBasePath(base_template, base_action, dir, image_path, dir, i + 1));

                // Leather Armor Walking
                armor_base_template = "armor_walking";
                armor_base_action = "spr_armor_walking";
                armor_image_path = "armor_leather_walking";
                armorFrames[i] = setup(getArmorPath(armor_base_template, armor_base_action, dir, armor_image_path, dir, i + 1));

            }
            faceAnimation.put(dir, faceFrames.clone());
            walkAnimation.put(dir, walkFrames.clone());
            armorWalkAnimation.put(dir, armorFrames.clone());

        }

    }

    public void getPlayerAttackImage(){

        for (Direction dir : Direction.values()) {

            for (int i = 0; i < 4; i++) {

                int offset = gp.tileSize * 2;

                combatFrames[i] = setup(
                        getBasePath("spr_base_combat", "spr_base_combat", dir, "spr_base_combat", dir, i + 1),
                        offset, offset);

                slashFrames[i] = setup(
                        getBasePath("spr_base_attacks", "spr_base_slash", dir, "spr_base_slash", dir, i + 1),
                        offset, offset);

                swordSlashFrames[i] = setup(
                        getBasePath("spr_base_weapons", "spr_base_sword_slash", dir, "spr_base_sword_slash", dir, i + 1),
                        offset, offset);
            }

            baseCombatAnimation.put(dir, combatFrames.clone());
            baseCombatSlashAnimation.put(dir, slashFrames.clone());
            baseCombatSwordSlashAnimation.put(dir, swordSlashFrames.clone());

        }

    }

    public void update(){

        if(attacking){
            idle = false;
            attacking();

        }
        else if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed){

            if(keyH.upPressed){
                direction = Direction.UP;
            }
            else if(keyH.downPressed){
                direction = Direction.DOWN;
            }
            else if(keyH.leftPressed){
                direction = Direction.LEFT;
            }
            else if(keyH.rightPressed){
                direction = Direction.RIGHT;
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
            spriteNum = 1;
            idle = true;
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

            // Save the current world x and world y and solid area
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust players attack area
            switch(direction){
                case Direction.UP:
                    attackArea.width = 36;
                    attackArea.height = 12;
                    worldY -= attackArea.height;
                    break;
                case Direction.DOWN:
                    attackArea.width = 42;
                    attackArea.height = 36;
                    worldY += attackArea.height;
                    break;
                case Direction.LEFT:
                    attackArea.width = 24;
                    attackArea.height = 42;
                    worldX -= attackArea.width;

                    break;
                case Direction.RIGHT:
                    attackArea.width = 36;
                    attackArea.height = 42;
                    worldX += attackArea.width;

                    break;
            }

            // Attack area becomes solid area
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            // Check monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.enemy);
            damageEnemy(monsterIndex);

            // After checking collions, restore to original position
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

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

    public void damageEnemy(int i){
        if(i != 999){
            if(!gp.enemy[i].invincible){
                gp.enemy[i].life -= 1;
                gp.enemy[i].invincible = true;

                if(gp.enemy[i].life <= 0){
                    gp.enemy[i].dying = true;
                }
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        BufferedImage image_face = null;
        BufferedImage image_armor = null;
        BufferedImage image_weapon = null;
        BufferedImage image_attack = null;

        int tempScreenX = screenX;
        int tempScreenY = screenY;

        int frameIndex = spriteNum - 1;

        if(!idle){
            if(!attacking){
                image       = walkAnimation.get(direction)[frameIndex];
                image_face  = faceAnimation.get(direction)[frameIndex % 2]; // if face only has 2 frames
                image_armor = armorWalkAnimation.get(direction)[frameIndex];
            }
            else{
                tempScreenX = screenX - gp.tileSize / 2;
                tempScreenY = screenY - gp.tileSize / 2;

                image = baseCombatAnimation.get(direction)[frameIndex];
                image_face = null;
                image_armor = null;
                image_weapon = baseCombatSwordSlashAnimation.get(direction)[frameIndex];
                image_attack = baseCombatSlashAnimation.get(direction)[frameIndex];
            }
        }
        else{
            // Idle
            image       = baseIdle.get(direction);
            image_face = faceIdle.get(direction);
            image_armor = armorIdle.get(direction);
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
            g2.drawImage(image_weapon, tempScreenX, tempScreenY, null);
            g2.drawImage(image_attack, tempScreenX, tempScreenY, null);
        }

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // Show collision mask
        if(keyH.debugMode) {
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

            if (attacking && spriteNum == 2) {

                int attackWorldX = worldX;
                int attackWorldY = worldY;

                switch (direction) {
                    case Direction.UP:
                        attackWorldY -= attackArea.height;
                        break;
                    case Direction.DOWN:
                        attackWorldY += attackArea.height;
                        break;
                    case Direction.LEFT:
                        attackWorldX -= attackArea.width;
                        break;
                    case Direction.RIGHT:
                        attackWorldX += attackArea.width;
                        break;
                }

                attackArea.x = attackWorldX;
                attackArea.y = attackWorldY;

                // Convert world to screen position
                int screenX = attackArea.x - gp.player.worldX + gp.player.screenX;
                int screenY = attackArea.y - gp.player.worldY + gp.player.screenY;

                g2.setColor(Color.BLUE);
                g2.drawRect(screenX, screenY, attackArea.width, attackArea.height);
            }
        }
    }
}
