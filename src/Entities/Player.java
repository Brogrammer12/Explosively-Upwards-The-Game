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
    public int animationNum;
    public boolean aim=false;
    public boolean stickNades=false;
    public String aimDirection="";
    public boolean bombGoing=false;
    public boolean leftOrRight=false;
    public boolean idle=true;
    public boolean above=false;
    public boolean below=false;
    //public boolean Move=false;
    public boolean boom=false;
    public boolean spriteReset=false;
    public int bombsLeft=10;
    public int bombsLeftMove=10;
    public int timer=0;
    public int health=5;
    public int Level=1;
    public int menuLevel=1;
    public int menuTimer=0;
    public boolean startMenuTimer=false;
    public float alpha=1.0f;
    public boolean warmup=false;
    public Font fontPixeboy;
    public String bombType="explosive";
    public BufferedImage left1,leftIdle2, leftIdle3, right1, rightIdle2, rightIdle3, 
    bomb, bombPlanted, rWalk1, rWalk2, rWalk3, rWalk4, lWalk1, lWalk2, lWalk3, lWalk4,
     crouchLeft, crouchRight, jumpRight, jumpLeft, pRightBoom1, pRightBoom2, pLeftBoom1,
      pLeftBoom2, lRun1, lRun2, lRun3, lRun4, rRun1, rRun2, rRun3, rRun4, diagRightDown, diagLeftDown, diagRightUp, diagLeftUp, leftUpShoot, rightUpShoot,
      leftDownShoot, rightDownShoot;
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
            bomb=ImageIO.read(getClass().getResourceAsStream("/resources/bomb/Bomb.png"));
            bombPlanted=ImageIO.read(getClass().getResourceAsStream("/resources/bomb/explosion.png"));
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
            rRun1=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerRunRight2.png"));
            rRun2=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerRunRight3.png"));
            rRun3=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerRunRight4.png"));
            rRun4=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerRunRight5.png"));
            lRun1=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerRunLeft2.png"));
            lRun2=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerRunLeft3.png"));
            lRun3=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerRunLeft4.png"));
            lRun4=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerRunLeft5.png"));
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
            diagLeftDown=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerLeftDiagonalDown.png"));
            diagLeftUp=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerLeftDiagonalUp.png"));
            diagRightDown=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerRightDiagonalDown.png"));
            diagRightUp=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerRightDiagonalUp.png"));
            leftUpShoot=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerLeftUp.png"));
            leftDownShoot=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerDownShootLeft.png"));
            rightUpShoot=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerRightUp.png"));
            rightDownShoot=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerDownShoot.png"));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public void update() {
        //System.out.println(worldY);
        if (em.k.escPressed==true && em.k.hasPressed==false) {
            if (em.paused==true) {
                em.paused=false;
            }
            else {
                em.paused=true;
            }
            em.k.hasPressed=true;
        }
        if (em.paused==false) {
            handleXVelocity();
            handleMovement();
            handleSpriteCounting();
        }
        else {
            if (em.k.leftPressed==true && em.k.menuHasPressed==false) {
                em.k.menuHasPressed=true;
            }
            else if(em.k.rightPressed==true && em.k.menuHasPressed==false) {
                em.k.menuHasPressed=true;
            }
        }
    }




    public void handleXVelocity() {
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
    }




    public void handleMovement() {
        if (disableGravity==false) {
            if (worldY>=em.maxWorldVert*em.resTileSize+3*em.resTileSize) {
            worldX=300;
        worldY=(em.maxWorldVert*em.resTileSize)-500;
        velocityY=0;
        health--;
        }
        if (em.k.rPressed==true && em.k.hasPressed==false) {
            if (stickNades==false) {
                stickNades=true;
                //Move=true;
            }
            else if(stickNades==true) {
                stickNades=false;
                //Move=false;
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
            boom=true;
            em.k.hasPressed=true;
        }
        if (em.k.rightPressed==false && em.k.leftPressed==false && em.k.downPressed==false && em.k.upPressed==false && em.k.ePressed==false && em.k.hasPressed==false) {
            idle=true;
        }
        collisionOn=false;
        falling=false;
        em.cChecker.checkPlayer(this);
        em.cChecker.checkEntity(this, em.npc);
        switch (direction) {
            case "left":
            if (em.k.upPressed==true && grounded==true && disableGravity==false && aimDirection==null) {
                    velocityY-=12;
                    //currentlyColliding=false;
                    em.playSE(3);
                }
            break;
            case "right":
                if (em.k.upPressed==true && grounded==true && disableGravity==false && aimDirection==null) {
                    velocityY-=12;
                    //currentlyColliding=false;
                    em.playSE(3);
                }
            break;
        }
        }
        if (collisionOn==false) {
            switch (direction) {
                case "left":
                    if (em.k.downPressed==false) {
                        if (em.k.leftPressed==true && disableGravity==false && aimDirection==null) {
                            worldX-=moveSpeed;
                        }
                    }
                
                break;
                case "right":
                    if (em.k.downPressed==false) {
                        if (em.k.rightPressed==true && disableGravity==false && aimDirection==null) {
                            worldX+=moveSpeed;
                        }
                    }
                
                break;
            }
            if (em.m.mouseMode==true) {
                if (em.k.downPressed==false && direction!="left") {
                        if (em.k.leftPressed==true && disableGravity==false && aimDirection==null) {
                            worldX-=moveSpeed;
                        }
                    }
                    if (em.k.downPressed==false && direction!="right") {
                        if (em.k.rightPressed==true && disableGravity==false && disableGravity==false && aimDirection==null) {
                            worldX+=moveSpeed;
                        }
                    }
            }
        }
         if (falling==true) {
           worldY+=moveSpeed;
        }
    }




    public void handleSpriteCounting() {
         spriteCounter++;
        if (idle==true && boom==false) {
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
        else if(idle==false && boom==false) {
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
        else if(boom==true) {
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




    public void warmupTextDrawer(Graphics2D g2) {
        if (warmup==false) {
            Graphics2D g2d=g2; // Dummy execution to trigger JIT optimization
    g2d.setFont(fontPixeboy);
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    g2d.setColor(Color.MAGENTA);
    g2d.drawString("Warming Up...", 670, 120);
warmup=true;
        }
    }




    public void handleDetonateAnim() {
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




    public void handleMovementDraw() {
        if (boom==false) {
             switch (direction) {
                case "left":
                if (em.k.downPressed==true && em.paused==false) {
                        image=crouchLeft;
                        animationNum=6;
                    
                }
                else if((em.k.upPressed==true || falling==true) && em.paused==false) {
                        image=jumpLeft;
                         animationNum=0;
                    
                }
                else {
                    if (idle==true) {
                        if (SpriteNum==0) {
                            image=left1;
                           animationNum=0;
                    }
                    else if(SpriteNum==1) {
                            image=leftIdle2;
                            animationNum=1;
                    }
                    else if(SpriteNum==2) {
                            image=leftIdle3;
                            animationNum=1;
                    }
                    }
                    else if(idle==false) {
                        if (em.k.shiftPressed==true) {
                            moveSpeed=6;
                            if (SpriteNum==0) {
                        image=lRun1;
                        animationNum=4;
                    }
                    else if(SpriteNum==1) {
                            image=lRun2;
                            animationNum=4;
                    }
                    else if(SpriteNum==2) {
                            image=lRun3;
                            animationNum=4;
                    }
                    else if(SpriteNum==3) {
                        image=lRun4;
                        animationNum=5;
                    }
                        }
                        else {
                            moveSpeed=4;
                            if (SpriteNum==0) {
                        image=lWalk1;
                        animationNum=0;
                    }
                    else if(SpriteNum==1) {
                            image=lWalk2;
                             animationNum=0;
                    }
                    else if(SpriteNum==2) {
                            image=lWalk3;
                             animationNum=0;
                    }
                    else if(SpriteNum==3) {
                        image=lWalk4;
                         animationNum=0;
                    }
                        }
                    }
                }
                break;
                case "right":
                if (em.k.downPressed==true && em.paused==false) {
                        image=crouchRight;
                        animationNum=6;
                    
                }
                else if((em.k.upPressed==true || falling==true) && em.paused==false) {
                        image=jumpRight;
                         animationNum=0;
                    
                }
                else {
                    if (idle==true) {
                        if (SpriteNum==0) {
                            image=right1;
                            animationNum=0;
                    }
                    else if(SpriteNum==1) {
                            image=rightIdle2;
                            animationNum=1;
                    }
                    else if(SpriteNum==2) {
                            image=rightIdle3;
                            animationNum=1;
                    }
                    }
                    else if(idle==false) {
                        if (em.k.shiftPressed==true) {
                            moveSpeed=6;
                            if (SpriteNum==0) {
                            image=rRun1;
                            animationNum=2;
                    }
                    else if(SpriteNum==1) {
                            image=rRun2;
                            animationNum=2;
                    }
                    else if(SpriteNum==2) {
                            image=rRun3;
                            animationNum=2;
                    }
                    else if(SpriteNum==3) {
                        image=rRun4;
                        animationNum=3;
                    }
                        }
                        else {
                            moveSpeed=4;
                            if (SpriteNum==0) {
                            image=rWalk1;
                             animationNum=0;
                    }
                    else if(SpriteNum==1) {
                            image=rWalk2;
                             animationNum=0;
                    }
                    else if(SpriteNum==2) {
                            image=rWalk3;
                             animationNum=0;
                    }
                    else if(SpriteNum==3) {
                        image=rWalk4;
                         animationNum=0;
                    }
                        }
                    }
                }
                break;
            }
        }
    }




    public void handleAimDirection() {
         if (em.m.mouseY<em.screenHeight/2 && em.m.mouseX>screenX && em.m.mouseX<screenX+em.resTileSize*3 && em.m.rightClicked==true) {
            aimDirection="up";
            switch (direction) {
                case "left":
                    image=leftUpShoot;
                    break;
                case "right":
                image=rightUpShoot;
                    break;
            }
        }
         else if (em.m.mouseY>em.screenHeight/2 && em.m.mouseX>screenX && em.m.mouseX<screenX+em.resTileSize*3 && em.m.rightClicked==true) {
            aimDirection="down";
            switch (direction) {
                case "left":
                    image=leftDownShoot;
                    break;
                case "right":
                image=rightDownShoot;
                    break;
            }
        }
        else if (em.m.mouseX>em.screenWidth/2 && em.m.mouseY>em.screenHeight/2 && em.m.rightClicked==true) {
                image=diagRightDown;
                aimDirection="diagRightDown";
            }
            else if (em.m.mouseX>em.screenWidth/2 && em.m.mouseY<em.screenHeight/2 && em.m.rightClicked==true) {
                image=diagRightUp;
                aimDirection="diagRightUp";
            }
            else if (em.m.mouseX<em.screenWidth/2 && em.m.mouseY<em.screenHeight/2 && em.m.rightClicked==true) {
                image=diagLeftUp;
                aimDirection="diagLeftUp";
            }
            else if (em.m.mouseX<em.screenWidth/2 && em.m.mouseY>em.screenHeight/2 && em.m.rightClicked==true) {
                image=diagLeftDown;
                aimDirection="diagLeftDown";
            }
            else if(em.m.rightClicked==false) {
                aimDirection=null;
            }
        
       if (disableGravity==true) {
        if (direction=="left") {
            image=jumpLeft;
        }
        else if (direction=="right") {
            image=jumpRight;
        }
        }
        if (aimDirection!=null) {
            //em.k.shiftPressed=false;
            //em.k.hasPressed=false;
            animationNum=0;
        }
    }




    public void drawHealth(Graphics2D g2) {
        for (int i=0; i<health; i++) {
            g2.drawImage(bombHealth, 50+i*em.resTileSize, 40, em.resTileSize, em.resTileSize, null);
        }
    }




    public void drawHitbox(Graphics2D g2) {
            g2.setColor(Color.RED);
        g2.drawRect(screenX+solidArea.x, screenY+solidArea.y, solidArea.width, solidArea.height);
        //this code shows player hitbox
        
    }



    @Override
    public void draw(Graphics2D g2) {
        image=null;
            warmupTextDrawer(g2);
        handleDetonateAnim();
            handleMovementDraw();
        handleAimDirection();
        //g2.setColor(Color.WHITE);
        //g2.fillRect(worldX, worldY, em.resTileSize, em.resTileSize);
        BufferedImage finalImage=em.Skinner.ReskinPlayer(image, 2);
        g2.drawImage(finalImage, screenX, screenY, em.resTileSize*3, em.resTileSize*3, null);
        em.lRenderer.renderPlayer(screenX, screenY, 7, g2, direction, animationNum);
        if (bombsLeft>=11) {
            bombsLeft=10;
        }
        if (bombsLeftMove>=11) {
            bombsLeftMove=10;
        }
        if (bombType=="stickyMove") {
            g2.drawImage(MoveHealth[bombsLeftMove], em.resTileSize*15, 50, 64*3, em.resTileSize, null);
        }
        else {
            
                g2.drawImage(Health[bombsLeft], em.resTileSize*15, 50, 64*3, em.resTileSize, null);
        }
        drawHealth(g2);
        if (em.showHitboxes==true) {
            drawHitbox(g2);
        }
    }
}
