package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Obj_Chest extends ParentObject{

    public Obj_Chest() {
        name = "Chest";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
