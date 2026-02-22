package object;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public class Obj_Heart extends Entity {

    public Obj_Heart(GamePanel gp, KeyHandler keyH) {

        super(gp, keyH);

        name = "Heart";

        image = setup("/objects/health/heart_full.png");
        image2 = setup("/objects/health/heart_half.png");
        image3 = setup("/objects/health/heart_blank.png");
    }

}
