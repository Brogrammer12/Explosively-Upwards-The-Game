package Objects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.everythingManager;

public class arrow extends object{
int arrowIndex;
int index;
int Level;
    public arrow(everythingManager em, int index, int arrowIndex) {
        super(em);
        this.arrowIndex=arrowIndex;
        this.index=index;
        Level=em.npc[index].Level;
        direction=em.npc[index].direction;
        worldX=(int) em.npc[index].worldX;
        worldY=(int) em.npc[index].worldY;
    }
    @Override
    public void objFunction(Graphics2D g2) {
        Rectangle playerArea=new Rectangle(em.p1.defaultSolidArea.x, em.p1.defaultSolidArea.y, em.p1.defaultSolidArea.width,em.p1.defaultSolidArea.height);
        if (Level==em.p1.Level) {
            if (Math.abs(worldX-em.p1.worldX)<200 && Math.abs(worldY-em.p1.worldY)<200) {
                 try {
                int entityLeftWorldX=(int) (em.p1.worldX+em.p1.solidArea.x);
        int entityRightWorldX=(int) (em.p1.worldX+em.p1.solidArea.x+em.p1.solidArea.width);
        int entityMiddleWorldX=(int) (em.p1.worldX+em.p1.solidArea.x+em.p1.solidArea.width/2);
        int entityTopWorldY=(int) (em.p1.worldY+em.p1.solidArea.y);
        int entityBottomWorldY=(int) (em.p1.worldY+em.p1.solidArea.y+em.p1.solidArea.height);
        int entityLeftcol=entityLeftWorldX/em.resTileSize;
        int entityRightcol=entityRightWorldX/em.resTileSize;
        int entityMiddlecol=entityMiddleWorldX/em.resTileSize;
        int entityTopRow=entityTopWorldY/em.resTileSize;
        int entityBottomRow=entityBottomWorldY/em.resTileSize;
        int tileNum1, tileNum2, tileNum3;
            int bottomRow=(entityBottomWorldY-em.resTileSize)/em.resTileSize;
            entityLeftcol=(entityLeftWorldX-em.p1.moveSpeed)/em.resTileSize;
        tileNum1=em.tileM.mapTileNum[entityLeftcol] [entityTopRow].tileNum;
        tileNum2=em.tileM.mapTileNum[entityLeftcol] [bottomRow].tileNum;
        //collisionFound=false;
        //collisionFoundRight=false;
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            if (em.tileM.tile[tileNum1].grounded==false || em.tileM.tile[tileNum2].grounded==false) {
                //entity.collisionOn=true;
                //em.stopX=false;
                //entity.velocityX=0;
                //collisionFound=true;
                //explode=true;
                em.p1.velocityX=0;
                em.stopX=false;
                em.stopXR=false;
                em.p1.worldX+=5;
                //worldX+=10;
            }
        }
            entityRightcol=(entityRightWorldX+em.p1.moveSpeed)/em.resTileSize;
        tileNum1=em.tileM.mapTileNum[entityRightcol] [entityTopRow].tileNum;
        tileNum2=em.tileM.mapTileNum[entityRightcol] [bottomRow].tileNum;
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            if (em.tileM.tile[tileNum1].grounded==false || em.tileM.tile[tileNum2].grounded==false) {
                
                //entity.collisionOn=true;
                //em.stopXR=false;
                //entity.velocityX=0;
                //collisionFoundRight=true;
                //explode=true;
                em.p1.velocityX=0;
                em.stopX=false;
                em.stopXR=false;
                em.p1.worldX-=5;
                //worldX-=10;
            }
        }
            }
            catch (Exception e) {

            }
            }
            if (em.paused==false) {
                if (em.npc[index]==null) {
            em.obj[arrowIndex]=null;
            return;
        }
        em.cChecker.checkBomb(this);
        solidArea.x=worldX;
        solidArea.y=worldY;
        playerArea.x=(int) em.p1.worldX;
        playerArea.y=(int) em.p1.worldY;
        if (solidArea.intersects(playerArea)) {
            kys=true;
            if (direction=="left") {
                em.p1.velocityX-=10;
                if (em.stopX==false) {
                    em.p1.health--;
                }
                em.stopX=true;
            }
            else {
                em.p1.velocityX+=10;
                if (em.stopXR==false) {
                    em.p1.health--;
                }
                em.stopXR=true;
            }

        }
        if (kys==true) {
            em.obj[arrowIndex]=null;
            return;
        }
         g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
         try {
            if (direction=="right") {
            image=ImageIO.read(getClass().getResourceAsStream("/resources/bomb/arrowRight.png"));
            worldX+=3;
            worldY+=2;
        }
        else {
            image=ImageIO.read(getClass().getResourceAsStream("/resources/bomb/arrowLeft.png"));
            worldX-=3;
            worldY+=2;
        }
         }
         catch (Exception e) {

         }
             solidArea.x=0;
        solidArea.y=0;
        playerArea.x=em.p1.defaultSolidArea.x;
        playerArea.y=em.p1.defaultSolidArea.y;
            }
             int screenX=(int) (worldX-em.p1.worldX+em.p1.screenX);
            int screenY=(int) (worldY-em.p1.worldY+em.p1.screenY);
                g2.drawImage(image, screenX, screenY, em.resTileSize, em.resTileSize, null);
            if (em.showHitboxes==true) {
            g2.setColor(Color.RED);
        g2.drawRect(screenX+solidArea.x, screenY+solidArea.y, solidArea.width, solidArea.height);
        //this code shows player hitbox
        }
        }
        else {
            em.obj[arrowIndex]=null;
            return;
        }
    }

}
