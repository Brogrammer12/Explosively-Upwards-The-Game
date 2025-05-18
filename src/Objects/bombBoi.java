package Objects;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.everythingManager;

public class bombBoi extends object{
    everythingManager em;
    public boolean instantiateCoords=false;
    public int index;
    public bombBoi(everythingManager em, String direction, int index) {
        super(em);
        this.em=em;
        this.index=index;
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
            if (em.p1.Move==true) {
                Move=true;
                 image=ImageIO.read(getClass().getResourceAsStream("/resources/bomb/BombMove.png"));
            altImage=ImageIO.read(getClass().getResourceAsStream("/resources/bomb/bombPlantedMove.png"));
            boom=ImageIO.read(getClass().getResourceAsStream("/resources/bomb/explosionMove.png"));
            }
            else {
                Move=false;
                image=ImageIO.read(getClass().getResourceAsStream("/resources/bomb/Bomb.png"));
            altImage=ImageIO.read(getClass().getResourceAsStream("/resources/bomb/bombPlanted.png"));
            boom=ImageIO.read(getClass().getResourceAsStream("/resources/bomb/explosion.png"));
            }
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
            if (sideCol==true) {
                try {
                    if (Move==true) {
                    switch (direction) {
                        case "left":
                        altImage=ImageIO.read(getClass().getResourceAsStream("/resources/bomb/bombPlantedMoveLeft.png"));
                        break;
                        case "right":
                        altImage=ImageIO.read(getClass().getResourceAsStream("/resources/bomb/bombPlantedMoveRight.png"));
                    }
                }
                else {
                    switch (direction) {
                        case "left":
                        altImage=ImageIO.read(getClass().getResourceAsStream("/resources/bomb/bombPlantedLeft.png"));
                        break;
                        case "right":
                        altImage=ImageIO.read(getClass().getResourceAsStream("/resources/bomb/bombPlantedRight.png"));
                        break;
                    }
                }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            image=altImage;
        }
        if (explode==true) {
                    image=boom;
                    if (timer==0 && Move==true) {
                            if (em.boomTotal+1>=2) {
                                em.p1.velocityY=-20;
                            }
                            else {
                                em.p1.velocityY-=15;
                                em.boomTotal++;
                            }
                    }
                    timer++;
                    if (timer==25) {
                        em.objBomb[index]=null;
                        timer=0;
                        explode=false;
                    }
        }
        screenX=(int) (worldX-em.p1.worldX+em.p1.screenX);
        screenY=(int) (worldY-em.p1.worldY+em.p1.screenY);
        g2.drawImage(image, screenX, screenY, em.resTileSize, em.resTileSize, null);
    }

}
