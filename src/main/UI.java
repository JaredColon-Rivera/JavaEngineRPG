package main;

import entity.Entity;
import object.Obj_Heart;
import object.Obj_Key;
import object.ParentObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B, comicSans, comicSansB, century;
    // BufferedImage keyImage;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    KeyHandler keyH;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) throws IOException, FontFormatException {

        this.gp = gp;
        this.keyH = keyH;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Comic Sans", Font.BOLD, 80);

        // InputStream is = getClass().getResourceAsStream("/font/comic.ttf");
        InputStream is = getClass().getResourceAsStream("/font/CENTURY.TTF");
        comicSans = Font.createFont(Font.TRUETYPE_FONT, is);

        // Obj_Key key = new Obj_Key(gp);
        // keyImage = key.image;

        Entity heart = new Obj_Heart(gp, keyH);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        this.g2 = g2;
        g2.setFont(comicSans);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2.setColor(Color.white);

        // TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        // PLAY STATE
        if(gp.gameState == gp.playState){
            // Do play state stuff later
            drawPlayerLife();
        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
            drawPlayerLife();
        }
        // Dialouge State
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
//        if(gameFinished){
//
//            String text;
//            int textLength;
//            int x;
//            int y;
//
//            // Final Quest message
//            g2.setFont(arial_40);
//            g2.setColor(Color.white);
//            text = "You found the treasure!";
//            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            x = gp.screenWidth/2 - textLength/2;
//            y = gp.screenHeight/2 - (gp.tileSize * 3);
//            g2.drawString(text, x, y);
//
//            // Final time
//            text = "Your time is: " + dFormat.format(playTime);
//            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            x = gp.screenWidth/2 - textLength/2;
//            y = gp.screenHeight/2 + (gp.tileSize * 4);
//            g2.drawString(text, x, y);
//
//            // Final message
//            g2.setFont(arial_80B);
//            g2.setColor(Color.YELLOW);
//            text = "Congratulations!";
//            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            x = gp.screenWidth/2 - textLength/2;
//            y = gp.screenHeight/2 + (gp.tileSize * 2);
//            g2.drawString(text, x, y);
//
//            gp.gameThread = null;
//        }
//        else{
//
//            g2.setFont(arial_40);
//            g2.setColor(Color.white);
//            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
//            g2.drawString("x " + gp.player.hasKey, 74, 65);
//
//            // Time
//            playTime += (double)1/60;
//            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*11, 65);
//
//            // Message
//            if(messageOn){
//                g2.setFont(g2.getFont().deriveFont(30F));
//                g2.drawString(message, gp.tileSize/2, gp.tileSize * 5);
//
//                messageCounter++;
//
//                if(messageCounter > 120){
//                    messageCounter = 0;
//                    messageOn = false;
//                }
//            }
//        }
    }

    public void drawPlayerLife(){

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;

        int i = 0;

        // Draw Max Life
        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // Reset
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        // Draw Current Life
        while(i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x+= gp.tileSize;
        }

    }


    public void drawTitleScreen(){
        // TITLE NAME
        g2.setColor(Color.black);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 45F));
        String text = "The Adventures of Lemon Head";
        int x = getXForCenteredText(text);
        int y = gp.tileSize * 3;

        // SHADOW
        g2.setColor(Color.GRAY);
        g2.drawString(text, x + 3, y + 3);

        // MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // PLAYER IMAGE
        x = gp.screenWidth/2 - (gp.tileSize * 2)/2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
        g2.drawImage(gp.player.face_down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
        g2.drawImage(gp.player.armor_idle_down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

        text = "NEW GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize * 3.5;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y);
            g2.drawString("<", x + (gp.tileSize + 220), y);
        }

        text = "LOAD GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x - gp.tileSize, y);
            g2.drawString("<", x + (gp.tileSize + 235), y);

        }

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">", x - gp.tileSize, y);
            g2.drawString("<", x + (gp.tileSize + 82), y);

        }



    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen(){

        // WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }


    }
    public void drawSubWindow(int x, int y, int width, int height){

        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);

    }

    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
