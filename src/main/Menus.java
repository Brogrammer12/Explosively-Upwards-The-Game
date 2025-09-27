package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Entities.Player;

public class Menus {
    everythingManager em;
    public Font fontPixeboy;
    public boolean warmup=false;
    public BufferedImage PlayerImage, bomb, bombPlanted;
    public int menuTimer=0;
    public int menuLevel=1;
    public boolean startMenuTimer=false;
    public boolean playerDied=false;
    public Menus(everythingManager em) {
        this.em=em;
        try {
            fontPixeboy=Font.createFont(Font.TRUETYPE_FONT, new File("src/Fonts/PixelifySans-VariableFont_wght.ttf"));
            fontPixeboy=fontPixeboy.deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fontPixeboy);
        }
        catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        imageLoader();
    }
public void warmupTextDrawer(Graphics2D g2) {
        if (warmup==false) {
            Graphics2D g2d=g2; // Dummy execution to trigger JIT optimization
    g2d.setFont(fontPixeboy);
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    g2d.setColor(Color.MAGENTA);
    g2d.drawString("Warming Up...", 670, 120);
warmup=true;
        }
    }
    public void imageLoader() {
        try {
            PlayerImage=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerHangingRight.png"));
            bomb=ImageIO.read(getClass().getResourceAsStream("/resources/bomb/Bomb.png"));
            bombPlanted=ImageIO.read(getClass().getResourceAsStream("/resources/bomb/explosion.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void drawTitle(Graphics2D g2) {
        if (em.titleScreenFinished==false) {
            g2.setColor(Color.BLACK);
            fontPixeboy=fontPixeboy.deriveFont(30f);
            g2.setFont(fontPixeboy);
            g2.drawString("Start Game", em.screenWidth/2-75, em.screenHeight/2);
            fontPixeboy=fontPixeboy.deriveFont(50f);
            g2.setFont(fontPixeboy);
            g2.drawString("Explosively Upwards", em.screenWidth/2-230, em.screenHeight/2-200);
            g2.drawImage(PlayerImage, em.screenWidth/2-300, em.screenHeight/2-200, em.resTileSize*3, em.resTileSize*3, null);
        }
        if ((em.k.enterPressed==true || em.m.mouseClicked==true) && em.titleScreenFinished==false) {
            System.out.println("title screen finished");
            em.k.enterPressed=false;
            em.m.mouseClicked=false;
            em.titleScreenFinished=true;
        }
    }
    
    public void drawDeathScreen(Graphics2D g2) {
        if (em.p1.health<=0) {
            em.paused=true;
            playerDied=true;
            fontPixeboy=fontPixeboy.deriveFont(30f);
            g2.setColor(Color.BLACK);
            g2.fillRect(em.screenWidth/2-6*em.resTileSize, 0, 11*em.resTileSize, 11*em.resTileSize+em.resTileSize/2);
            g2.setFont(fontPixeboy);
            g2.setColor(Color.WHITE);
            g2.drawString("YOU DIED", em.screenWidth/2-2*em.resTileSize, 70);
            g2.drawString("RESPAWN?", em.screenWidth/2-2*em.resTileSize, em.resTileSize*6);
            if (em.m.mouseClicked==true || em.k.enterPressed==true) {
                em.p1.Level=1;
                em.bDrawer.index=0;
                em.p1.health=5;
                em.tileM.newMap("level 1.tmj");
                playerDied=false;
                em.m.mouseClicked=false;
                em.k.enterPressed=false;
                em.paused=false;
                em.p1.velocityX=0;
                em.p1.velocityY=0;
                em.instantiate.setObjects();
                em.p1.worldX=em.playerSpawns[0].x;
                em.p1.worldY=em.playerSpawns[0].y;
            }
        }
    }

    public void drawPauseMenu(Graphics2D g2) {
        if (em.paused==true && playerDied==false) {
            fontPixeboy=fontPixeboy.deriveFont(30f);
            g2.setColor(Color.BLACK);
            g2.fillRect(em.screenWidth/2-6*em.resTileSize, 0, 11*em.resTileSize, 11*em.resTileSize+em.resTileSize/2);
            g2.setFont(fontPixeboy);
            g2.setColor(Color.WHITE);
            g2.drawString("SETTINGS", em.screenWidth/2-2*em.resTileSize, 70);
            g2.drawString("EXIT GAME", em.screenWidth/2-2*em.resTileSize, em.resTileSize*8);
            BufferedImage select=null;
            if (startMenuTimer==true) {
                menuTimer++;
                select=bombPlanted;
                if (menuTimer==20) {
                    menuTimer=0;
                    startMenuTimer=false;
                    switch (menuLevel) {
                        case 1:
                        System.exit(0);
                        break;
                    }
                }
            }
            if (em.k.enterPressed==true) {
                startMenuTimer=true;
                if (menuTimer==0) {
                    em.playSE(2);
                }
            }
            else if (startMenuTimer==false) {
                select=bomb;
            }
            g2.drawImage(select, em.screenWidth/2-3*em.resTileSize, em.resTileSize*7+em.resTileSize/4, em.resTileSize, em.resTileSize, null);
        }
    }
}
