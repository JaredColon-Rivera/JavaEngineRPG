package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.util.Random;

import static util.constants.KeyConstants.*;
import static util.constants.SpriteConstants.*;

public class Npc_Troig extends Entity{

    public Npc_Troig(GamePanel gp, KeyHandler keyH){
        super(gp, keyH);

        direction = DIRECTION_DOWN;

        speed = 1;
        idle = false;
        getImage();
        setDialogue();

    }

    public void speak(){
        super.speak();
    }

    public void setDialogue(){
        dialogues[0] = "Hey Dude.";
        dialogues[1] = "Hey Sup.";
        dialogues[2] = "This is going to be the best game yet.\nIt's going to be awesome";
        dialogues[3] = "Lets start your adventure.";
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

    public void getImage(){
        // Faces
        face_down1 = setup(FACE_DOWN_TROIG1);
        face_down2 = setup(FACE_DOWN_TROIG2);

        face_up1 = setup(FACE_UP_TROIG1);
        face_up2 = setup(FACE_UP_TROIG2);

        face_left1 = setup(FACE_LEFT_TROIG1);
        face_left2 = setup(FACE_LEFT_TROIG2);

        face_right1 = setup(FACE_RIGHT_TROIG1);
        face_right2 = setup(FACE_RIGHT_TROIG2);

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

}
