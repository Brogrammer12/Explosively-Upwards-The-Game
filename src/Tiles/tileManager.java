package Tiles;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import java.io.File;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import Objects.bombBoi;
import main.everythingManager;

public class tileManager {
    everythingManager em;
    public tile[] tile;
    public int mapTileNum[][];
    public String realFile="level 1.tmj";
    public tileManager(everythingManager em) {
        this.em=em;
        newMap(realFile);
        //mapTileNum=new int[em.maxWorldHoriz][em.maxWorldVert];
        tile=new tile[20];
        tileLoader();
    }
    public void tileLoader() {
        try {
            tile[0]=new tile();
            tile[0].image=null;
            tile[2]=new tile();
            tile[2].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/leftTile.png"));
            tile[2].collision=true;
            tile[4]=new tile();
            tile[4].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/middleTile.png"));
            tile[4].collision=true;
            tile[6]=new tile();
            tile[6].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/rightTile.png"));
            tile[6].collision=true;
            tile[8]=new tile();
            tile[8].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallLeft.png"));
            tile[8].collision=true;
            tile[8].grounded=false;
            tile[10]=new tile();
            tile[10].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallRight.png"));
            tile[10].collision=true;
            tile[10].grounded=false;
            tile[9]=new tile();
            tile[9].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallLeftCracked.png"));
            tile[9].collision=true;
            tile[9].grounded=false;
            tile[9].destructible=true;
            tile[11]=new tile();
            tile[11].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallRightCracked.png"));
            tile[11].collision=true;
            tile[11].grounded=false;
            tile[11].destructible=true;
           /*  tile[8]=new tile();
            tile[8].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/leftTileFactory.png"));
            tile[8].collision=true;
            tile[9]=new tile();
            tile[9].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/middleTileFactory.png"));
            tile[9].collision=true;
            tile[10]=new tile();
            tile[10].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/rightTileFactory.png"));
            tile[10].collision=true;*/
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void newMap(String fileName) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            JsonNode root=mapper.readTree(new File(fileName));
            JsonNode dataNode=root.get("layers").get(0).get("data");
            JsonNode heightNode=root.get("layers").get(0).get("height");
            JsonNode widthNode=root.get("layers").get(0).get("width");
            int[] data=mapper.readValue(dataNode.toString(), int[].class);
            int height=Integer.parseInt(heightNode.toString());
            int width=Integer.parseInt(widthNode.toString());
            mapTileNum=new int[width][height];
            em.maxWorldHoriz=width;
            em.maxWorldVert=height;
            for (int row=0; row<height; row++) {
                for (int col=0; col<width; col++) {
                    mapTileNum[col] [row]=data[row*width+col];
                }
            }
            for (int row=0; row<height; row++) {
                for (int col=0; col<width; col++) {
                    System.out.print(mapTileNum[col][row]+" ");
                }
                System.out.println();
            }
            //System.exit(0);
        } catch (Exception e) {
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
