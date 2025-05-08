package Entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.everythingManager;

public class Player extends Entity{
    public int screenX;
    public int screenY;
    everythingManager em;
    public boolean bombGoing=false;
    public boolean leftOrRight=false;
    public boolean idle=true;
    public Player(everythingManager em) {
        screenX=em.screenWidth/2-(em.resTileSize*3)/2;
        screenY=em.screenHeight/2-(em.resTileSize*3)/2;
        this.em=em;
        solidArea=new Rectangle(40, 20, 70, 124);
        worldX=300;
        worldY=50;
        bombX=(int) (worldX+(em.resTileSize*3)/2+40);
        bombY=(int) (worldY+(em.resTileSize*3)/2+40);
        moveSpeed=4;
        imageLoader();
    }
    public void imageLoader() {
        try {
            left1=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerLeft1.png"));
            leftIdle2=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerLeftIdle2.png"));
            leftIdle3=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerLeftIdle3.png"));
            right1=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerRight1.png"));
            rightIdle2=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerRightIdle2.png"));
            rightIdle3=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerRightIdle3.png"));
            //bomb=ImageIO.read(getClass().getResourceAsStream("/resources/Player/Bomb.png"));
            //bombPlanted=ImageIO.read(getClass().getResourceAsStream("/resources/Player/bombPlanted.png"));
            lWalk1=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerLeftWalking1.png"));
            lWalk2=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerLeftWalking2.png"));
            lWalk3=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerLeftWalking3.png"));
            lWalk4=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerLeftWalking4.png"));
            rWalk1=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerRightWalking1.png"));
            rWalk2=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerRightWalking2.png"));
            rWalk3=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerRightWalking3.png"));
            rWalk4=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerRightWalking4.png"));
            crouchRight=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerCrouchRight.png"));
            crouchLeft=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerCrouchLeft.png"));
            jumpLeft=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerLeftJump.png"));
            jumpRight=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerRightJump.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void update() {
        if (em.k.leftPressed==true) {
            direction="left";
            idle=false;
        }
         if(em.k.rightPressed==true) {
            direction="right";
            idle=false;
        }
        if (em.k.upPressed==true) {
            idle=false;
        }
        if (em.k.rightPressed==false && em.k.leftPressed==false && em.k.downPressed==false && em.k.upPressed==false) {
            idle=true;
        }
        collisionOn=false;
        falling=false;
        em.cChecker.checkPlayer(this);
        if (collisionOn==false) {
            switch (direction) {
                case "left":
                if (em.k.upPressed==true && grounded==true) {
                    velocityY-=10;
                }
                    if (em.k.downPressed==false) {
                        if (em.k.leftPressed==true) {
                            worldX-=moveSpeed;
                        }
                    }
                
                break;
                case "right":
                if (em.k.upPressed==true && grounded==true) {
                    velocityY-=10;
                }
                    if (em.k.downPressed==false) {
                        if (em.k.rightPressed==true) {
                            worldX+=moveSpeed;
                        }
                    }
                
                break;
            }
        }
         if (falling==true) {
           worldY+=moveSpeed;
        }
        spriteCounter++;
        if (idle==true) {
            if (SpriteNum==3) {
                SpriteNum=0;
            }
            if (spriteCounter>=25) {
                if (idleBack==false && SpriteNum!=2) {
                     SpriteNum++;
                }
                else if (SpriteNum==2) {
                 idleBack=true;
                }
                if (idleBack==true && SpriteNum!=0) {
                 SpriteNum--;
                }
                if (SpriteNum==0 && idleBack==true) {
                 idleBack=false;
                 //SpriteNum++;
                }
                spriteCounter=0;
             }
        }
        else if(idle==false) {
            if (spriteCounter>=12) {
                if (SpriteNum==3) {
                    SpriteNum=0;
                }
                else {
                    SpriteNum++;
                }
                spriteCounter=0;
             }
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image=null;
            switch (direction) {
                case "left":
                if (em.k.downPressed==true) {
                    image=crouchLeft;
                }
                else if(em.k.upPressed==true || falling==true) {
                    image=jumpLeft;
                }
                else {
                    if (idle==true) {
                        if (SpriteNum==0) {
                            image=left1;
                    }
                    else if(SpriteNum==1) {
                            image=leftIdle2;
                    }
                    else if(SpriteNum==2) {
                            image=leftIdle3;
                    }
                    }
                    else if(idle==false) {
                        if (SpriteNum==0) {
                        image=lWalk1;
                    }
                    else if(SpriteNum==1) {
                            image=lWalk2;
                    }
                    else if(SpriteNum==2) {
                            image=lWalk3;
                    }
                    else if(SpriteNum==3) {
                        image=lWalk4;
                    }
                    }
                }
                break;
                case "right":
                if (em.k.downPressed==true) {
                    image=crouchRight;
                }
                else if(em.k.upPressed==true || falling==true) {
                    image=jumpRight;
                }
                else {
                    if (idle==true) {
                        if (SpriteNum==0) {
                            image=right1;
                    }
                    else if(SpriteNum==1) {
                            image=rightIdle2;
                    }
                    else if(SpriteNum==2) {
                            image=rightIdle3;
                    }
                    }
                    else if(idle==false) {
                        if (SpriteNum==0) {
                            image=rWalk1;
                    }
                    else if(SpriteNum==1) {
                            image=rWalk2;
                    }
                    else if(SpriteNum==2) {
                            image=rWalk3;
                    }
                    else if(SpriteNum==3) {
                        image=rWalk4;
                    }
                    }
                }
                break;
            }
        
       
        //g2.setColor(Color.WHITE);
        //g2.fillRect(worldX, worldY, em.resTileSize, em.resTileSize);
        g2.drawImage(image, screenX, screenY, em.resTileSize*3, em.resTileSize*3, null);
        //g2.setColor(Color.RED);
       // g2.drawRect(screenX+solidArea.x, screenY+solidArea.y, solidArea.width, solidArea.height);
       /*  if (em.k.enterPressed==true) {
            bombGoing=true;
            if (direction=="right") {
                leftOrRight=true;
            }
            else if(direction=="left") {
                leftOrRight=false;
            }
        }
        if (bombGoing==true) {
            g2.drawImage(bomb, bombX, bombY, em.resTileSize, em.resTileSize, null);
            if (bombX>=em.maxScreenHoriz*em.resTileSize || bombX<=-em.resTileSize) {
                bombGoing=false;
            }
            if (leftOrRight==true) {
                bombX+=5;
            }
            else if(leftOrRight==false) {
                bombX-=5;
            }
        }
        else if(em.k.enterPressed==false) {
            bombX=worldX+(em.resTileSize*3)/2+40;
            bombY=worldY+(em.resTileSize*3)/2-30;
        }*/
    }
}
