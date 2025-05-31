package main;

import java.awt.Graphics2D;

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
            em.p1.bombsLeftMove--;
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
            em.p1.bombsLeft--;
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
                        em.p1.bombsLeftMove++;
                    }
                    else {
                        em.p1.bombsLeft++;
                    }
                    }
                    else if (em.objBomb[i].direction=="left" && em.objBomb[i].worldX<-em.resTileSize) {
                        em.objBomb[i]=null;
                        if (em.p1.Move==true) {
                        em.p1.bombsLeftMove++;
                    }
                    else {
                        em.p1.bombsLeft++;
                    }
                    }
            }
        }
    }
    public void setObjects() {
        em.obj[0]=new Exit(em);
    }
    
}
