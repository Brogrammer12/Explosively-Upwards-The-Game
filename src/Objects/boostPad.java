package Objects;

import main.everythingManager;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
public class boostPad extends object{
everythingManager em;
public BufferedImage Boom;
public int Level;
    public boostPad(everythingManager em, String orientation, int worldx, int worldy, int Level) {
        super(em);
        this.em=em;
        worldX=worldx;
        worldY=worldy;
        direction=orientation;
        this.Level=Level;
        try {
            Boom=ImageIO.read(getClass().getResourceAsStream("/resources/bomb/explosionMove.png"));
            if (orientation.equals("Vert")) {
            //direction="up";
            image=ImageIO.read(getClass().getResourceAsStream("/resources/bomb/boostPadVert.png"));
        }
        else if (orientation.equals("Left")) {
            //direction="left";
            image=ImageIO.read(getClass().getResourceAsStream("/resources/bomb/boostPadLeft.png"));
        }
        else if (orientation.equals("Right")) {
            //direction="right";
            image=ImageIO.read(getClass().getResourceAsStream("/resources/bomb/boostPadRight.png"));
        }
        else if (orientation.equals("diagLeft")) {
           // direction="diagLeft";
            image=ImageIO.read(getClass().getResourceAsStream("/resources/bomb/boostPadDiagonalLeft.png"));
        }
        else if (orientation.equals("diagRight")) {
            //direction="diagRight";
            image=ImageIO.read(getClass().getResourceAsStream("/resources/bomb/boostPadDiagonalRight.png"));
        }
        }
        catch (IOException e) {
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
        if (playerArea.intersects(solidArea) && em.p1.worldY<worldY-playerArea.height+60) {
            if (timer==0) {
                //em.p1.currentlyColliding=false;
                em.playSE(2);
                if (em.p1.velocityY>0) {
                    em.p1.velocityY=0;
                }
            if (direction.equals("Vert")) {
                em.p1.velocityY-=15;
            }
            else if (direction.equals("Left")) {
                em.p1.velocityX+=15;
                em.stopXR=true;
            }
            else if (direction.equals("Right")) {
                em.p1.velocityX-=15;
                em.stopX=true;
            }
            else if (direction.equals("diagLeft")) {
                em.p1.velocityY-=15;
                em.p1.velocityX+=15;
                em.stopXR=true;
            }
            else if (direction.equals("diagRight")) {
                em.p1.velocityY-=15;
                em.p1.velocityX-=15;
                em.stopX=true;
            } 
            }
            timer++;
            g2.drawImage(Boom, screenX, screenY, em.resTileSize, em.resTileSize, null);
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
