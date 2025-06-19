package Objects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.everythingManager;

public class Exit extends object{
everythingManager em;
public String playerDirection;
    public Exit(everythingManager em) {
        super(em);
        this.em=em;
        solidArea.width=96;
        worldX=em.maxWorldHoriz*em.resTileSize-300;
        worldY=250;
        imageLoader();
    }
    public void imageLoader() {
        try {
            image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/Cloud.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public void objFunction(Graphics2D g2) {
        System.out.println(playerDirection);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
         int screenX=(int) (worldX-em.p1.worldX+em.p1.screenX);
            int screenY=(int) (worldY-em.p1.worldY+em.p1.screenY);
        solidArea.x=worldX;
        solidArea.y=worldY;
        em.p1.solidArea.x=(int) em.p1.worldX;
        em.p1.solidArea.y=(int) em.p1.worldY;
        if (solidArea.intersects(em.p1.solidArea)) {
            if (em.p1.worldX<worldX && playerDirection==null) {
                playerDirection="left";
            }
            else if(em.p1.worldX>worldX && playerDirection==null) {
                playerDirection="right";
            }
            if (playerDirection=="left") {
                if (em.p1.worldX<worldX-25 && em.p1.grounded==true) {
                em.k.sRightPressed=true;
                em.p1.worldX+=em.p1.moveSpeed;
        
            }
            else if(em.p1.worldX>=worldX-100 && em.p1.grounded==true) {
                em.k.sRightPressed=false;
                em.p1.idle=true;
                em.k.sUpPressed=true;
                    screenY-=moveSpeed;
                    worldY-=moveSpeed;
                    em.p1.screenY-=moveSpeed;
                    em.p1.worldY-=moveSpeed;
                    if (em.p1.screenY<=-em.resTileSize*2-65) {
                        em.bDrawer.index=1;
                        for (int i=0; i<em.tileM.mapTileNum.length; i++) {
                            for (int e=0; e<em.tileM.mapTileNum[i].length; e++) {
                                em.tileM.mapTileNum[i] [e]=0;
                            }
                        }
                        g2.setFont(new Font("Pixeboy",Font.BOLD, 30));
                        g2.setColor(Color.WHITE);
                       int nextLevel=em.p1.Level+1;
                        g2.drawString("Level: "+nextLevel, (em.maxScreenHoriz*em.resTileSize)/2, (em.maxScreenVert*em.resTileSize)/2);
                    }
                if (em.p1.screenY<=-em.resTileSize*3-200) {
                    em.bDrawer.index=2;
                    em.k.sUpPressed=false;
                    em.k.sRightPressed=false;
                    int nextLevel=em.p1.Level+1;
                    String level="Level "+nextLevel+".tmj";
                    em.tileM.newMap(level);
                    em.p1.Level++;
                    em.p1.worldX=300;
                    em.p1.worldY=(em.maxWorldVert*em.resTileSize)-500;
                    worldX=em.Exits[nextLevel-1].x;
                    worldY=em.Exits[nextLevel-1].y;
                   // worldX=1500;
                   // worldY=790;
                    em.p1.screenX=em.screenWidth/2-(em.resTileSize*3)/2;
                    em.p1.screenY=em.screenHeight/2-(em.resTileSize*3)/2;
                }
                
            }
            }
            else if(playerDirection=="right") {
                if (em.p1.worldX>worldX && em.p1.grounded==true) {
                em.k.sRightPressed=true;
                em.p1.worldX-=em.p1.moveSpeed;
        
            }
            else if(em.p1.worldX<=worldX && em.p1.grounded==true) {
                em.k.sRightPressed=false;
                em.p1.idle=true;
                em.k.sUpPressed=true;
                    screenY-=moveSpeed;
                    worldY-=moveSpeed;
                    em.p1.screenY-=moveSpeed;
                    em.p1.worldY-=moveSpeed;
                    if (em.p1.screenY<=-em.resTileSize*2-65) {
                        em.bDrawer.index=1;
                        for (int i=0; i<em.tileM.mapTileNum.length; i++) {
                            for (int e=0; e<em.tileM.mapTileNum[i].length; e++) {
                                em.tileM.mapTileNum[i] [e]=0;
                            }
                        }
                        g2.setFont(new Font("Pixeboy",Font.BOLD, 30));
                        g2.setColor(Color.WHITE);
                       int nextLevel=em.p1.Level+1;
                        g2.drawString("Level: "+nextLevel, (em.maxScreenHoriz*em.resTileSize)/2, (em.maxScreenVert*em.resTileSize)/2);
                    }
                if (em.p1.screenY<=-em.resTileSize*3-200) {
                    em.bDrawer.index=2;
                    em.k.sUpPressed=false;
                    em.k.sRightPressed=false;
                    int nextLevel=em.p1.Level+1;
                    String level="Level "+nextLevel+".tmj";
                    em.tileM.newMap(level);
                    em.p1.Level++;
                    em.p1.worldX=300;
                    em.p1.worldY=(em.maxWorldVert*em.resTileSize)-500;
                    worldX=em.Exits[nextLevel-1].x;
                    worldY=em.Exits[nextLevel-1].y;
                   // worldX=1500;
                   // worldY=790;
                    em.p1.screenX=em.screenWidth/2-(em.resTileSize*3)/2;
                    em.p1.screenY=em.screenHeight/2-(em.resTileSize*3)/2;
                }
                
            }
            }
        }
         solidArea.x=0;
        solidArea.y=0;
        playerDirection=null;
        em.p1.solidArea.x=em.p1.defaultSolidArea.x;
        em.p1.solidArea.y=em.p1.defaultSolidArea.y;
            g2.drawImage(image, screenX, screenY, 96, em.resTileSize, null);
            if (em.showHitboxes==true) {
                g2.setColor(Color.BLUE);
        g2.drawRect(screenX+solidArea.x, screenY+solidArea.y, solidArea.width, solidArea.height);
            }
    }

}
