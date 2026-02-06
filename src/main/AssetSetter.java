package main;

import object.Obj_Boots;
import object.Obj_Chest;
import object.Obj_Door;
import object.Obj_Key;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new Obj_Key();
        gp.obj[0].worldX = 29 * gp.tileSize;
        gp.obj[0].worldY = 10 * gp.tileSize;

        gp.obj[1] = new Obj_Key();
        gp.obj[1].worldX = 12 * gp.tileSize;
        gp.obj[1].worldY = 20 * gp.tileSize;

        gp.obj[2] = new Obj_Door();
        gp.obj[2].worldX = 12 * gp.tileSize;
        gp.obj[2].worldY = 6 * gp.tileSize;

        gp.obj[3] = new Obj_Door();
        gp.obj[3].worldX = 13 * gp.tileSize;
        gp.obj[3].worldY = 18 * gp.tileSize;

        gp.obj[4] = new Obj_Chest();
        gp.obj[4].worldX = 12 * gp.tileSize;
        gp.obj[4].worldY = 3 * gp.tileSize;

        gp.obj[5] = new Obj_Boots();
        gp.obj[5].worldX = 4 * gp.tileSize;
        gp.obj[5].worldY = 17 * gp.tileSize;



    }

}
