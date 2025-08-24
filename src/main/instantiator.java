package main;

import java.awt.Graphics2D;

import Entities.meleeRoman;
import Entities.rangedRoman;
import Events.tileBreak;
import Objects.Exit;
import Objects.bombBoi;
import Objects.boostPad;
import Objects.bubbles;

public class instantiator {
    int index=0;
    everythingManager em;
    public instantiator(everythingManager em) {
        this.em=em;
        setObjects();
    }
    public void setBombs() {
        if ((em.k.enterPressed==true || em.m.mouseClicked==true) && em.k.hasPressed==false && em.k.sRightPressed==false && em.k.sUpPressed==false) {
            if (em.p1.Move==true) {
                if (em.p1.bombsLeftMove!=0) {
                em.objBomb[index]=new bombBoi(em, em.p1.direction, index);
                em.playSE(1);
            em.p1.bombsLeftMove-=5;
            if (index==19) {
                index=0;
            }
            else {
                index++;
            }
            }
            }
            else {
                if (em.p1.bombsLeft!=0) {
                em.objBomb[index]=new bombBoi(em, em.p1.direction, index);
                em.playSE(1);
            em.p1.bombsLeft-=5;
            if (index==19) {
                index=0;
            }
            else {
                index++;
            }
            }
            }
            em.k.hasPressed=true;
        }
        for (int i=0; i<em.objBomb.length; i++) {
            if (em.objBomb[i]!=null) {
                if (em.objBomb[i].direction=="right" && em.objBomb[i].worldX>=em.maxWorldHoriz*em.resTileSize+em.resTileSize) {
                    em.objBomb[i]=null;
                    if (em.p1.Move==true) {
                        em.p1.bombsLeftMove+=5;
                    }
                    else {
                        em.p1.bombsLeft+=5;
                    }
                    }
                    else if (em.objBomb[i].direction=="left" && em.objBomb[i].worldX<-em.resTileSize) {
                        em.objBomb[i]=null;
                        if (em.p1.Move==true) {
                        em.p1.bombsLeftMove+=5;
                    }
                    else {
                        em.p1.bombsLeft+=5;
                    }
                    }
            }
        }
    }
    public void setObjects() {
        em.obj[0]=new Exit(em);
        em.obj[1]=new boostPad(em, "diagLeft", 200, em.maxWorldVert*em.resTileSize-480, 1);
        em.obj[2]=new bubbles(em, "left", 450, em.maxWorldVert*em.resTileSize-480, 5);
        em.eventManager[0]=new tileBreak(em);
        em.npc[0]=new meleeRoman(em, em.resTileSize*10, em.resTileSize*em.maxWorldVert-410, 1, false);
        em.npc[1]=new meleeRoman(em, em.resTileSize*14, em.resTileSize*em.maxWorldVert/2+200, 1, false);
        em.npc[2]=new meleeRoman(em, em.resTileSize*12, em.resTileSize*em.maxWorldVert/2-1000, 1, false);
        em.npc[3]=new meleeRoman(em, em.resTileSize*12+500, 900, 2, false);
        em.npc[4]=new meleeRoman(em, em.resTileSize*12, 900, 2, false);
        em.npc[5]=new rangedRoman(em, em.resTileSize*20, em.resTileSize*em.maxWorldVert-410, 1, false, "left", 5);
        em.npc[6]=new rangedRoman(em, em.resTileSize, em.resTileSize*em.maxWorldVert-1250, 1, false, "right", 6);
        em.npc[7]=new meleeRoman(em, 1000, em.resTileSize*em.maxWorldVert-610, 3, true);
        em.npc[8]=new meleeRoman(em, 800, em.resTileSize*em.maxWorldVert-2300, 3, true);
        em.npc[9]=new meleeRoman(em, 1000, em.resTileSize*em.maxWorldVert-2300, 3, true);
        em.npc[10]=new meleeRoman(em, 1150, em.resTileSize*em.maxWorldVert-2300, 3, true);
        em.npc[11]=new meleeRoman(em, 800, em.resTileSize*em.maxWorldVert-2500, 3, true);
        em.npc[12]=new meleeRoman(em, 1000, em.resTileSize*em.maxWorldVert-2500, 3, true);
        em.npc[13]=new meleeRoman(em, 1150, em.resTileSize*em.maxWorldVert-2500, 3, true);
        em.npc[14]=new meleeRoman(em, 800, em.resTileSize*em.maxWorldVert-2700, 3, true);
        em.npc[15]=new meleeRoman(em, 1000, em.resTileSize*em.maxWorldVert-2700, 3, true);
        em.npc[16]=new meleeRoman(em, 1150, em.resTileSize*em.maxWorldVert-2700, 3, true);
    }
    
}
