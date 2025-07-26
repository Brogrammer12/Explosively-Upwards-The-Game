package main;

import java.awt.Graphics2D;

import Entities.meleeRoman;
import Entities.rangedRoman;
import Objects.Exit;
import Objects.bombBoi;

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
        em.npc[0]=new meleeRoman(em, em.resTileSize*10, em.resTileSize*em.maxWorldVert-410, 1, false);
        em.npc[1]=new meleeRoman(em, em.resTileSize*14, em.resTileSize*em.maxWorldVert/2+200, 1, false);
        em.npc[2]=new meleeRoman(em, em.resTileSize*12, em.resTileSize*em.maxWorldVert/2-1000, 1, false);
        em.npc[3]=new meleeRoman(em, em.resTileSize*12+100, 900, 2, false);
        em.npc[4]=new meleeRoman(em, em.resTileSize*12, 900, 2, false);
        em.npc[5]=new rangedRoman(em, em.resTileSize*20, em.resTileSize*em.maxWorldVert-410, 1, false, "left", 5);
        em.npc[6]=new rangedRoman(em, em.resTileSize*15, em.resTileSize*em.maxWorldVert-1010, 1, false, "right", 6);
    }
    
}
