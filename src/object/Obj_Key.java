package object;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

import java.io.IOException;

import static util.ResourceUtil.getResource;

public class Obj_Key extends Entity {

    public Obj_Key(GamePanel gp, KeyHandler keyH) {

        super(gp, keyH);

        name = "Key";
        idle_down1 = setup("/objects/items/key.png");

    }

}
