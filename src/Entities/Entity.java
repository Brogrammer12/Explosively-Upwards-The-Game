package Entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public float worldX, worldY;
    public BufferedImage left1,leftIdle2, leftIdle3, right1, rightIdle2, rightIdle3, 
    bomb, bombPlanted, rWalk1, rWalk2, rWalk3, rWalk4, lWalk1, lWalk2, lWalk3, lWalk4,
     crouchLeft, crouchRight, jumpRight, jumpLeft, pRightBoom1, pRightBoom2, pLeftBoom1,
      pLeftBoom2;
      public BufferedImage bombHealth;
      public BufferedImage[] Health, MoveHealth;
    public String direction="left";
    public int moveSpeed;
    public int SpriteNum=0;
    public int spriteCounter=0;
    public int IdleStage=0;
    public boolean idleBack=false;
    public int bombX, bombY;
    public Rectangle solidArea;
    public boolean collisionOn=false;
    public boolean falling=false;
    public boolean standing=false;
    public float velocityY=0;
    public float gravity=0.4f;
    public float jumpStrength=-10f;
    public float maxFallSpeed=15;
    public boolean grounded=false;
}
