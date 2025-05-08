package Objects;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.everythingManager;

public class bombBoi extends object{
    everythingManager em;
    public boolean instantiateCoords=false;
    public bombBoi(everythingManager em, String direction) {
        super(em);
        this.em=em;
        this.direction=direction;
        if (direction=="right") {
            if (em.k.downPressed==true) {
                worldX=(int) (em.p1.worldX+(em.resTileSize*3)/2+40);
            worldY=(int) (em.p1.worldY+(em.resTileSize*3)/2-20);
            }
            else {
                worldX=(int) (em.p1.worldX+(em.resTileSize*3)/2+40);
            worldY=(int) (em.p1.worldY+(em.resTileSize*3)/2-30);
            }
        }
        else if(direction=="left") {
            if (em.k.downPressed==true) {
                worldX=(int) (em.p1.worldX+(em.resTileSize*3)/2-90);
            worldY=(int) (em.p1.worldY+(em.resTileSize*3)/2-20);
            }
            else {
                worldX=(int) (em.p1.worldX+(em.resTileSize*3)/2-90);
            worldY=(int) (em.p1.worldY+(em.resTileSize*3)/2-30);
            }
        }
        try {
            image=ImageIO.read(getClass().getResourceAsStream("/resources/Player/Bomb.png"));
            altImage=ImageIO.read(getClass().getResourceAsStream("/resources/Player/bombPlanted.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public void objFunction(Graphics2D g2) {
        em.cChecker.checkBomb(this);
        if (bombTriggered==false) {
            if (direction=="right") {
                worldX+=moveSpeed;
                worldY+=2;
            }
            else if(direction=="left") {
                worldX-=moveSpeed;
                worldY+=2;
            }
        }
        else if(bombTriggered==true) {
            image=altImage;
        }
        screenX=(int) (worldX-em.p1.worldX+em.p1.screenX);
        screenY=(int) (worldY-em.p1.worldY+em.p1.screenY);
        g2.drawImage(image, screenX, screenY, em.resTileSize, em.resTileSize, null);
    }

}
