package Objects;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.everythingManager;
public class object {
public int worldX;
public int worldY;
public int screenX;
public int screenY;
public int Level;
public BufferedImage image;
public BufferedImage altImage;
public BufferedImage boom;
public String direction;
public int moveSpeed=5;
public boolean bombTriggered=false;
public boolean explode=false;
public int timer=0;
public boolean Move;
public boolean sideCol=false;
everythingManager em;
public object(everythingManager em) {
    this.em=em;
}
public Rectangle solidArea=new Rectangle(0, 0, 48, 48);
public void objFunction(Graphics2D g2) {}
}
