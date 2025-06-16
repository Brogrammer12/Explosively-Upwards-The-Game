package Entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.everythingManager;

public class Entity {
    public float worldX, worldY;
      public BufferedImage bombHealth;
      public BufferedImage[] Health, MoveHealth;
      public BufferedImage image=null;
      public int healtha;
      public int maxHealth;
    public String direction="left";
    public int moveSpeed;
    public int SpriteNum=0;
    public int spriteCounter=0;
    public int IdleStage=0;
    public boolean idleBack=false;
    public int bombX, bombY;
    public Rectangle solidArea;
    public Rectangle defaultSolidArea=new Rectangle(0, 0, 16*3*3, 16*3*3);
    public boolean collisionOn=false;
    public boolean falling=false;
    public boolean standing=false;
    public float velocityY=0;
    public float velocityX=0;
    public float gravity=0.4f;
    public float XGravity=0.4f;
    public float jumpStrength=-10f;
    public float maxFallSpeed=15;
    public boolean grounded=false;
    everythingManager em;
    public Entity(everythingManager em) {
      this.em=em;
    }
}
