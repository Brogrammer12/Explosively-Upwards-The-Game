package Entities;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;

import main.everythingManager;

public class Player extends Entity{
    public int screenX;
    public int screenY;
    public boolean bombGoing=false;
    public boolean leftOrRight=false;
    public boolean idle=true;
    public boolean Move=false;
    public boolean event=false;
    public boolean boom=false;
    public boolean spriteReset=false;
    public int bombsLeft=10;
    public int bombsLeftMove=10;
    public int timer=0;
    public int health=3;
    public int Level=1;
    public float alpha=1.0f;
    public boolean warmup=false;
    public Font fontPixeboy;
    public BufferedImage left1,leftIdle2, leftIdle3, right1, rightIdle2, rightIdle3, 
    bomb, bombPlanted, rWalk1, rWalk2, rWalk3, rWalk4, lWalk1, lWalk2, lWalk3, lWalk4,
     crouchLeft, crouchRight, jumpRight, jumpLeft, pRightBoom1, pRightBoom2, pLeftBoom1,
      pLeftBoom2;
    public Player(everythingManager em) {
        super(em);
        screenX=em.screenWidth/2-(em.resTileSize*3)/2;
        screenY=em.screenHeight/2-(em.resTileSize*3)/2;
        solidArea=new Rectangle(10, 0, em.resTileSize*2+10, em.resTileSize*3);
        defaultSolidArea.x=20;
        defaultSolidArea.y=0;
        worldX=300;
        worldY=(em.maxWorldVert*em.resTileSize)-500;
        bombX=(int) (worldX+(em.resTileSize*3)/2+40);
        bombY=(int) (worldY+(em.resTileSize*3)/2+40);
        moveSpeed=4;
        Health=new BufferedImage[11];
        MoveHealth=new BufferedImage[11];
        try {
            fontPixeboy=Font.createFont(Font.TRUETYPE_FONT, new File("src/Fonts/PixelifySans-VariableFont_wght.ttf"));
            fontPixeboy=fontPixeboy.deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fontPixeboy);

        }
        catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
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
            pRightBoom1=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerRightBoom1.png"));
            pRightBoom2=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerRightBoom2.png"));
            pLeftBoom1=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerLeftBoom1.png"));
            pLeftBoom2=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerLeftBoom2.png"));
            Health[10]=ImageIO.read(getClass().getResourceAsStream("/resources/Health/BombsLeft10.png"));
            Health[9]=ImageIO.read(getClass().getResourceAsStream("/resources/Health/BombsLeft9.png"));
            Health[8]=ImageIO.read(getClass().getResourceAsStream("/resources/Health/BombsLeft8.png"));
            Health[7]=ImageIO.read(getClass().getResourceAsStream("/resources/Health/BombsLeft7.png"));
            Health[6]=ImageIO.read(getClass().getResourceAsStream("/resources/Health/BombsLeft6.png"));
            Health[5]=ImageIO.read(getClass().getResourceAsStream("/resources/Health/BombsLeft5.png"));
            Health[4]=ImageIO.read(getClass().getResourceAsStream("/resources/Health/BombsLeft4.png"));
            Health[3]=ImageIO.read(getClass().getResourceAsStream("/resources/Health/BombsLeft3.png"));
            Health[2]=ImageIO.read(getClass().getResourceAsStream("/resources/Health/BombsLeft2.png"));
            Health[1]=ImageIO.read(getClass().getResourceAsStream("/resources/Health/BombsLeft1.png"));
            Health[0]=ImageIO.read(getClass().getResourceAsStream("/resources/Health/BombsLeft0.png"));
            MoveHealth[10]=ImageIO.read(getClass().getResourceAsStream("/resources/Health/BombsLeftMove10.png"));
            MoveHealth[9]=ImageIO.read(getClass().getResourceAsStream("/resources/Health/BombsLeftMove9.png"));
            MoveHealth[8]=ImageIO.read(getClass().getResourceAsStream("/resources/Health/BombsLeftMove8.png"));
            MoveHealth[7]=ImageIO.read(getClass().getResourceAsStream("/resources/Health/BombsLeftMove7.png"));
            MoveHealth[6]=ImageIO.read(getClass().getResourceAsStream("/resources/Health/BombsLeftMove6.png"));
            MoveHealth[5]=ImageIO.read(getClass().getResourceAsStream("/resources/Health/BombsLeftMove5.png"));
            MoveHealth[4]=ImageIO.read(getClass().getResourceAsStream("/resources/Health/BombsLeftMove4.png"));
            MoveHealth[3]=ImageIO.read(getClass().getResourceAsStream("/resources/Health/BombsLeftMove3.png"));
            MoveHealth[2]=ImageIO.read(getClass().getResourceAsStream("/resources/Health/BombsLeftMove2.png"));
            MoveHealth[1]=ImageIO.read(getClass().getResourceAsStream("/resources/Health/BombsLeftMove1.png"));
            MoveHealth[0]=ImageIO.read(getClass().getResourceAsStream("/resources/Health/BombsLeftMove0.png"));
            bombHealth=ImageIO.read(getClass().getResourceAsStream("/resources/Health/bombPlantedHealth.png"));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void update() {
        System.out.println(em.k.sUpPressed);
        if (em.stopX==true) {
            if (velocityX>=0) {
                velocityX=0;
                em.stopX=false;
            }
        }
        if (em.stopXR==true) {
            if (velocityX<=0) {
                velocityX=0;
                em.stopXR=false;
            }
        }
        if (grounded==true) {
            XGravity=0.3f;
        }
        else {
            XGravity=0.2f;
        }
        if (em.k.sRightPressed==false) {
            if (worldY>=em.maxWorldVert*em.resTileSize+3*em.resTileSize) {
            worldX=300;
        worldY=(em.maxWorldVert*em.resTileSize)-500;
        velocityY=0;
        health--;
        }
        if (em.k.rPressed==true && em.k.hasPressed==false) {
            if (Move==false) {
                Move=true;
            }
            else if(Move==true) {
                Move=false;
            }
            em.k.hasPressed=true;
        }
        if (em.k.leftPressed==true) {
            if (em.m.mouseMode==false) {
                direction="left";
            }
            idle=false;
        }
         if(em.k.rightPressed==true) {
            if (em.m.mouseMode==false) {
                direction="right";
            }
            idle=false;
        }
        if (em.k.upPressed==true) {
            idle=false;
        }
        if (em.k.ePressed==true && em.k.hasPressed==false) {
            event=true;
            boom=true;
            em.k.hasPressed=true;
        }
        if (em.k.rightPressed==false && em.k.leftPressed==false && em.k.downPressed==false && em.k.upPressed==false && em.k.ePressed==false && em.k.hasPressed==false) {
            idle=true;
        }
        collisionOn=false;
        falling=false;
        em.cChecker.checkPlayer(this);
        em.cChecker.checkEntity(this, em.meleeroman);
        switch (direction) {
            case "left":
            if (em.k.upPressed==true && grounded==true && em.k.sRightPressed==false && em.k.sUpPressed==false) {
                    velocityY-=10;
                }
            break;
            case "right":
                if (em.k.upPressed==true && grounded==true && em.k.sRightPressed==false && em.k.sUpPressed==false) {
                    velocityY-=10;
                }
            break;
        }
        }
        if (collisionOn==false) {
            switch (direction) {
                case "left":
                    if (em.k.downPressed==false) {
                        if (em.k.leftPressed==true && em.k.sRightPressed==false && em.k.sUpPressed==false) {
                            worldX-=moveSpeed;
                        }
                    }
                
                break;
                case "right":
                    if (em.k.downPressed==false) {
                        if (em.k.rightPressed==true && em.k.sRightPressed==false && em.k.sUpPressed==false) {
                            worldX+=moveSpeed;
                        }
                    }
                
                break;
            }
            if (em.m.mouseMode==true) {
                if (em.k.downPressed==false && direction!="left") {
                        if (em.k.leftPressed==true && em.k.sRightPressed==false && em.k.sUpPressed==false) {
                            worldX-=moveSpeed;
                        }
                    }
                    if (em.k.downPressed==false && direction!="right") {
                        if (em.k.rightPressed==true && em.k.sRightPressed==false && em.k.sUpPressed==false) {
                            worldX+=moveSpeed;
                        }
                    }
            }
        }
         if (falling==true) {
           worldY+=moveSpeed;
        }
        spriteCounter++;
        if (idle==true && event==false) {
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
        else if(idle==false && event==false) {
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
        else if(event==true) {
             if (spriteCounter>=25) {
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
         if (warmup==false) {
            Graphics2D g2d=g2; // Dummy execution to trigger JIT optimization
    g2d.setFont(fontPixeboy);
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    g2d.setColor(Color.MAGENTA);
    g2d.drawString("Warming Up...", 670, 120);
warmup=true;
        }
        image=null;
        if (event==true) {
            if (boom==true) {
                if (spriteReset==false) {
                    SpriteNum=0;
                    spriteCounter=0;
                    spriteReset=true;
                }
                switch (direction) {
                    case "left":
                    if (SpriteNum==0) {
                        image=pLeftBoom1;
                    }
                    else if(SpriteNum==1) {
                        image=pLeftBoom2;
                    }
                    else if(SpriteNum==2) {
                        image=left1;
                        event=false;
                        boom=false;
                        SpriteNum=0;
                        spriteReset=false;
                         for (int i=0; i<em.objBomb.length; i++) {
                            if (em.objBomb[i]!=null) {
                                em.objBomb[i].explode=true;
                            }
                        }
                        bombsLeftMove=10;
                        bombsLeft=10;
                        em.boomTotal=0;
                    }
                    break;
                    case "right":
                    if (SpriteNum==0) {
                        image=pRightBoom1;
                    }
                    else if(SpriteNum==1) {
                        image=pRightBoom2;
                    }
                    else if(SpriteNum==2) {
                        image=right1;
                        event=false;
                        boom=false;
                        SpriteNum=0;
                        spriteReset=false;
                        for (int i=0; i<em.objBomb.length; i++) {
                            if (em.objBomb[i]!=null) {
                                em.objBomb[i].explode=true;
                            }
                        }
                        bombsLeftMove=10;
                        bombsLeft=10;
                        em.boomTotal=0;
                    }
                    break;
                }
            }
        }
        else {
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
        }
        
       if (em.k.sUpPressed==true) {
            image=jumpRight;
        }
        //g2.setColor(Color.WHITE);
        //g2.fillRect(worldX, worldY, em.resTileSize, em.resTileSize);
        g2.drawImage(image, screenX, screenY, em.resTileSize*3, em.resTileSize*3, null);
        if (bombsLeft==11) {
            bombsLeft=10;
        }
        if (bombsLeftMove==11) {
            bombsLeftMove=10;
        }
        if (Move==true) {
            g2.drawImage(MoveHealth[bombsLeftMove], em.resTileSize*15, 50, 64*3, em.resTileSize, null);
        }
        else {
            
                g2.drawImage(Health[bombsLeft], em.resTileSize*15, 50, 64*3, em.resTileSize, null);
        }
        if (health==3) {
            g2.drawImage(bombHealth, 50, 40, em.resTileSize, em.resTileSize, null);
        g2.drawImage(bombHealth, 50+em.resTileSize, 40, em.resTileSize, em.resTileSize, null);
        g2.drawImage(bombHealth, 50+em.resTileSize*2, 40, em.resTileSize, em.resTileSize, null);
        }
        else if(health==2) {
            g2.drawImage(bombHealth, 50, 40, em.resTileSize, em.resTileSize, null);
        g2.drawImage(bombHealth, 50+em.resTileSize, 40, em.resTileSize, em.resTileSize, null);
        }
        else if(health==1) {
            g2.drawImage(bombHealth, 50, 40, em.resTileSize, em.resTileSize, null);
        }
        if (em.showHitboxes==true) {
            g2.setColor(Color.RED);
        g2.drawRect(screenX+solidArea.x, screenY+solidArea.y, solidArea.width, solidArea.height);
        //this code shows player hitbox
        }
    if (em.m.rightClicked==true && Move==true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2.setFont(fontPixeboy);
            g2.setColor(Color.MAGENTA);
            g2.drawString("Subspace Grenade", 670, 120);
            alpha-=0.02f;
            if (alpha<0) {
                alpha=1.0f;
                em.m.rightClicked=false;
            }
        }
         else if(em.m.rightClicked==true && Move==false) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2.setFont(fontPixeboy);
            g2.setColor(Color.RED);
            g2.drawString("Explosive Grenade", 670, 120);
            alpha-=0.02f;
            if (alpha<0) {
                alpha=1.0f;
                em.m.rightClicked=false;
            }
        }
    }
}
