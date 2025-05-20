package Objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.everythingManager;

public class Exit extends object{
everythingManager em;
    public Exit(everythingManager em) {
        super(em);
        this.em=em;
        solidArea.width=96;
        worldX=em.maxWorldHoriz*em.resTileSize-300;
        worldY=300;
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
         int screenX=(int) (worldX-em.p1.worldX+em.p1.screenX);
            int screenY=(int) (worldY-em.p1.worldY+em.p1.screenY);
        solidArea.x=worldX;
        solidArea.y=worldY;
        em.p1.solidArea.x=(int) em.p1.worldX;
        em.p1.solidArea.y=(int) em.p1.worldY;
        if (solidArea.intersects(em.p1.solidArea) && em.p1.Level==1) {
             if (em.p1.worldX<worldX-25) {
                em.k.sRightPressed=true;
                em.p1.worldX+=em.p1.moveSpeed;
        
            }
            else if(em.p1.worldX>=worldX-100) {
                em.k.sRightPressed=false;
                em.p1.idle=true;
                em.k.sUpPressed=true;
                    screenY-=moveSpeed;
                    worldY-=moveSpeed;
                    em.p1.screenY-=moveSpeed;
                    em.p1.worldY-=moveSpeed;
                    if (em.p1.screenY<=-em.resTileSize*2-30) {
                        em.bDrawer.index=1;
                        for (int i=0; i<em.tileM.mapTileNum.length; i++) {
                            for (int e=0; e<em.tileM.mapTileNum[i].length; e++) {
                                em.tileM.mapTileNum[i] [e]=0;
                            }
                        }
                        g2.setFont(new Font("Pixeboy",Font.BOLD, 30));
                        g2.setColor(Color.WHITE);
                        g2.drawString("Level 2", (em.maxScreenHoriz*em.resTileSize)/2, (em.maxScreenVert*em.resTileSize)/2);
                    }
                if (em.p1.screenY<=-em.resTileSize*3-200) {
                    em.k.sUpPressed=false;
                    em.k.sRightPressed=false;
                    em.tileM.newMap("/resources/maps/map2.txt");
                    em.tileM.loadMap("/resources/maps/map2.txt");
                    em.p1.Level++;
                    em.p1.worldX=300;
                    em.p1.worldY=(em.maxWorldVert*em.resTileSize)-500;
                    em.p1.screenX=em.screenWidth/2-(em.resTileSize*3)/2;
                    em.p1.screenY=em.screenHeight/2-(em.resTileSize*3)/2;
                }
                
            }
        }
         solidArea.x=0;
        solidArea.y=0;
        em.p1.solidArea.x=0;
        em.p1.solidArea.y=0;
            g2.drawImage(image, screenX, screenY, 96, em.resTileSize, null);
            if (em.showHitboxes==true) {
                g2.setColor(Color.BLUE);
        g2.drawRect(screenX+solidArea.x, screenY+solidArea.y, solidArea.width, solidArea.height);
            }
    }

}
