package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

import static util.ResourceUtil.getResource;

public class Obj_Door extends ParentObject{

    GamePanel gp;

    public Obj_Door(GamePanel gp) {

        this.gp = gp;

        name = "Door";
        try{
            image = getResource("/objects/doors.png");
            image = resUtil.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

}
