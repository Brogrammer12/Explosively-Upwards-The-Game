package Entities;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Objects.arrow;
import Objects.bombBoi;
import main.everythingManager;

public class rangedRoman extends Entity{
public boolean Grand;
public BufferedImage right1, right2, left1, left2;
int index=0;
public int thisIndex=0;
    public rangedRoman(everythingManager em, int worldx, int worldy, int Level, boolean Grand, String direction, int thisIndex) {
        super(em);
        this.direction=direction;
        healtha=3;
        maxHealth=3;
        this.thisIndex=thisIndex;
        this.Grand=Grand;
        this.Level=Level;
        solidArea=new Rectangle(20, 0, em.resTileSize*2-10, em.resTileSize*3-20);
        defaultSolidArea.x=20;
        defaultSolidArea.y=0;
       worldX=worldx;
       worldY=worldy;
        imageLoader();
    }
    public void imageLoader() {
         try {
            if (Grand==false) {
                right1=ImageIO.read(getClass().getResourceAsStream("/resources/Enemies/rangedRoman/rangedRomanSoldierRight1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("/resources/Enemies/rangedRoman/rangedRomanSoldierRight2.png"));
            left1=ImageIO.read(getClass().getResourceAsStream("/resources/Enemies/rangedRoman/rangedRomanSoldierLeft1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("/resources/Enemies/rangedRoman/rangedRomanSoldierLeft2.png"));
            }
            else {
                right1=ImageIO.read(getClass().getResourceAsStream("/resources/Enemies/rangedRoman/rangedRomanRight1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("/resources/Enemies/rangedRoman/rangedRomanRight2.png"));
            left1=ImageIO.read(getClass().getResourceAsStream("/resources/Enemies/rangedRoman/rangedRomanLeft1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("/resources/Enemies/rangedRoman/rangedRomanLeft2.png"));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public void update() {
        if (Level==em.p1.Level) {
            if (healtha>=2) {
            spriteCounter++;
        if (spriteCounter>=25) {
            spriteCounter=0;
            if (SpriteNum==0) {
                SpriteNum=1;
            }
            else if(SpriteNum==1) {
                SpriteNum=0;
            }
        }
        }
        if (SpriteNum==0 && spriteCounter==0) {
            for (int i=0; i<em.obj.length; i++) {
                if (em.obj[i]==null) {
                    index=i;
                    break;
                }
            }
            em.obj[index]=new arrow(em, thisIndex, index);
            
        }
        }
    }
    @Override
    public void draw(Graphics2D g2) {
         if (Level==em.p1.Level) {
            if (healtha>=2) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
         image=null;
         switch (direction) {
            case "left":
            if (SpriteNum==0) {
                image=left1;
            }
            else if(SpriteNum==1) {
                image=left2;
            }
            break;
            case "right":
            if (SpriteNum==0) {
                image=right1;
            }
            else if(SpriteNum==1) {
                image=right2;
            }
            break;
         }
          int screenX=(int) (worldX-em.p1.worldX+em.p1.screenX);
            int screenY=(int) (worldY-em.p1.worldY+em.p1.screenY);
                g2.drawImage(image, screenX, screenY, em.resTileSize*3-20, em.resTileSize*3-20, null);
            if (em.showHitboxes==true) {
            g2.setColor(Color.RED);
        g2.drawRect(screenX+solidArea.x, screenY+solidArea.y, solidArea.width, solidArea.height);
        //this code shows player hitbox
        }
           
        } 
        }
    }

}
