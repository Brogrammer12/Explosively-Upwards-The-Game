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
    public int screenX;
    public int screenY;
    public int worldX;
    public int worldY;
    public int cautionTimer=0;
    public int width=48;
    public int height=48;
    public float velocityY=0;
    int entityLeftcol;
        int entityRightcol;
        int entityMiddlecol;
            int entityBottomWorldY;
            int entityBottomRow;
        int tileNum1;
        int tileNum2;
        int tileNum3;
    public boolean tileFound=false;
    public boolean lavaFinished=false;
    public BufferedImage cautionSign;
    public BufferedImage lavaWave;
    public BufferedImage lavaWave2;
    public BufferedImage lavaWave3;
    public int previousValue=0;
    public tileBreak(everythingManager em) {
        this.em=em;
        imageLoader();
    }
    public void imageLoader() {
        try {
            cautionSign=ImageIO.read(getClass().getResourceAsStream("/resources/events/caution.png"));
            lavaWave=ImageIO.read(getClass().getResourceAsStream("/resources/events/lava1.png"));
            lavaWave2=ImageIO.read(getClass().getResourceAsStream("/resources/events/lava2.png"));
            lavaWave3=ImageIO.read(getClass().getResourceAsStream("/resources/events/lava3.png"));
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
         tileNum1=em.tileM.mapTileNum[entityLeftcol] [entityBottomRow].tileNum;
         tileNum2=em.tileM.mapTileNum[entityRightcol] [entityBottomRow].tileNum;
         tileNum3=em.tileM.mapTileNum[entityMiddlecol] [entityBottomRow].tileNum;
            }
        if ((em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true || em.tileM.tile[tileNum3].collision==true) || tileFound==true) {
            if (em.p1.falling==false || tileFound==true) {
                tileFound=true;
                //System.out.println("that shit is about to break u stupid nigger!");
                timer++;
                if (timer<100) {
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
                }
                if (timer<100) {
                    worldX=entityMiddlecol*em.resTileSize;
                    worldY=entityBottomRow*em.resTileSize;
                    screenX=(int) (entityMiddlecol*em.resTileSize-em.p1.worldX+em.p1.screenX);
            screenY=(int) (entityBottomRow*em.resTileSize-em.p1.worldY+em.p1.screenY);
            width=em.resTileSize;
            height=em.resTileSize;
                }
                else {
                    screenX=(int) (worldX-em.p1.worldX+em.p1.screenX);
            screenY=(int) (worldY-em.p1.worldY+em.p1.screenY);
                }
                g2.drawImage(image, screenX, screenY, width, height, null);
                if (timer>=100) {
                    if (timer==100) {
                        worldX-=200;
                        worldY+=200;
                        velocityY=-15;
                    }
                    width=em.resTileSize*3;
                    height=em.resTileSize*3;
                    //image=lavaWave;
                    velocityY+=0.4f;
        velocityY=Math.min(velocityY, 15);
        worldY+=velocityY;
                    worldX+=3;
                    if (velocityY<0 && velocityY>-8) {
                        image=lavaWave2;
                    }
                    else if(velocityY>0) {
                        image=lavaWave3;
                    }
                    else {
                        image=lavaWave;
                    }
                    if (screenY>=em.maxScreenVert*em.resTileSize && image!=lavaWave) {
                        lavaFinished=true;
                    }
                    if (lavaFinished==true) {
                        lavaFinished=false;
                        timer=0;
                    em.playSE(4);
                    image=null;
                    tileFound=false;
                    em.tileM.mapTileNum[entityLeftcol] [entityBottomRow].tileNum=0;
                    em.tileM.mapTileNum[entityRightcol] [entityBottomRow].tileNum=0;
                    em.tileM.mapTileNum[entityMiddlecol] [entityBottomRow].tileNum=0;
                    }
                }
            }
        }
        }
}
