package Entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public float worldX, worldY;
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
