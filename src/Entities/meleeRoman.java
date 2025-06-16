package Entities;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Color;
import javax.imageio.ImageIO;

import main.everythingManager;
public class meleeRoman extends Entity{
    public BufferedImage right1, right2, left1, left2;
    public meleeRoman(everythingManager em) {
        super(em);
        direction="right";
        healtha=3;
        maxHealth=3;
        solidArea=new Rectangle(20, 0, em.resTileSize*2-10, em.resTileSize*3-20);
        defaultSolidArea.x=20;
        defaultSolidArea.y=0;
       worldX=em.resTileSize*10;
       worldY=em.resTileSize*em.maxWorldVert-410;
        imageLoader();
    }
    public void imageLoader() {
        try {
            right1=ImageIO.read(getClass().getResourceAsStream("/resources/Enemies/meleeRoman/meleeRomanRight1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("/resources/Enemies/meleeRoman/meleeRomanRight2.png"));
            left1=ImageIO.read(getClass().getResourceAsStream("/resources/Enemies/meleeRoman/meleeRomanLeft1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("/resources/Enemies/meleeRoman/meleeRomanLeft2.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void update() {
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
        em.cChecker.checkPlayer(this);
        if (grounded==false && direction=="right") {
            direction="left";
            worldX-=2;
        }
        else if(grounded==false && direction=="left") {
            direction="right";
            worldX+=2;
        }
        if (direction=="left") {
            worldX-=2;
        }
        else if(direction=="right") {
            worldX+=2;
        }
        }
    }
    public void draw(Graphics2D g2) {
        if (healtha>=2) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
         image=null;
         solidArea.x=(int) worldX;
         solidArea.y=(int) worldY;
         em.p1.solidArea.x=(int) em.p1.worldX;
         em.p1.solidArea.y=(int) em.p1.worldY;
            if (solidArea.intersects(em.p1.solidArea)) {
            if (direction=="left") {
                em.p1.velocityX-=10;
                em.stopX=true;
            }
            else if(direction=="right") {
                em.p1.velocityX+=10;
                em.stopXR=true;
            }
         }
         
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
         solidArea.x=defaultSolidArea.x;
         solidArea.y=defaultSolidArea.y;
         em.p1.solidArea.x=em.p1.defaultSolidArea.x;
         em.p1.solidArea.y=em.p1.defaultSolidArea.y;
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
