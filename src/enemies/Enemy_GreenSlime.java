package enemies;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

import java.util.Random;

import static util.ResourceUtil.getBasePath;
import static util.ResourceUtil.getMonsterPath;
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

        // Base Walking
        for (int i = 0; i < 4; i++) {
            base_template = "greenslime";
            walk_up[i] = setup(getMonsterPath(base_template, Direction.DOWN, i + 1));
            walk_down[i] = setup(getMonsterPath(base_template, Direction.DOWN, i + 1));
            walk_left[i] = setup(getMonsterPath(base_template, Direction.DOWN, i + 1));
            walk_right[i] = setup(getMonsterPath(base_template, Direction.DOWN, i + 1));;
        }
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


}
