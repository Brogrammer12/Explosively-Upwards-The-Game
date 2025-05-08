package Tiles;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Objects.bombBoi;
import main.everythingManager;

public class tileManager {
    everythingManager em;
    public tile[] tile;
    public int mapTileNum[][];
    public tileManager(everythingManager em) {
        this.em=em;
        mapTileNum=new int[em.maxWorldHoriz][em.maxWorldVert];
        tile=new tile[10];
        tileLoader();
        loadMap("/resources/maps/map1.txt");
    }
    public void tileLoader() {
        try {
            tile[0]=new tile();
            tile[0].image=null;
            tile[1]=new tile();
            tile[1].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/leftTile.png"));
            tile[1].collision=true;
            tile[2]=new tile();
            tile[2].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/middleTile.png"));
            tile[2].collision=true;
            tile[3]=new tile();
            tile[3].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/rightTile.png"));
            tile[3].collision=true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath) {
        try {
            InputStream is=getClass().getResourceAsStream(filePath);
        BufferedReader br=new BufferedReader(new InputStreamReader(is));
        int col=0;
        int row=0;
        while (col<em.maxWorldHoriz && row<em.maxWorldVert) {
                String line=br.readLine();
                while (col<em.maxWorldHoriz) {
                    String[] numbers=line.split(" ");
                    int num=Integer.parseInt(numbers[col]);
                    mapTileNum[col] [row]=num;
                    col++;
                }
                if (col==em.maxWorldHoriz) {
                    col=0;
                    row++;
                }
        }
        br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        int col=0;
        int row=0;
        while (col<em.maxWorldHoriz && row<em.maxWorldVert) {
            int tileNum=mapTileNum[col] [row];
            int worldX=col*em.resTileSize;
            int worldY=row*em.resTileSize;
            int screenX=(int) (worldX-em.p1.worldX+em.p1.screenX);
            int screenY=(int) (worldY-em.p1.worldY+em.p1.screenY);
            g2.drawImage(tile[tileNum].image, screenX, screenY, em.resTileSize, em.resTileSize, null);
            col++;
            if (col==em.maxWorldHoriz) {
                col=0;
                row++;
            }
        }
    }
}
