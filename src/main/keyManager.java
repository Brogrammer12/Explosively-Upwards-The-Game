package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class keyManager implements KeyListener{
public boolean leftPressed, rightPressed, enterPressed, downPressed, upPressed, rPressed, ePressed;
public boolean hasPressed;
public boolean menuHasPressed;
//public boolean sRightPressed=false;
//public boolean sUpPressed=false;
public boolean shiftPressed=false;
public boolean escPressed=false;
everythingManager em;
public keyManager(everythingManager em) {
this.em=em;
}
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode=e.getKeyCode();
        if (keyCode==KeyEvent.VK_A) {
            leftPressed=true;
        }
        else if(keyCode==KeyEvent.VK_D) {
            rightPressed=true;
        }
        else if(keyCode==KeyEvent.VK_S) {
            downPressed=true;
        }
        else if(keyCode==KeyEvent.VK_W) {
            upPressed=true;
        }
        if (keyCode==KeyEvent.VK_ENTER) {
            enterPressed=true;
        }
        if (keyCode==KeyEvent.VK_R) {
            rPressed=true;
        }
        if (keyCode==KeyEvent.VK_E) {
            ePressed=true;
        }
        if (keyCode==KeyEvent.VK_SPACE) {
            upPressed=true;
        }
        if (keyCode==KeyEvent.VK_SHIFT) {
            shiftPressed=true;
        }
        if (keyCode==KeyEvent.VK_ESCAPE) {
            escPressed=true;
        }
        if (keyCode==KeyEvent.VK_1) {
            //em.p1.Move=false;
            em.p1.bombType="explosive";
        }
        else if (keyCode==KeyEvent.VK_2) {
            //em.p1.Move=true;
            em.p1.bombType="stickyMove";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode=e.getKeyCode();
        if (keyCode==KeyEvent.VK_A) {
            leftPressed=false;
            menuHasPressed=false;
        }
        else if(keyCode==KeyEvent.VK_D) {
            rightPressed=false;
            menuHasPressed=false;
        }
        else if(keyCode==KeyEvent.VK_S) {
            downPressed=false;
            menuHasPressed=false;
        }
        else if(keyCode==KeyEvent.VK_W) {
            upPressed=false;
            menuHasPressed=false;
        }
        if (keyCode==KeyEvent.VK_ENTER) {
            enterPressed=false;
            hasPressed=false;
        }
        if (keyCode==KeyEvent.VK_R) {
            rPressed=false;
            hasPressed=false;
        }
        if (keyCode==KeyEvent.VK_E) {
            ePressed=false;
            hasPressed=false;
        }
        if (keyCode==KeyEvent.VK_SPACE) {
            upPressed=false;
            hasPressed=false;
        }
        if (keyCode==KeyEvent.VK_SHIFT) {
            shiftPressed=false;
        }
        if (keyCode==KeyEvent.VK_ESCAPE) {
            escPressed=false;
            hasPressed=false;
        }
    }

}
