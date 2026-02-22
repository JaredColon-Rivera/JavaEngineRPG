package main;

import enemies.Enemy_GreenSlime;
import entity.Npc_Troig;
import object.Obj_Boots;
import object.Obj_Chest;
import object.Obj_Door;
import object.Obj_Key;

import static util.constants.KeyConstants.*;

public class AssetSetter {

    GamePanel gp;
    KeyHandler keyH;
    public AssetSetter(GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;
    }

    public void setObject(){
        gp.obj[0] = new Obj_Key(gp, keyH);
        gp.obj[0].worldX = 29 * gp.tileSize;
        gp.obj[0].worldY = 10 * gp.tileSize;

        gp.obj[1] = new Obj_Key(gp, keyH);
        gp.obj[1].worldX = 12 * gp.tileSize;
        gp.obj[1].worldY = 20 * gp.tileSize;

        gp.obj[2] = new Obj_Door(gp, keyH);
        gp.obj[2].worldX = 12 * gp.tileSize;
        gp.obj[2].worldY = 6 * gp.tileSize;

        gp.obj[3] = new Obj_Door(gp, keyH);
        gp.obj[3].worldX = 13 * gp.tileSize;
        gp.obj[3].worldY = 18 * gp.tileSize;

        gp.obj[4] = new Obj_Chest(gp, keyH);
        gp.obj[4].worldX = 12 * gp.tileSize;
        gp.obj[4].worldY = 3 * gp.tileSize;

        gp.obj[5] = new Obj_Boots(gp, keyH);
        gp.obj[5].worldX = 4 * gp.tileSize;
        gp.obj[5].worldY = 17 * gp.tileSize;

    }

    public void setNPC(){
        gp.npc[0] = new Npc_Troig(gp, keyH);
        gp.npc[0].worldX = gp.tileSize * 12;
        gp.npc[0].worldY = gp.tileSize * 11;

//        gp.npc[1] = new Npc_Troig(gp, keyH);
//        gp.npc[1].direction = DIRECTION_UP;
//        gp.npc[1].worldX = gp.tileSize * 13;
//        gp.npc[1].worldY = gp.tileSize * 11;
//
//        gp.npc[2] = new Npc_Troig(gp, keyH);
//        gp.npc[2].direction = DIRECTION_LEFT;
//        gp.npc[2].worldX = gp.tileSize * 14;
//        gp.npc[2].worldY = gp.tileSize * 11;
//
//        gp.npc[3] = new Npc_Troig(gp, keyH);
//        gp.npc[3].direction = DIRECTION_RIGHT;
//        gp.npc[3].worldX = gp.tileSize * 15;
//        gp.npc[3].worldY = gp.tileSize * 11;
    }

    public void setEnemy(){
        gp.enemy[0] = new Enemy_GreenSlime(gp, keyH);
        gp.enemy[0].worldX = gp.tileSize * 13;
        gp.enemy[0].worldY = gp.tileSize * 13;

        gp.enemy[1] = new Enemy_GreenSlime(gp, keyH);
        gp.enemy[1].worldX = gp.tileSize * 14;
        gp.enemy[1].worldY = gp.tileSize * 14;
    }

}
