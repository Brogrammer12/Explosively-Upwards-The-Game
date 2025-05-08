package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class keyManager implements KeyListener{
public boolean leftPressed, rightPressed, enterPressed, downPressed, upPressed;
public boolean hasPressed;
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
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode=e.getKeyCode();
        if (keyCode==KeyEvent.VK_A) {
            leftPressed=false;
        }
        else if(keyCode==KeyEvent.VK_D) {
            rightPressed=false;
        }
        else if(keyCode==KeyEvent.VK_S) {
            downPressed=false;
        }
        else if(keyCode==KeyEvent.VK_W) {
            upPressed=false;
        }
        if (keyCode==KeyEvent.VK_ENTER) {
            enterPressed=false;
            hasPressed=false;
        }
    }

}
