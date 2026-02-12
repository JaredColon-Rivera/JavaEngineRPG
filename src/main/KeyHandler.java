package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static util.constants.KeyConstants.*;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    public boolean debugMode;

    GamePanel gp;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(gp.gameState == gp.playState){

            if(code == KEY_UP) upPressed = true;
            if(code == KEY_DOWN) downPressed = true;
            if(code == KEY_LEFT) leftPressed = true;
            if(code == KEY_RIGHT) rightPressed = true;
            if(code == KEY_PAUSE) {
                gp.gameState = gp.pauseState;
            }
            if(code == KEY_ENTER){
                enterPressed = true;
            }
        }
        // Pause State
        else if(gp.gameState == gp.pauseState){
             if(code == KEY_PAUSE){
                gp.gameState = gp.playState;
            }

        }

        // Dialogue State
        else if(gp.gameState == gp.dialogueState){
            if(code == KEY_ENTER){
                gp.gameState = gp.playState;
            }
        }

        // DEBUG
        if(code == KEY_DEBUG){
            if(!debugMode){
                debugMode = true;
            }
            else if(debugMode){
                debugMode = false;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KEY_UP) upPressed = false;
        if(code == KEY_DOWN) downPressed = false;
        if(code == KEY_LEFT) leftPressed = false;
        if(code == KEY_RIGHT) rightPressed = false;


    }
}
