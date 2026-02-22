package object;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

import java.io.IOException;

import static util.ResourceUtil.getResource;

public class Obj_Door extends Entity {

    GamePanel gp;

    public Obj_Door(GamePanel gp, KeyHandler keyH) {

        super(gp, keyH);

        name = "Door";
        idle_down1 = setup("/objects/env/doors.png");
        collision = true;
    }

}
