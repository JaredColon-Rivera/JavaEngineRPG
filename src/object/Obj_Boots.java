package object;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

import java.io.IOException;

import static util.ResourceUtil.getResource;

public class Obj_Boots extends Entity {

    public Obj_Boots(GamePanel gp, KeyHandler keyH) {

        super(gp, keyH);

        name = "Boots";
        idle_down1 = setup("/objects/items/boots.png");


    }

}
