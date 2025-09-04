package Objects;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.everythingManager;

public class subspaceNadeChain extends object{

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
            em.p1.velocityX=-10;
            em.stopX=true;
            em.stopXR=false;
            explode=true;
        }
        else if(em.p1.aimDirection=="diagRightUp" && explode==false) {
            em.p1.velocityY=15;
            em.p1.velocityX=-10;
            em.stopX=true;
            em.stopXR=false;
            explode=true;
        }
        else if(em.p1.aimDirection=="diagLeftUp" && explode==false) {
            em.p1.velocityY=15;
            em.p1.velocityX=10;
            em.stopXR=true;
            em.stopX=false;
            explode=true;
        }
        else if(em.p1.aimDirection=="diagLeftDown" && explode==false) {
            em.p1.velocityY=-15;
            em.p1.velocityX=10;
            em.stopXR=true;
            em.stopX=false;
            explode=true;
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
