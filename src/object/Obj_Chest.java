package object;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

import java.io.IOException;

import static util.ResourceUtil.getResource;
import static util.constants.KeyConstants.DIRECTION_DOWN;

public class Obj_Chest extends Entity {

    public Obj_Chest(GamePanel gp, KeyHandler keyH) {

        super(gp, keyH);

        name = "Chest";
        idle_down1 = setup("/objects/env/chest.png");
        direction = DIRECTION_DOWN;
        collision = true;
    }
}
