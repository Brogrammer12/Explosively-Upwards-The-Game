package main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class mouseListener implements MouseMotionListener, MouseListener{
    everythingManager em;
    public int mouseX;
    public int mouseY;
    public boolean mouseMode=true;
    public boolean mouseClicked=false;
    public boolean rightClicked=false;
    public Rectangle mouseRect;
    public mouseListener(everythingManager em) {
        this.em=em;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int button=e.getButton();
        if (mouseMode==true) {
            if (button==MouseEvent.BUTTON1) {
            mouseClicked=true;
        }
        /*else if (button==MouseEvent.BUTTON3) {
            if (em.p1.Move==false && em.k.hasPressed==false && em.paused==false) {
                em.p1.Move=true;
                rightClicked=true;
                em.k.hasPressed=true;
            }
            else if(em.p1.Move==true && em.k.hasPressed==false && em.paused==false) {
                em.p1.Move=false;
                rightClicked=true;
                em.k.hasPressed=true;
            }
        }*/
        else if(button==MouseEvent.BUTTON2) {
            //em.p1.event=true;
            em.p1.boom=true;
            em.k.hasPressed=true;
        }
        if (button==MouseEvent.BUTTON3) {
            rightClicked=true;
        }
        /*if (mouseX>em.screenWidth/2 && mouseY>em.screenHeight/2 && em.p1.aim==true) {
                System.out.println("right down");
            }
            else if (mouseX>em.screenWidth/2 && mouseY<em.screenHeight/2 && em.p1.aim==true) {
                System.out.println("right up");
            }
            else if (mouseX<em.screenWidth/2 && mouseY<em.screenHeight/2 && em.p1.aim==true) {
                System.out.println("left up");
            }
            else if (mouseX<em.screenWidth/2 && mouseY>em.screenHeight/2 && em.p1.aim==true) {
                System.out.println("left down");
            }*/
        }
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (mouseClicked==true) {
            mouseClicked=false;
        }
        else {
            rightClicked=false;
        }
        em.k.hasPressed=false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX=e.getX();
        mouseY=e.getY();
        if (mouseMode==true) {
            if (em.paused==false) {
                if (mouseX>em.screenWidth/2) {
                em.p1.direction="right";
                //System.out.println("right");
            }
            else {
                em.p1.direction="left";
                //System.out.println("left");
            }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX=e.getX();
        mouseY=e.getY();
        if (mouseMode==true) {
            if (em.paused==false) {
                if (mouseX>em.screenWidth/2) {
                em.p1.direction="right";
                //System.out.println("right");
            }
            else {
                em.p1.direction="left";
                //System.out.println("left");
            }
            }
        }
    }

}
