package main;

import Entities.Entity;
import Entities.Player;
import Entities.meleeRoman;
import Objects.arrow;
import Objects.bombBoi;
import Objects.object;

public class CollisionChecker {
    everythingManager em;
    public boolean ceiling=false;
    public CollisionChecker(everythingManager em) {
        this.em=em;
    }
    public void checkPlayer(Entity entity) {
        if (em.tileM!=null) {
            int entityLeftWorldX=(int) (entity.worldX+entity.solidArea.x);
        int entityRightWorldX=(int) (entity.worldX+entity.solidArea.x+entity.solidArea.width);
        int entityMiddleWorldX=(int) (entity.worldX+entity.solidArea.x+entity.solidArea.width/2);
        int entityTopWorldY=(int) (entity.worldY+entity.solidArea.y);
        int entityBottomWorldY=(int) (entity.worldY+entity.solidArea.y+entity.solidArea.height);
        int entityLeftcol=entityLeftWorldX/em.resTileSize;
        int entityRightcol=entityRightWorldX/em.resTileSize;
        int entityMiddlecol=entityMiddleWorldX/em.resTileSize;
        int entityTopRow=entityTopWorldY/em.resTileSize;
        int entityBottomRow=entityBottomWorldY/em.resTileSize;
        int tileNum1, tileNum2, tileNum3;
        if (entity.worldX+entity.solidArea.width>em.worldWidth || entity.worldX<0 || entity.worldY+entity.solidArea.height>em.worldHeight || entity.worldY<0) {
            if (entity.disableGravity==false) {
                entity.velocityY+=entity.gravity;
        entity.velocityY=Math.min(entity.velocityY, entity.maxFallSpeed);
        entity.worldY+=entity.velocityY;
            if (entity.velocityX!=0) {
                if (em.stopX==true) {
                    entity.velocityX+=entity.XGravity;
        entity.velocityX=Math.min(entity.velocityX, entity.maxFallSpeed);
        entity.worldX+=entity.velocityX;
                }
                else if(em.stopXR==true) {
                    entity.velocityX-=entity.XGravity;
        //entity.velocityX=Math.min(entity.velocityX, entity.maxFallSpeed);
        entity.worldX+=entity.velocityX;
                }
        }
        
        entity.grounded=false;
            }
        }
        else {
            try {
                if (entity.getClass()==Player.class) {
                    if (entity.grounded==false && ceiling==false && entity.disableGravity==false && entity.disableGravity==false) {
                    entityTopRow=(int) ((entityTopWorldY-entity.velocityY)/em.resTileSize);
        tileNum1=em.tileM.mapTileNum[entityLeftcol] [entityTopRow];
        tileNum2=em.tileM.mapTileNum[entityRightcol] [entityTopRow];
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            if (em.tileM.tile[tileNum1].grounded==false || em.tileM.tile[tileNum2].grounded==false) {
                entity.velocityY=5;
                ceiling=true;
            }
        }
                }
                else {
                    ceiling=false;
                }
                }
                if (entity.disableGravity==false) {
                    entityBottomRow=(int) ((entityBottomWorldY+entity.velocityY)/em.resTileSize);
        tileNum1=em.tileM.mapTileNum[entityLeftcol] [entityBottomRow];
        tileNum2=em.tileM.mapTileNum[entityRightcol] [entityBottomRow];
        tileNum3=em.tileM.mapTileNum[entityMiddlecol] [entityBottomRow];
        if ((em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true || em.tileM.tile[tileNum3].collision==true)) {
            if (entity.velocityY>=0) {
               entity.velocityY=0;
                if (entity.velocityX!=0) {
                    if (em.stopX==true) {
                        entity.velocityX+=entity.XGravity;
        entity.velocityX=Math.min(entity.velocityX, entity.maxFallSpeed);
        entity.worldX+=entity.velocityX;
                }
                else if(em.stopXR==true) {
                    entity.velocityX-=entity.XGravity;
        //entity.velocityX=Math.min(entity.velocityX, entity.maxFallSpeed);
        entity.worldX+=entity.velocityX;
                }
        }
            entity.grounded=true;
            }
            else if (entity.disableGravity==false) {
                entity.velocityY+=entity.gravity;
        entity.velocityY=Math.min(entity.velocityY, entity.maxFallSpeed);
        entity.worldY+=entity.velocityY;
        if (entity.velocityX!=0) {
            if (em.stopX==true) {
                entity.velocityX+=entity.XGravity;
        entity.velocityX=Math.min(entity.velocityX, entity.maxFallSpeed);
        entity.worldX+=entity.velocityX;
                }
                else if(em.stopXR==true) {
                    entity.velocityX-=entity.XGravity;
        //entity.velocityX=Math.min(entity.velocityX, entity.maxFallSpeed);
        entity.worldX+=entity.velocityX;
                }
        }
        entity.grounded=false;
            }
        }
        else if (entity.disableGravity==false) {
            entity.velocityY+=entity.gravity;
        entity.velocityY=Math.min(entity.velocityY, entity.maxFallSpeed);
        entity.worldY+=entity.velocityY;
        if (entity.velocityX!=0) {
            if (em.stopX==true) {
                entity.velocityX+=entity.XGravity;
        entity.velocityX=Math.min(entity.velocityX, entity.maxFallSpeed);
        entity.worldX+=entity.velocityX;
                }
                else if(em.stopXR==true) {
                  entity.velocityX-=entity.XGravity;
        //entity.velocityX=Math.min(entity.velocityX, entity.maxFallSpeed);
        entity.worldX+=entity.velocityX;  
                }
        }
        entity.grounded=false;
        }
                }
            }
            catch (Exception e) {
               e.printStackTrace();
            }
        }
        try {
            if (em.m.mouseMode==false || entity.getClass()!=Player.class) {
                if (entity.disableGravity==false || entity.getClass()!=Player.class) {
                switch (entity.direction) {
            case "left":
            entityLeftcol=(entityLeftWorldX-entity.moveSpeed)/em.resTileSize;
        tileNum1=em.tileM.mapTileNum[entityLeftcol] [entityTopRow];
        tileNum2=em.tileM.mapTileNum[entityLeftcol] [entityBottomRow];
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            if (em.tileM.tile[tileNum1].grounded==false || em.tileM.tile[tileNum2].grounded==false) {
                entity.collisionOn=true;
            }
        }
            break;
            case "right":
        entityRightcol=(entityRightWorldX+entity.moveSpeed)/em.resTileSize;
        tileNum1=em.tileM.mapTileNum[entityRightcol] [entityTopRow];
        tileNum2=em.tileM.mapTileNum[entityRightcol] [entityBottomRow];
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            if (em.tileM.tile[tileNum1].grounded==false || em.tileM.tile[tileNum2].grounded==false) {
                entity.collisionOn=true;
            }
        }
            break;
        }
            }
            }
            else if (em.m.mouseMode==true) {
                if (entity.disableGravity==false) {
           if (em.k.leftPressed==true || em.p1.velocityX!=0) {
            entityLeftcol=(entityLeftWorldX-entity.moveSpeed)/em.resTileSize;
        tileNum1=em.tileM.mapTileNum[entityLeftcol] [entityTopRow];
        tileNum2=em.tileM.mapTileNum[entityLeftcol] [entityBottomRow];
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            if (em.tileM.tile[tileNum1].grounded==false || em.tileM.tile[tileNum2].grounded==false) {
                entity.collisionOn=true;
                em.stopX=false;
                entity.velocityX=0;
            }
        }
           }
           else if (em.k.rightPressed==true || em.p1.velocityX!=0) {
            entityRightcol=(entityRightWorldX+entity.moveSpeed)/em.resTileSize;
        tileNum1=em.tileM.mapTileNum[entityRightcol] [entityTopRow];
        tileNum2=em.tileM.mapTileNum[entityRightcol] [entityBottomRow];
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            if (em.tileM.tile[tileNum1].grounded==false || em.tileM.tile[tileNum2].grounded==false) {
                entity.collisionOn=true;
                em.stopXR=false;
                entity.velocityX=0;
            }
        }
           }
            }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        }
    }
    public void checkBomb(object entity) {
        if (entity.getClass()!=arrow.class) {
            int entityLeftWorldX=entity.worldX+entity.solidArea.x;
        int entityRightWorldX=entity.worldX+entity.solidArea.x+entity.solidArea.width;
        int entityTopWorldY=entity.worldY+entity.solidArea.y;
        int entityBottomWorldY=entity.worldY+entity.solidArea.y+entity.solidArea.height;
        int entityLeftcol=entityLeftWorldX/em.resTileSize;
        int entityRightcol=entityRightWorldX/em.resTileSize;
        int entityTopRow=entityTopWorldY/em.resTileSize;
        int entityBottomRow=entityBottomWorldY/em.resTileSize;
        int tileNum1, tileNum2;
            try {
        switch (entity.direction) {
            case "left":
            entityLeftcol=(entityLeftWorldX-entity.moveSpeed)/em.resTileSize;
        tileNum1=em.tileM.mapTileNum[entityLeftcol] [entityTopRow];
        tileNum2=em.tileM.mapTileNum[entityLeftcol] [entityBottomRow];
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            entity.bombTriggered=true;
            //entity.sideCol=true;
            if (entity.explode==false && em.p1.bombType!="stickyMove") {
                 if (em.p1.bombsLeft==5) {
                em.p1.bombsLeft=10;
            }
            else {
                em.p1.bombsLeft=5;
            }
            }
            if (entity.Move==false) {
                entity.explode=true; // makes it explode on impact
            }
            if (entity.explode==true && em.tileM.tile[tileNum1].destructible==true && entity.Move==false) {
                em.tileM.mapTileNum[entityLeftcol] [entityTopRow]=0;
            }
            if (entity.explode==true && em.tileM.tile[tileNum2].destructible==true && entity.Move==false) {
                em.tileM.mapTileNum[entityLeftcol] [entityBottomRow]=0;
            }
        }
            break;
            case "right":
        entityRightcol=(entityRightWorldX+entity.moveSpeed)/em.resTileSize;
        tileNum1=em.tileM.mapTileNum[entityRightcol] [entityTopRow];
        tileNum2=em.tileM.mapTileNum[entityRightcol] [entityBottomRow];
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            entity.bombTriggered=true;
            //entity.sideCol=true;
            if (entity.explode==false && em.p1.bombType!="stickyMove") {
                 if (em.p1.bombsLeft==5) {
                em.p1.bombsLeft=10;
            }
            else {
                em.p1.bombsLeft=5;
            }
            }

           if (entity.Move==false) {
                entity.explode=true; // makes it explode on impact
            }
            if (entity.explode==true && em.tileM.tile[tileNum1].destructible==true && entity.Move==false) {
                em.tileM.mapTileNum[entityRightcol] [entityTopRow]=0;
            }
            if (entity.explode==true && em.tileM.tile[tileNum2].destructible==true && entity.Move==false) {
                em.tileM.mapTileNum[entityRightcol] [entityBottomRow]=0;
            }
        }
            break;
        }
        if (entity.bombTriggered!=true) {
            entityBottomRow=(entityBottomWorldY+entity.moveSpeed)/em.resTileSize;
        tileNum1=em.tileM.mapTileNum[entityLeftcol] [entityBottomRow];
        tileNum2=em.tileM.mapTileNum[entityRightcol] [entityBottomRow];
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            if (entity.Move==false) {
                entity.bombTriggered=true;
            entity.explode=true;
            }
            if (em.p1.bombType!="stickyMove") {
                if (em.p1.bombsLeft==5) {
                em.p1.bombsLeft=10;
            }
            else {
                em.p1.bombsLeft=5;
            }
            }
            entity.sideCol=false;
             if (entity.explode==true && em.tileM.tile[tileNum1].destructible==true && entity.Move==false) {
                em.tileM.mapTileNum[entityLeftcol] [entityBottomRow]=0;
            }
            if (entity.explode==true && em.tileM.tile[tileNum2].destructible==true && entity.Move==false) {
                em.tileM.mapTileNum[entityRightcol] [entityBottomRow]=0;
            }
        }
        }
            }
            catch (Exception e) {

            }
        }
        else if(entity.getClass()==arrow.class) {
            int entityLeftWorldX=entity.worldX+entity.solidArea.x;
        int entityRightWorldX=entity.worldX+entity.solidArea.x+entity.solidArea.width;
        int entityTopWorldY=entity.worldY+entity.solidArea.y;
        int entityBottomWorldY=entity.worldY+entity.solidArea.y+entity.solidArea.height;
        int entityLeftcol=entityLeftWorldX/em.resTileSize;
        int entityRightcol=entityRightWorldX/em.resTileSize;
        int entityTopRow=entityTopWorldY/em.resTileSize;
        int entityBottomRow=entityBottomWorldY/em.resTileSize;
        int tileNum1, tileNum2;
        switch (entity.direction) {
            case "left":
            entityLeftcol=(entityLeftWorldX-entity.moveSpeed)/em.resTileSize;
        tileNum1=em.tileM.mapTileNum[entityLeftcol] [entityTopRow];
        tileNum2=em.tileM.mapTileNum[entityLeftcol] [entityBottomRow];
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            entity.kys=true;
            
        }
            break;
            case "right":
            entityRightcol=(entityRightWorldX+entity.moveSpeed)/em.resTileSize;
        tileNum1=em.tileM.mapTileNum[entityRightcol] [entityTopRow];
        tileNum2=em.tileM.mapTileNum[entityRightcol] [entityBottomRow];
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            entity.kys=true;
            
        }
            break;
        }
        if (entity.kys!=true) {
            entityBottomRow=(entityBottomWorldY+entity.moveSpeed)/em.resTileSize;
        tileNum1=em.tileM.mapTileNum[entityLeftcol] [entityBottomRow];
        tileNum2=em.tileM.mapTileNum[entityRightcol] [entityBottomRow];
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            entity.kys=true;
            
        }
        }
        }
    }
    public void checkEntity(Entity player, Entity[] entity) {
        for (int i=0; i<entity.length; i++) {
            if (entity[i]!=null) {
                if (entity[i].healtha>=2 && entity[i].Level==em.p1.Level) {
entity[i].solidArea.x=(int) entity[i].worldX;
         entity[i].solidArea.y=(int) entity[i].worldY;
         player.solidArea.x=(int) player.worldX;
         player.solidArea.y=(int) player.worldY;
if (em.k.leftPressed==true) {
    player.solidArea.x-=player.moveSpeed;
if (player.solidArea.intersects(entity[i].solidArea)) {
    player.collisionOn=true;
}
}
else if(em.k.rightPressed==true) {
    player.solidArea.x+=player.moveSpeed;
if (player.solidArea.intersects(entity[i].solidArea)) {
    player.collisionOn=true;
}
}



         entity[i].solidArea.x=entity[i].defaultSolidArea.x;
         entity[i].solidArea.y=entity[i].defaultSolidArea.y;
         player.solidArea.x=player.defaultSolidArea.x;
         player.solidArea.y=player.defaultSolidArea.y;
        }
            }
        }
    }
}
