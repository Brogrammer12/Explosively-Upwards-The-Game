package Objects;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.everythingManager;

public class subspaceNadeChain extends object{
public boolean collisionFound=false;
    public subspaceNadeChain(everythingManager em) {
        super(em);
        worldX=(int) em.p1.worldX;
        worldY=(int) em.p1.worldY;
        loadImage();
    }
    public void loadImage() {
        try {
            image=ImageIO.read(getClass().getResourceAsStream("/resources/bomb/explosionMove.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void objFunction(Graphics2D g2) {
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
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            if (em.tileM.tile[tileNum1].grounded==false || em.tileM.tile[tileNum2].grounded==false) {
                if (em.cChecker.ceiling==true) {
                    em.p1.worldY+=5;
                }
                //entity.collisionOn=true;
                //em.stopX=false;
                //entity.velocityX=0;
                collisionFound=true;
                //explode=true;
                em.p1.velocityX=0;
                em.stopX=false;
                em.stopXR=false;
                em.p1.worldX+=20;
            }
        }
            entityRightcol=(entityRightWorldX+em.p1.moveSpeed)/em.resTileSize;
        tileNum1=em.tileM.mapTileNum[entityRightcol] [entityTopRow].tileNum;
        tileNum2=em.tileM.mapTileNum[entityRightcol] [bottomRow].tileNum;
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            if (em.tileM.tile[tileNum1].grounded==false || em.tileM.tile[tileNum2].grounded==false) {
                if (em.cChecker.ceiling==true) {
                    em.p1.worldY+=5;
                }
                //entity.collisionOn=true;
                //em.stopXR=false;
                //entity.velocityX=0;
                collisionFound=true;
                //explode=true;
                em.p1.velocityX=0;
                em.stopX=false;
                em.stopXR=false;
                em.p1.worldX-=20;
            }
        }
        
           
            
        //em.p1.currentlyColliding=false;
        if (collisionFound==false) {
            if (em.p1.aimDirection=="down" && explode==false) {
            em.p1.velocityY=-15;
             explode=true;
        }
        else if(em.p1.aimDirection=="up" && explode==false) {
            em.p1.velocityY=15;
            explode=true;
        }
        else if(em.p1.aimDirection=="diagRightDown" && explode==false) {
            em.p1.velocityY=-15;
            if (em.p1.collisionOn==false) {
            em.p1.velocityX=-10;
            em.stopX=true;
            em.stopXR=false;
            }
            explode=true;
        }
        else if(em.p1.aimDirection=="diagRightUp" && explode==false) {
            em.p1.velocityY=15;
            if (em.p1.collisionOn==false) {
                em.p1.velocityX=-10;
            em.stopX=true;
            em.stopXR=false;
            }
            explode=true;
        }
        else if(em.p1.aimDirection=="diagLeftUp" && explode==false) {
            em.p1.velocityY=15;
            if (em.p1.collisionOn==false) {
                 em.p1.velocityX=10;
            em.stopXR=true;
            em.stopX=false;
            }
            explode=true;
        }
        else if(em.p1.aimDirection=="diagLeftDown" && explode==false) {
            em.p1.velocityY=-15;
            if (em.p1.collisionOn==false) {
                em.p1.velocityX=10;
            em.stopXR=true;
            em.stopX=false;
            }
            explode=true;
        }
        }
        timer++;
        screenX=(int) (worldX-em.p1.worldX+em.p1.screenX);
        screenY=(int) (worldY-em.p1.worldY+em.p1.screenY);
        if (timer<25) {
            g2.drawImage(image, screenX, screenY, em.resTileSize, em.resTileSize, null);
        }
        else {
            if (kys==false) {
                em.p1.bombsLeftMove=10;
                kys=true;
            }
        }
    }

}
