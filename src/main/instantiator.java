package main;

import java.awt.Graphics2D;

import Objects.bombBoi;

public class instantiator {
    int index=0;
    everythingManager em;
    public instantiator(everythingManager em) {
        this.em=em;
    }
    public void setObject() {
        if (em.k.enterPressed==true && em.k.hasPressed==false) {
            em.obj[index]=new bombBoi(em, em.p1.direction);
            if (index==9) {
                index=0;
            }
            else {
                index++;
            }
            em.k.hasPressed=true;
        }
        for (int i=0; i<em.obj.length; i++) {
            if (em.obj[i]!=null) {
                if (em.obj[i].direction=="right" && em.obj[i].worldX>=em.maxScreenHoriz*em.resTileSize) {
                    em.obj[i]=null;
                    }
                    else if (em.obj[i].direction=="left" && em.obj[i].worldX<-em.resTileSize) {
                        em.obj[i]=null;
                    }
            }
        }
    }
    
}
