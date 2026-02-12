package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

import static util.ResourceUtil.getResource;

public class Obj_Chest extends ParentObject{

    GamePanel gp;

    public Obj_Chest(GamePanel gp) {

        this.gp = gp;

        name = "Chest";
        try{
            image = getResource("/objects/chest.png");
            image = resUtil.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
