package Tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.everythingManager;

public class BackgroundDrawer {
    everythingManager em;
    public int index=0;
    public BackgroundDrawer(everythingManager em) {
        this.em=em;
        backgroundLoader();
    }
    public void backgroundLoader() {
        try {
            em.backgrounds[0]=ImageIO.read(getClass().getResourceAsStream("/resources/bgs/skyBackground2.png"));
            em.backgrounds[2]=ImageIO.read(getClass().getResourceAsStream("/resources/bgs/Factory.png"));
            em.backgrounds[3]=ImageIO.read(getClass().getResourceAsStream("/resources/bgs/Level 3.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void drawBackground(BufferedImage background, Graphics2D g2) {
        g2.drawImage(background, 0, 0, em.maxScreenHoriz*em.resTileSize, em.maxScreenVert*em.resTileSize, null);
    }
}
