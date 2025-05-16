package main;

import javax.swing.JFrame;
import java.awt.*;
public class App {
    public static void main(String[] args) {
        JFrame window=new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setResizable(false);
        window.setTitle("Explosively Upwards");
        everythingManager window2=new everythingManager();
        window.add(window2);
        window.pack();
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth=screenSize.width;
        int screenHeight=screenSize.height;
        int width=window.getWidth();
        int height=window.getHeight();
        int x=(screenWidth-width)/2;
        int y=(screenHeight-height)/2;
        window.setLocation(x, y);
        window2.startGameThread();
    }
}