package Objects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.everythingManager;

public class arrow extends object{
int arrowIndex;
    public arrow(everythingManager em, int index, int arrowIndex) {
        super(em);
        this.arrowIndex=arrowIndex;
        direction=em.npc[index].direction;
        worldX=(int) em.npc[index].worldX;
        worldY=(int) em.npc[index].worldY;
    }
    @Override
    public void objFunction(Graphics2D g2) {
        em.cChecker.checkBomb(this);
        if (kys==true) {
            em.obj[arrowIndex]=null;
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
         int screenX=(int) (worldX-em.p1.worldX+em.p1.screenX);
            int screenY=(int) (worldY-em.p1.worldY+em.p1.screenY);
                g2.drawImage(image, screenX, screenY, em.resTileSize, em.resTileSize, null);
            if (em.showHitboxes==true) {
            g2.setColor(Color.RED);
        g2.drawRect(screenX+solidArea.x, screenY+solidArea.y, solidArea.width, solidArea.height);
        //this code shows player hitbox
        }
    }

}
