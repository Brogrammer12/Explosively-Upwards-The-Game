package main;

import java.awt.Rectangle;

import Entities.Entity;
import Entities.Player;
import Entities.meleeRoman;
import Objects.arrow;
import Objects.bombBoi;
import Objects.object;
import Tiles.tileSuperclass;

public class CollisionChecker {
    everythingManager em;
    public boolean ceiling=false;
    //public boolean currentlyColliding=false;
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

        //This guy below is supposed to detect when the player is out of the map bounds. A bit scuffed right now.
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
        //this piece of the code below is supposed to be detecting collision with the ceiling. Working so far.
        else {
            try {
                if (entity.getClass()==Player.class) {
                    if (entity.grounded==false && ceiling==false && entity.disableGravity==false) {
                   /* entityTopRow=(int) ((entityTopWorldY-entity.velocityY)/em.resTileSize);
        tileNum1=em.tileM.mapTileNum[entityLeftcol] [entityTopRow].tileNum;
        tileNum2=em.tileM.mapTileNum[entityRightcol] [entityTopRow].tileNum;
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            if (em.tileM.tile[tileNum1].grounded==false || em.tileM.tile[tileNum2].grounded==false) {
                entity.velocityY=5;
                ceiling=true;
            }
        }*/
        int playerCol = (int) em.p1.worldX / em.resTileSize;
        int playerRow = (int) em.p1.worldY / em.resTileSize;
        int radius = 2; // how many tiles around the player to check
        int startCol = Math.max(0, playerCol - radius);
        int endCol = Math.min(em.tileM.mapTileNum.length - 1, playerCol + radius);
        int startRow = Math.max(0, playerRow - radius);
        int endRow = Math.min(em.tileM.mapTileNum[0].length - 1, playerRow + radius);
        for (int i=startCol; i<endCol; i++) {
                            for (int e=startRow; e<endRow; e++) {
                                Rectangle p1Area=new Rectangle(entity.solidArea.x, entity.solidArea.y, entity.solidArea.width, entity.solidArea.height);
                                Rectangle tempTileArea=new Rectangle(em.tileM.mapTileNum[i] [e].tileSolidArea.x, em.tileM.mapTileNum[i] [e].tileSolidArea.y, em.tileM.mapTileNum[i] [e].tileSolidArea.width, em.tileM.mapTileNum[i] [e].tileSolidArea.height);
                                p1Area.x=(int) em.p1.worldX;
                                p1Area.y=(int) em.p1.worldY;
                                tempTileArea.x=i*em.resTileSize;
                                tempTileArea.y=e*em.resTileSize;
                                if (tempTileArea.intersects(p1Area) && em.tileM.tile[em.tileM.mapTileNum[i] [e].tileNum].collision==true && em.tileM.tile[em.tileM.mapTileNum[i] [e].tileNum].grounded==false) {
                                    if (em.p1.worldY>tempTileArea.y) {
                                        entity.velocityY=5;
                                        entity.currentlyColliding=false;
                                        ceiling=true;
                                    }
                                }
                            }
                        }
                }
                else {
                    ceiling=false;
                }
                }
                if (entity.disableGravity==false) {
                    int playerCol = (int) em.p1.worldX / em.resTileSize;
        int playerRow = (int) em.p1.worldY / em.resTileSize;
        int radius = 2; // how many tiles around the player to check
        int startCol = Math.max(0, playerCol - radius);
        int endCol = Math.min(em.tileM.mapTileNum.length - 1, playerCol + radius);
        int startRow = Math.max(0, playerRow - radius);
        int endRow = Math.min(em.tileM.mapTileNum[0].length - 1, playerRow + radius);
        outerLoop:
        for (int i=0; i<em.tileM.mapTileNum.length; i++) {
                            for (int e=0; e<em.tileM.mapTileNum[i].length; e++) {
                                Rectangle p1Area=new Rectangle((int) entity.worldX+entity.solidArea.x, (int) entity.worldY+entity.solidArea.y, entity.solidArea.width, entity.solidArea.height);
                                Rectangle tempTileArea=new Rectangle(i*em.resTileSize+em.tileM.mapTileNum[i] [e].tileSolidArea.x, e*em.resTileSize+em.tileM.mapTileNum[i] [e].tileSolidArea.y, em.tileM.mapTileNum[i] [e].tileSolidArea.width, em.tileM.mapTileNum[i] [e].tileSolidArea.height);
                                if (tempTileArea.intersects(p1Area) && em.tileM.tile[em.tileM.mapTileNum[i] [e].tileNum].collision==true) {
                                  // if (p1Area.y<tempTileArea.y) {
                                  Rectangle intersection=p1Area.intersection(tempTileArea);
                                    if (entity.velocityY>=0 && em.tileM.mapTileNum[i] [e].tileNum!=0) {
                                        //System.out.println("width:"+intersection.width);
                                       // System.out.println("height:"+intersection.height);
                                        if (intersection.width==48 && intersection.height<48 && entity.worldY<tempTileArea.y) {
                                            entity.velocityY=0;
                                            //entity.velocityX=0;
                                            entity.currentlyColliding=true;
                                            entity.grounded=true;
                                            //System.out.println("intersection happened:"+"width: "+intersection.width+"height: "+intersection.height);
                                            //System.out.println("tile x: "+i*em.resTileSize+" tile y: "+ e*em.resTileSize);
                                            break outerLoop;
                                        }
                                        else {
                                            entity.currentlyColliding=false;
                                            entity.grounded=false;
                                            //System.out.println("condition failed: "+"width: "+intersection.width+"height: "+intersection.height);
                                            //System.out.println("tile x fail: "+i*em.resTileSize+" tile y fail: "+ e*em.resTileSize);
                                    }
                                       // if (intersection.height>intersection.width) {
                                            //entity.worldY -= intersection.height;
                                      //  }
                                    }
                                  // }
                                }
                            }
                        }
                   /* entityBottomRow=(int) ((entityBottomWorldY+entity.velocityY)/em.resTileSize);
        tileNum1=em.tileM.mapTileNum[entityLeftcol] [entityBottomRow].tileNum;
        tileNum2=em.tileM.mapTileNum[entityRightcol] [entityBottomRow].tileNum;
        tileNum3=em.tileM.mapTileNum[entityMiddlecol] [entityBottomRow].tileNum;
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
        }*/

        //supposed to be else if down here
        if (entity.disableGravity==false && entity.currentlyColliding==false) {
            //entity.grounded=false;
            //System.out.println("not colliding, still applying gravity");
            entity.velocityY+=entity.gravity;
        entity.velocityY=Math.min(entity.velocityY, entity.maxFallSpeed);
        entity.worldY+=entity.velocityY;
        entity.grounded=false;
        }
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
                }
            }
            catch (Exception e) {
               e.printStackTrace();
            }
        }
        try {
            outerLoop2:
        for (int i=0; i<em.tileM.mapTileNum.length; i++) {
                            for (int e=0; e<em.tileM.mapTileNum[i].length; e++) {
                                Rectangle p1Area=new Rectangle((int) entity.worldX+entity.solidArea.x, (int) entity.worldY+entity.solidArea.y, entity.solidArea.width, entity.solidArea.height);
                                Rectangle tempTileArea=new Rectangle(i*em.resTileSize+em.tileM.mapTileNum[i] [e].tileSolidArea.x, e*em.resTileSize+em.tileM.mapTileNum[i] [e].tileSolidArea.y, em.tileM.mapTileNum[i] [e].tileSolidArea.width, em.tileM.mapTileNum[i] [e].tileSolidArea.height);
                                if (tempTileArea.intersects(p1Area) && em.tileM.tile[em.tileM.mapTileNum[i] [e].tileNum].collision==true) {
                                  // if (p1Area.y<tempTileArea.y) {
                                  Rectangle intersection=p1Area.intersection(tempTileArea);
                                    if (em.tileM.mapTileNum[i] [e].tileNum!=0 && em.tileM.tile[em.tileM.mapTileNum[i] [e].tileNum].grounded==false) {
                                        //System.out.println("width:"+intersection.width);
                                       // System.out.println("height:"+intersection.height);
                                        if (intersection.height==48 && intersection.width<48) {
                                           // entity.velocityY=0;
                                            entity.velocityX=0;
                                            entity.collisionOn=true;
                                            if (p1Area.x<tempTileArea.x && em.k.leftPressed==true) {
                                                entity.collisionOn=false;
                                            }
                                            else if(p1Area.x>tempTileArea.x && em.k.rightPressed==true) {
                                                entity.collisionOn=false;
                                            }
                                            //entity.grounded=true;
                                           // System.out.println("intersection happened:"+"width: "+intersection.width+"height: "+intersection.height);
                                           // System.out.println("tile x: "+i*em.resTileSize+" tile y: "+ e*em.resTileSize);
                                            break outerLoop2;
                                        }
                                        else {
                                            entity.collisionOn=false;
                                            //entity.currentlyColliding=false;
                                            //entity.grounded=false;
                                           // System.out.println("condition failed: "+"width: "+intersection.width+"height: "+intersection.height);
                                            //System.out.println("tile x fail: "+i*em.resTileSize+" tile y fail: "+ e*em.resTileSize);
                                    }
                                       // if (intersection.height>intersection.width) {
                                            //entity.worldY -= intersection.height;
                                      //  }
                                    }
                                  // }
                                }
                            }
                        }
            /*if (em.m.mouseMode==false || entity.getClass()!=Player.class) {
                if (entity.disableGravity==false || entity.getClass()!=Player.class) {
                switch (entity.direction) {
            case "left":
            entityLeftcol=(entityLeftWorldX-entity.moveSpeed)/em.resTileSize;
        tileNum1=em.tileM.mapTileNum[entityLeftcol] [entityTopRow].tileNum;
        tileNum2=em.tileM.mapTileNum[entityLeftcol] [entityBottomRow].tileNum;
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            if (em.tileM.tile[tileNum1].grounded==false || em.tileM.tile[tileNum2].grounded==false) {
                entity.collisionOn=true;
            }
        }
            break;
            case "right":
        entityRightcol=(entityRightWorldX+entity.moveSpeed)/em.resTileSize;
        tileNum1=em.tileM.mapTileNum[entityRightcol] [entityTopRow].tileNum;
        tileNum2=em.tileM.mapTileNum[entityRightcol] [entityBottomRow].tileNum;
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
        tileNum1=em.tileM.mapTileNum[entityLeftcol] [entityTopRow].tileNum;
        tileNum2=em.tileM.mapTileNum[entityLeftcol] [entityBottomRow].tileNum;
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
        tileNum1=em.tileM.mapTileNum[entityRightcol] [entityTopRow].tileNum;
        tileNum2=em.tileM.mapTileNum[entityRightcol] [entityBottomRow].tileNum;
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            if (em.tileM.tile[tileNum1].grounded==false || em.tileM.tile[tileNum2].grounded==false) {
                entity.collisionOn=true;
                em.stopXR=false;
                entity.velocityX=0;
            }
        }
           }
            }
            }*/
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
        tileNum1=em.tileM.mapTileNum[entityLeftcol] [entityTopRow].tileNum;
        tileNum2=em.tileM.mapTileNum[entityLeftcol] [entityBottomRow].tileNum;
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
                em.tileM.mapTileNum[entityLeftcol] [entityTopRow].tileNum=0;
            }
            if (entity.explode==true && em.tileM.tile[tileNum2].destructible==true && entity.Move==false) {
                em.tileM.mapTileNum[entityLeftcol] [entityBottomRow].tileNum=0;
            }
        }
            break;
            case "right":
        entityRightcol=(entityRightWorldX+entity.moveSpeed)/em.resTileSize;
        tileNum1=em.tileM.mapTileNum[entityRightcol] [entityTopRow].tileNum;
        tileNum2=em.tileM.mapTileNum[entityRightcol] [entityBottomRow].tileNum;
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
                em.tileM.mapTileNum[entityRightcol] [entityTopRow].tileNum=0;
            }
            if (entity.explode==true && em.tileM.tile[tileNum2].destructible==true && entity.Move==false) {
                em.tileM.mapTileNum[entityRightcol] [entityBottomRow].tileNum=0;
            }
        }
            break;
        }
        if (entity.bombTriggered!=true) {
            entityBottomRow=(entityBottomWorldY+entity.moveSpeed)/em.resTileSize;
        tileNum1=em.tileM.mapTileNum[entityLeftcol] [entityBottomRow].tileNum;
        tileNum2=em.tileM.mapTileNum[entityRightcol] [entityBottomRow].tileNum;
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
                em.tileM.mapTileNum[entityLeftcol] [entityBottomRow].tileNum=0;
            }
            if (entity.explode==true && em.tileM.tile[tileNum2].destructible==true && entity.Move==false) {
                em.tileM.mapTileNum[entityRightcol] [entityBottomRow].tileNum=0;
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
        tileNum1=em.tileM.mapTileNum[entityLeftcol] [entityTopRow].tileNum;
        tileNum2=em.tileM.mapTileNum[entityLeftcol] [entityBottomRow].tileNum;
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            entity.kys=true;
            
        }
            break;
            case "right":
            entityRightcol=(entityRightWorldX+entity.moveSpeed)/em.resTileSize;
        tileNum1=em.tileM.mapTileNum[entityRightcol] [entityTopRow].tileNum;
        tileNum2=em.tileM.mapTileNum[entityRightcol] [entityBottomRow].tileNum;
        if (em.tileM.tile[tileNum1].collision==true || em.tileM.tile[tileNum2].collision==true) {
            entity.kys=true;
            
        }
            break;
        }
        if (entity.kys!=true) {
            entityBottomRow=(entityBottomWorldY+entity.moveSpeed)/em.resTileSize;
        tileNum1=em.tileM.mapTileNum[entityLeftcol] [entityBottomRow].tileNum;
        tileNum2=em.tileM.mapTileNum[entityRightcol] [entityBottomRow].tileNum;
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
