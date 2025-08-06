package Events;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Tiles.tile;
import main.everythingManager;

public class tileBreak extends events{
    everythingManager em;
    public int breakX=0;
    public int breakY=0;
    public int timer=0;
    public int cautionTimer=0;
    int entityLeftcol;
        int entityRightcol;
        int entityMiddlecol;
            int entityBottomWorldY;
            int entityBottomRow;
        int tileNum1;
        int tileNum2;
        int tileNum3;
    public boolean tileFound=false;
    public BufferedImage cautionSign;
    public int previousValue=0;
    public tileBreak(everythingManager em) {
        this.em=em;
        imageLoader();
    }
    public void imageLoader() {
        try {
            cautionSign=ImageIO.read(getClass().getResourceAsStream("/resources/events/caution.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
        public void tileEvent(Graphics2D g2) {
            if (tileFound==false) {
                 entityLeftcol=(int) ((em.p1.worldX+em.p1.solidArea.x)/em.resTileSize);
         entityRightcol=(int) ((em.p1.worldX+em.p1.solidArea.x+em.p1.solidArea.width)/em.resTileSize);
         entityMiddlecol=(int) ((em.p1.worldX+em.p1.solidArea.x+em.p1.solidArea.width/2)/em.resTileSize);
             entityBottomWorldY=(int) (em.p1.worldY+em.p1.solidArea.y+em.p1.solidArea.height);
             entityBottomRow=(int) ((entityBottomWorldY+em.p1.velocityY)/em.resTileSize);
         tileNum1=em.tileM.mapTileNum[entityLeftcol] [entityBottomRow];
         tileNum2=em.tileM.mapTileNum[entityRightcol] [entityBottomRow];
         tileNum3=em.tileM.mapTileNum[entityMiddlecol] [entityBottomRow];
            }
        if ((em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true || em.tileM.tile[tileNum3].collision==true) || tileFound==true) {
            if (em.p1.grounded==true || tileFound==true) {
                tileFound=true;
                //System.out.println("that shit is about to break u stupid nigger!");
                timer++;
                if (Math.abs(timer-previousValue)>=20) {
                    if (cautionTimer==0) {
                        image=cautionSign;
                        cautionTimer++;
                        previousValue=timer;
                    }
                    else if (cautionTimer==1) {
                        image=null;
                        cautionTimer--;
                        previousValue=timer;
                    }
                }
                int screenX=(int) (entityMiddlecol*em.resTileSize-em.p1.worldX+em.p1.screenX);
            int screenY=(int) (entityBottomRow*em.resTileSize-em.p1.worldY+em.p1.screenY);
                g2.drawImage(image, screenX, screenY, em.resTileSize, em.resTileSize, null);
                if (timer==100) {
                    timer=0;
                    em.playSE(4);
                    image=null;
                    tileFound=false;
                    em.tileM.mapTileNum[entityLeftcol] [entityBottomRow]=0;
                    em.tileM.mapTileNum[entityRightcol] [entityBottomRow]=0;
                    em.tileM.mapTileNum[entityMiddlecol] [entityBottomRow]=0;
                }
            }
        }
        }
}
