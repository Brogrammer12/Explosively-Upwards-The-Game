package main;

import Entities.Entity;
import Objects.object;

public class CollisionChecker {
    everythingManager em;
    public boolean ceiling=false;
    public CollisionChecker(everythingManager em) {
        this.em=em;
    }
    public void checkPlayer(Entity entity) {
        int entityLeftWorldX=(int) (entity.worldX+entity.solidArea.x);
        int entityRightWorldX=(int) (entity.worldX+entity.solidArea.x+entity.solidArea.width);
        int entityTopWorldY=(int) (entity.worldY+entity.solidArea.y);
        int entityBottomWorldY=(int) (entity.worldY+entity.solidArea.y+entity.solidArea.height);
        int entityLeftcol=entityLeftWorldX/em.resTileSize;
        int entityRightcol=entityRightWorldX/em.resTileSize;
        int entityTopRow=entityTopWorldY/em.resTileSize;
        int entityBottomRow=entityBottomWorldY/em.resTileSize;
        int tileNum1, tileNum2;
        if (entity.worldX>em.worldWidth-3*em.resTileSize || entity.worldX<0 || entity.worldY>em.worldHeight-4*em.resTileSize || entity.worldY<0) {
            entity.velocityY+=entity.gravity;
        entity.velocityY=Math.min(entity.velocityY, entity.maxFallSpeed);
        entity.worldY+=entity.velocityY;
        entity.grounded=false;
        }
        else {
            try {
                if (entity.grounded==false && ceiling==false) {
                    entityTopRow=(int) ((entityTopWorldY-entity.velocityY)/em.resTileSize);
        tileNum1=em.tileM.mapTileNum[entityLeftcol] [entityTopRow];
        tileNum2=em.tileM.mapTileNum[entityRightcol] [entityTopRow];
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            if (em.tileM.tile[tileNum1].grounded==false && em.tileM.tile[tileNum2].grounded==false) {
                entity.velocityY=5;
                ceiling=true;
            }
        }
                }
                else {
                    ceiling=false;
                }
                entityBottomRow=(int) ((entityBottomWorldY+entity.velocityY)/em.resTileSize);
        tileNum1=em.tileM.mapTileNum[entityLeftcol] [entityBottomRow];
        tileNum2=em.tileM.mapTileNum[entityRightcol] [entityBottomRow];
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            if (entity.velocityY>=0) {
                entity.velocityY=0;
            entity.grounded=true;
            }
            else {
                entity.velocityY+=entity.gravity;
        entity.velocityY=Math.min(entity.velocityY, entity.maxFallSpeed);
        entity.worldY+=entity.velocityY;
        entity.grounded=false;
            }
        }
        else {
            entity.velocityY+=entity.gravity;
        entity.velocityY=Math.min(entity.velocityY, entity.maxFallSpeed);
        entity.worldY+=entity.velocityY;
        entity.grounded=false;
        }
            }
            catch (Exception e) {
               
            }
        }
       switch (entity.direction) {
            case "left":
            entityLeftcol=(entityLeftWorldX-entity.moveSpeed)/em.resTileSize;
        tileNum1=em.tileM.mapTileNum[entityLeftcol] [entityTopRow];
        tileNum2=em.tileM.mapTileNum[entityLeftcol] [entityBottomRow];
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            if (em.tileM.tile[tileNum1].grounded==false && em.tileM.tile[tileNum2].grounded==false) {
                entity.collisionOn=true;
            }
        }
            break;
            case "right":
        entityRightcol=(entityRightWorldX+entity.moveSpeed)/em.resTileSize;
        tileNum1=em.tileM.mapTileNum[entityRightcol] [entityTopRow];
        tileNum2=em.tileM.mapTileNum[entityRightcol] [entityBottomRow];
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            if (em.tileM.tile[tileNum1].grounded==false && em.tileM.tile[tileNum2].grounded==false) {
                entity.collisionOn=true;
            }
        }
            break;
        }
    }
    public void checkBomb(object entity) {
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
            entity.sideCol=true;
        }
            break;
            case "right":
        entityRightcol=(entityRightWorldX+entity.moveSpeed)/em.resTileSize;
        tileNum1=em.tileM.mapTileNum[entityRightcol] [entityTopRow];
        tileNum2=em.tileM.mapTileNum[entityRightcol] [entityBottomRow];
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            entity.bombTriggered=true;
            entity.sideCol=true;
        }
            break;
        }
        if (entity.bombTriggered!=true) {
            entityBottomRow=(entityBottomWorldY+entity.moveSpeed)/em.resTileSize;
        tileNum1=em.tileM.mapTileNum[entityLeftcol] [entityBottomRow];
        tileNum2=em.tileM.mapTileNum[entityRightcol] [entityBottomRow];
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            entity.bombTriggered=true;
            entity.sideCol=false;
        }
        }
            }
            catch (Exception e) {

            }
        
    }
}
