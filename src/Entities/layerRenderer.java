package Entities;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.everythingManager;
public class layerRenderer {
    everythingManager em;
    BufferedImage[] hairstylesRight;
    BufferedImage[] hairstylesLeft;
    Point[] hairCoords;
    public layerRenderer(everythingManager em) {
        this.em=em;
        hairstylesRight=new BufferedImage[10];
        hairstylesLeft=new BufferedImage[10];
        hairCoords=new Point[37];
        hairstyles();
    }
    public void hairstyles() {
        try {
            hairstylesRight[0]=ImageIO.read(getClass().getResourceAsStream("/resources/hairstyles/ogHair.png"));
            hairstylesRight[1]=ImageIO.read(getClass().getResourceAsStream("/resources/hairstyles/bald.png"));
            hairstylesRight[2]=ImageIO.read(getClass().getResourceAsStream("/resources/hairstyles/bandana.png"));
            hairstylesRight[3]=ImageIO.read(getClass().getResourceAsStream("/resources/hairstyles/mohawk.png"));
            hairstylesRight[4]=ImageIO.read(getClass().getResourceAsStream("/resources/hairstyles/mohawk2.png"));
            hairstylesRight[5]=ImageIO.read(getClass().getResourceAsStream("/resources/hairstyles/afro.png"));
            hairstylesRight[6]=ImageIO.read(getClass().getResourceAsStream("/resources/hairstyles/ponytail.png"));
            hairstylesRight[7]=ImageIO.read(getClass().getResourceAsStream("/resources/hairstyles/slickHair.png"));
            hairstylesLeft[0]=ImageIO.read(getClass().getResourceAsStream("/resources/hairstyles/ogHairLeft.png"));
            hairstylesLeft[1]=ImageIO.read(getClass().getResourceAsStream("/resources/hairstyles/baldLeft.png"));
            hairstylesLeft[2]=ImageIO.read(getClass().getResourceAsStream("/resources/hairstyles/bandanaLeft.png"));
            hairstylesLeft[3]=ImageIO.read(getClass().getResourceAsStream("/resources/hairstyles/mohawkLeft.png"));
            hairstylesLeft[4]=ImageIO.read(getClass().getResourceAsStream("/resources/hairstyles/mohawk2Left.png"));
            hairstylesLeft[5]=ImageIO.read(getClass().getResourceAsStream("/resources/hairstyles/afroLeft.png"));
            hairstylesLeft[6]=ImageIO.read(getClass().getResourceAsStream("/resources/hairstyles/ponytailLeft.png"));
            hairstylesLeft[7]=ImageIO.read(getClass().getResourceAsStream("/resources/hairstyles/slickHairLeft.png"));
            hairCoords[0]=new Point(0, 0);
            hairCoords[1]=new Point(0, 5);
            hairCoords[2]=new Point(18, 0);
            hairCoords[3]=new Point(18, 5);
            hairCoords[4]=new Point(-18, 0);
            hairCoords[5]=new Point(-18, 5);
            hairCoords[6]=new Point(0, 15);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void renderPlayer(int x, int y, int hairIndex, Graphics2D g2, String direction, int animationNum) {
            //g2.drawImage(image, x, y, em.resTileSize, em.resTileSize, null);
            if (em.p1.aimDirection!="up" && em.p1.aimDirection!="down") {
                 if (direction.equals("left")) {
            g2.drawImage(hairstylesLeft[hairIndex], x+hairCoords[animationNum].x, y+hairCoords[animationNum].y, em.resTileSize*3, em.resTileSize*3, null);
            }
            else if(direction.equals("right")) {
                g2.drawImage(hairstylesRight[hairIndex], x+hairCoords[animationNum].x, y+hairCoords[animationNum].y, em.resTileSize*3, em.resTileSize*3, null);
            }
            }
    }
}
