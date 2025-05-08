package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfigTemplate;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Entities.Player;
import Objects.object;
import Tiles.BackgroundDrawer;
import Tiles.tile;
import Tiles.tileManager;

public class everythingManager extends JPanel implements Runnable{
    public final int ogTileSize=16;
    public final int scale=3;
    public final int resTileSize=ogTileSize*scale;
    public final int maxScreenHoriz=20;
    public final int maxScreenVert=12;
    public final int screenWidth=resTileSize*maxScreenHoriz;
    public final int screenHeight=resTileSize*maxScreenVert;
    public int maxWorldHoriz=20;
    public int maxWorldVert=12;
    public int worldWidth=maxWorldHoriz*resTileSize;
    public int worldHeight=maxWorldVert*resTileSize;
    public final int FPS=60;
    public tileManager tileM=new tileManager(this);
    public CollisionChecker cChecker=new CollisionChecker(this);
    public object[] obj=new object[10];
    public BufferedImage[] backgrounds=new BufferedImage[10];
    public BackgroundDrawer bDrawer=new BackgroundDrawer(this);
    public instantiator instantiate=new instantiator(this);
    Thread thread;
    public keyManager k=new keyManager();
    public Player p1=new Player(this);
    public everythingManager() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(k);
    }
    public void startGameThread() {
        thread=new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        double drawInterval=1000000000/FPS;
        double nextDrawTime=System.nanoTime()+drawInterval;
        while (thread!=null) {
            update();
            repaint();
            try {
                double remainingTime=nextDrawTime-System.nanoTime();
                remainingTime/=1000000;
                if(remainingTime<0) {
                    remainingTime=0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime+=drawInterval;
            } catch (InterruptedException e) {
                
                e.printStackTrace();
            }
            
        }
    }
    public void update() {
        p1.update();
        instantiate.setObject();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        bDrawer.drawBackground(backgrounds[0], g2);
        tileM.draw(g2);
        p1.draw(g2);
        for (int i=0; i<obj.length; i++) {
            if (obj[i]!=null) {
                obj[i].objFunction(g2);
            }
        }
        g2.dispose();
    }

}
