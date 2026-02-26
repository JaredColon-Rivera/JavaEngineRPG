package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.util.Random;

import static util.ResourceUtil.*;
import static util.ResourceUtil.getArmorPath;
import static util.constants.KeyConstants.*;

public class Npc_Troig extends Entity{

    public Npc_Troig(GamePanel gp, KeyHandler keyH){
        super(gp, keyH);

        direction = Direction.DOWN;

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
                direction = Direction.UP;
            }
            if(i > 25 && i <= 50){
                direction = Direction.DOWN;
            }
            if(i > 50 && i <= 75){
                direction = Direction.LEFT;
            }
            if(i > 75 && i <= 100){
                direction = Direction.RIGHT;
            }

            actionLockCounter = 0;

        }


    }

    public void getImage(){
        // Walking Faces
//        for (int i = 0; i < 2; i++) {
//            base_template = "npc";
//            base_action = "spr_troig_head_face";
//            image_path = "troig_head_face";
//            face_up[i] = setup(getFacePath(base_template, base_action, DIRECTION_UP, image_path, DIRECTION_UP, i + 1));
//            face_down[i] = setup(getFacePath(base_template, base_action, DIRECTION_DOWN, image_path, DIRECTION_DOWN, i + 1));
//            face_left[i] = setup(getFacePath(base_template, base_action, DIRECTION_LEFT, image_path, DIRECTION_LEFT, i + 1));
//            face_right[i] = setup(getFacePath(base_template, base_action, DIRECTION_RIGHT, image_path, DIRECTION_RIGHT, i + 1));
//        }
//
//        // Armor Idle
//        armor_idle_down1 = setup(getArmorPath("armor_idle", "spr_armor_idle", DIRECTION_DOWN, "armor_leather_idle", DIRECTION_DOWN, 1));
//        armor_idle_up1 = setup(getArmorPath("armor_idle", "spr_armor_idle", DIRECTION_UP, "armor_leather_idle", DIRECTION_UP, 1));
//        armor_idle_left1 = setup(getArmorPath("armor_idle", "spr_armor_idle", DIRECTION_LEFT, "armor_leather_idle", DIRECTION_LEFT, 1));
//        armor_idle_right1 = setup(getArmorPath("armor_idle", "spr_armor_idle", DIRECTION_RIGHT, "armor_leather_idle", DIRECTION_RIGHT, 1));
//
//        // Base Idle
//        idle_down1 = setup(getBasePath("spr_base_idle", "spr_base_idle", DIRECTION_DOWN, "base_idle", DIRECTION_DOWN, 1));
//        idle_up1 = setup(getBasePath("spr_base_idle", "spr_base_idle", DIRECTION_UP, "base_idle", DIRECTION_UP, 1));
//        idle_left1 = setup(getBasePath("spr_base_idle", "spr_base_idle", DIRECTION_LEFT, "base_idle", DIRECTION_LEFT, 1));
//        idle_right1 = setup(getBasePath("spr_base_idle", "spr_base_idle", DIRECTION_RIGHT, "base_idle", DIRECTION_RIGHT, 1));
//
//        // Leather Armor Walking
//        for (int i = 0; i < 4; i++) {
//            base_template = "armor_walking";
//            base_action = "spr_armor_walking";
//            image_path = "armor_leather_walking";
//            armor_walking_up[i] = setup(getArmorPath(base_template, base_action, DIRECTION_UP, image_path, DIRECTION_UP, i + 1));
//            armor_walking_down[i] = setup(getArmorPath(base_template, base_action, DIRECTION_DOWN, image_path, DIRECTION_DOWN, i + 1));
//            armor_walking_left[i] = setup(getArmorPath(base_template, base_action, DIRECTION_LEFT, image_path, DIRECTION_LEFT, i + 1));
//            armor_walking_right[i] = setup(getArmorPath(base_template, base_action, DIRECTION_RIGHT, image_path, DIRECTION_RIGHT, i + 1));
//        }
//
//        // Base Walking
//        for (int i = 0; i < 4; i++) {
//            base_template = "spr_base_walking";
//            base_action = "spr_base_walk";
//            image_path = "base_walk";
//            walk_up[i] = setup(getBasePath(base_template, base_action, DIRECTION_UP, image_path, DIRECTION_UP, i + 1));
//            walk_down[i] = setup(getBasePath(base_template, base_action, DIRECTION_DOWN, image_path, DIRECTION_DOWN, i + 1));
//            walk_left[i] = setup(getBasePath(base_template, base_action, DIRECTION_LEFT, image_path, DIRECTION_LEFT, i + 1));
//            walk_right[i] = setup(getBasePath(base_template, base_action, DIRECTION_RIGHT, image_path, DIRECTION_RIGHT, i + 1));
//        }
    }

}
