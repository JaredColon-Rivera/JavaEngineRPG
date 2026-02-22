package enemies;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

import java.util.Random;

import static util.constants.KeyConstants.*;
import static util.constants.KeyConstants.DIRECTION_RIGHT;

public class Enemy_GreenSlime extends Entity {

    public Enemy_GreenSlime(GamePanel gp, KeyHandler keyH){
        super(gp, keyH);

        type = 2;
        name = "Green Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        idle = false;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();

    }

    public void getImage(){
        up1 = setup("/enemies/greenslime_down_1.png");
        up2 = setup("/enemies/greenslime_down_2.png");
        up3 = setup("/enemies/greenslime_down_1.png");
        up4 = setup("/enemies/greenslime_down_2.png");
        down1 = setup("/enemies/greenslime_down_1.png");
        down2 = setup("/enemies/greenslime_down_2.png");
        down3 = setup("/enemies/greenslime_down_1.png");
        down4 = setup("/enemies/greenslime_down_2.png");
        left1 = setup("/enemies/greenslime_down_1.png");
        left2 = setup("/enemies/greenslime_down_2.png");
        left3 = setup("/enemies/greenslime_down_1.png");
        left4 = setup("/enemies/greenslime_down_2.png");
        right1 = setup("/enemies/greenslime_down_1.png");
        right2 = setup("/enemies/greenslime_down_2.png");
        right3 = setup("/enemies/greenslime_down_1.png");
        right4 = setup("/enemies/greenslime_down_2.png");
    }

    public void setAction(){
        actionLockCounter++;

        // Change direction every 2 seconds
        if(actionLockCounter == 120){

            Random random = new Random();

            int i = random.nextInt(100) + 1;

            if(i <= 25){
                direction = DIRECTION_UP;
            }
            if(i > 25 && i <= 50){
                direction = DIRECTION_DOWN;
            }
            if(i > 50 && i <= 75){
                direction = DIRECTION_LEFT;
            }
            if(i > 75 && i <= 100){
                direction = DIRECTION_RIGHT;
            }

            actionLockCounter = 0;

        }

    }


}
