package Objects;

import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import main.everythingManager;

public class bubbles extends object{

    public bubbles(everythingManager em, String direction, int worldx, int worldy, int Level) {
        super(em);
        try {
            worldX=worldx;
            worldY=worldy;
            this.Level=Level;
            this.direction=direction;
            image=ImageIO.read(getClass().getResourceAsStream("/resources/objs/bubbles.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void objFunction(Graphics2D g2) {
        if (Level==em.p1.Level) {
            int screenX=(int) (worldX-em.p1.worldX+em.p1.screenX);
        int screenY=(int) (worldY-em.p1.worldY+em.p1.screenY);
        Rectangle playerArea=new Rectangle(em.p1.defaultSolidArea.x, em.p1.defaultSolidArea.y, em.p1.defaultSolidArea.width,em.p1.defaultSolidArea.height);
        solidArea.x=worldX;
        solidArea.y=worldY;
        playerArea.x=(int) em.p1.worldX;
        playerArea.y=(int) em.p1.worldY;
        if (playerArea.intersects(solidArea)) {
           if (direction=="left") {
            em.p1.velocityX=-9;
            em.stopX=true;
           }
           else {
            em.p1.velocityX=+9;
            em.stopXR=true;
           }
           timer++;
        }
        else {
            timer=0;
        }
        solidArea.x=0;
        solidArea.y=0;
        playerArea.x=em.p1.defaultSolidArea.x;
        playerArea.y=em.p1.defaultSolidArea.y;
        g2.drawImage(image, screenX, screenY, em.resTileSize, em.resTileSize, null);
        if (em.showHitboxes==true) {
            g2.setColor(Color.RED);
        g2.drawRect(screenX+solidArea.x, screenY+solidArea.y, solidArea.width, solidArea.height);
        //this code shows player hitbox
        }
        }
    }

}
