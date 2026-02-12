package object;

import main.GamePanel;
import util.ResourceUtil;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

import static util.ResourceUtil.getResource;

public class Obj_Key extends ParentObject{

    GamePanel gp;

    public Obj_Key(GamePanel gp) {

        this.gp = gp;

        name = "Key";
        try{
            image = getResource("/objects/key.png");
            image = resUtil.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
