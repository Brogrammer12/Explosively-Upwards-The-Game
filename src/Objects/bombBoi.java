package Objects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.everythingManager;

public class bombBoi extends object{
    everythingManager em;
    public boolean instantiateCoords=false;
    public int index;
    int x1;
        int y1;
        int x2;
        int y2;
        int ry1;
        int rx1;
        double slope;
        double intercept;
    double pushDistance;
    double directionMultiplier;
    double newX;
    double newY;
    int worldx;
    int worldy;
    boolean moveDone=false;
    public boolean playerBoomed=false;
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
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            screenX=(int) (worldX-em.p1.worldX+em.p1.screenX);
        screenY=(int) (worldY-em.p1.worldY+em.p1.screenY);
        x1=screenX+em.resTileSize/2;
         y1=screenY+em.resTileSize/2;
        if (explode==false) {
         x2=em.p1.screenX+(em.resTileSize*3)/2;
         y2=em.p1.screenY+(em.resTileSize*3)/2;
         slope=(y2-y1)/(x2-x1);
         intercept=y1-(slope*x1);
     pushDistance = 200; 
    if (x2>x1) {
        directionMultiplier=1;
    }
    else {
        directionMultiplier=-1;
    }
     newX = x2 + directionMultiplier * pushDistance;
     newY = (slope * newX) + intercept;
     if (Math.abs(em.p1.screenY-newY)>=8*em.resTileSize) {
        newY=-6*em.resTileSize;
     }
        }
        else {
             if (em.k.rightPressed==true && em.p1.collisionOn==false) {
                newX-=em.p1.moveSpeed;
            }
            if (em.k.leftPressed==true && em.p1.collisionOn==false) {
                newX+=em.p1.moveSpeed;
            }
            if (em.p1.velocityY<0) {
                //newY+=em.p1.gravity;
                newY-=em.p1.velocityY;
            }
            if (em.p1.velocityY>0) {
                newY-=em.p1.velocityY;
            }
        }
        if (em.showBoomLine==true) {
            g2.setColor(Color.BLACK);
            g2.drawLine(x1, y1, (int) (newX), (int) (newY));
        }
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
                    if (timer==1 && Move==true) {
                        worldx=(int) (newX+em.p1.worldX-em.p1.screenX);  
                        worldy=(int) (newY+em.p1.worldY-em.p1.screenY);
                        ry1=y1;
                        rx1=x1;
                        //em.p1.worldX=worldx-2*em.resTileSize;
                        //em.p1.worldY=worldy-3*em.resTileSize;
                            /*if (em.boomTotal+1>=2) {
                                em.p1.velocityY=-20;
                            }
                            else {
                                em.p1.velocityY-=15;
                                em.boomTotal++;
                            }*/
                    }
                    if (Move==true) {
                        int differenceX=Math.abs(em.p1.screenX-screenX);
                        int differenceY=Math.abs(em.p1.screenY-screenY);
                      //  if (differenceX<=100 && differenceY<=100) {
                             if (slope>0 && moveDone==false) {
                          if (em.p1.worldX>worldx) {
                            em.p1.worldX-=10;
                            //worldx+=10;
                            em.disableGravity=true;
                          }
                          else {
                            //em.disableGravity=false;
                            //em.p1.velocityX-=20;
                          }
                          if (em.p1.worldY>worldy) {
                            em.p1.worldY-=10;
                            //worldy+=10;
                            em.disableGravity=true;
                          }
                          else {
                            em.stopX=true;
                            em.p1.velocityY-=10;
                            em.p1.velocityX-=10;
                            em.disableGravity=false;
                            moveDone=true;
                          }      //if line goes top left bottom right
                    }
                    else if (moveDone==false && slope!=0) {
                             if (em.p1.worldX<worldx) {
                            em.p1.worldX+=10;
                            //worldx-=10;
                            em.disableGravity=true;
                          }
                          else {
                            //em.disableGravity=false;
                            //em.p1.velocityX-=20;
                          }
                          if (em.p1.worldY>worldy) {
                            em.p1.worldY-=10;
                           // worldy+=10;
                            em.disableGravity=true;
                          }
                          else {
                            em.stopXR=true;
                            em.p1.velocityY-=10;
                            em.p1.velocityX+=10;
                            em.disableGravity=false;
                            moveDone=true;
                          }  //if line goes bottom left top right
                    }
                    else if (slope==0 && moveDone==false) {
                        if (em.p1.screenX>screenX) {
                            em.stopXR=true;
                            em.p1.velocityX+=10;
                            moveDone=true;
                        }
                        else {
                           em.stopX=true;
                            em.p1.velocityX-=10;
                            moveDone=true;
                        }
                    }
                      //  }
                    }
                    if ((moveDone==true && Move==true) || timer<=1) {
                        timer++;
                    }
                    else if(Move==false) {
                        timer++;
                    }
                    if (timer==25) {
                        em.objBomb[index]=null;
                        moveDone=false;
                        timer=0;
                        explode=false;
                    }
        }
        g2.drawImage(image, screenX, screenY, em.resTileSize, em.resTileSize, null);
    }

}
