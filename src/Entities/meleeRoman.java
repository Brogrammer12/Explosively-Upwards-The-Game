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
    public boolean Grand;
    public boolean subBomb=false;
    public meleeRoman(everythingManager em, int worldx, int worldy, int Level, boolean Grand) {
        super(em);
        direction="right";
        healtha=3;
        maxHealth=3;
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
                right1=ImageIO.read(getClass().getResourceAsStream("/resources/Enemies/meleeRoman/meleeRomanSoldierRight1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("/resources/Enemies/meleeRoman/meleeRomanSoldierRight2.png"));
            left1=ImageIO.read(getClass().getResourceAsStream("/resources/Enemies/meleeRoman/meleeRomanSoldierLeft1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("/resources/Enemies/meleeRoman/meleeRomanSoldierLeft2.png"));
            }
            else {
                right1=ImageIO.read(getClass().getResourceAsStream("/resources/Enemies/meleeRoman/meleeRomanRight1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("/resources/Enemies/meleeRoman/meleeRomanRight2.png"));
            left1=ImageIO.read(getClass().getResourceAsStream("/resources/Enemies/meleeRoman/meleeRomanLeft1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("/resources/Enemies/meleeRoman/meleeRomanLeft2.png"));
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
        collisionOn=false;
        em.cChecker.checkPlayer(this);
        if ((grounded==false && direction.equals("right")) || collisionOn==true && direction.equals("right")) {
            direction="left";
            worldX-=2;
        }
        else if((grounded==false && direction.equals("left")) || collisionOn==true && direction.equals("left")) {
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
    }
    @Override
    public void draw(Graphics2D g2) {
        if (Level==em.p1.Level) {
            Rectangle playerArea=new Rectangle(em.p1.solidArea.x, em.p1.solidArea.y, em.p1.solidArea.width,em.p1.solidArea.height);
            Rectangle thisArea=new Rectangle(solidArea.x, solidArea.y, solidArea.width, solidArea.height);
            if (healtha>=2) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
         image=null;
         thisArea.x+=(int) worldX;
         thisArea.y+=(int) worldY;
         playerArea.x+=(int) em.p1.worldX;
         playerArea.y+=(int) em.p1.worldY;
         for (int i=0; i<em.objBomb.length; i++) {
            if (em.objBomb[i]!=null) {
                if (em.objBomb[i].explode==true && em.objBomb[i].Move==true) {
                subBomb=true;
            }
            }
         }
            if (thisArea.intersects(playerArea) && subBomb==false) {
            if (direction.equals("left")) {
                if (em.stopX==false) {
                    em.p1.health--;
                    em.p1.velocityX-=10;
                    //worldX+=50;
                }
                em.stopX=true;
            }
            else if(direction.equals("right")) {
                if (em.stopXR==false) {
                    em.p1.health--;
                    em.p1.velocityX+=10;
                    //worldX-=50;
                }
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
         thisArea.x=defaultSolidArea.x;
         thisArea.y=defaultSolidArea.y;
         playerArea.x=em.p1.solidArea.x;
         playerArea.y=em.p1.solidArea.y;
          int screenX=(int) (worldX-em.p1.worldX+em.p1.screenX);
            int screenY=(int) (worldY-em.p1.worldY+em.p1.screenY);
            subBomb=false;
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
