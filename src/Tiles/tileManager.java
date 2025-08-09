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
        tile=new tile[40];
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
            tile[3]=new tile();
            tile[3].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/leftTileFactory.png"));
            tile[3].collision=true;
            tile[5]=new tile();
            tile[5].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/middleTileFactory.png"));
            tile[5].collision=true;
            tile[7]=new tile();
            tile[7].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/rightTileFactory.png"));
            tile[7].collision=true;
            tile[12]=new tile();
            tile[12].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/leftTileGrand.png"));
            tile[12].collision=true;
            tile[13]=new tile();
            tile[13].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/middleTileGrand.png"));
            tile[13].collision=true;
            tile[14]=new tile();
            tile[14].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/rightTileGrand.png"));
            tile[14].collision=true;
            tile[15]=new tile();
            tile[15].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallLeftCrackedGrand.png"));
            tile[15].collision=true;
            tile[15].grounded=false;
            tile[15].destructible=true;
            tile[16]=new tile();
            tile[16].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallLeftGrand.png"));
            tile[16].collision=true;
            tile[16].grounded=false;
            tile[17]=new tile();
            tile[17].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallRightCrackedGrand.png"));
            tile[17].collision=true;
            tile[17].grounded=false;
            tile[17].destructible=true;
            tile[18]=new tile();
            tile[18].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallRightGrand.png"));
            tile[18].collision=true;
            tile[18].grounded=false;
            tile[19]=new tile();
            tile[19].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/lavaLeftTile.png"));
            tile[19].collision=true;
            tile[20]=new tile();
            tile[20].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/lavaMiddleTile.png"));
            tile[20].collision=true;
            tile[21]=new tile();
            tile[21].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/lavaRightTile.png"));
            tile[21].collision=true;
            tile[22]=new tile();
            tile[22].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallRightCrackedLava.png"));
            tile[22].collision=true;
            tile[22].grounded=false;
            tile[22].destructible=true;
            tile[23]=new tile();
            tile[23].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallLeftCrackedLava.png"));
            tile[23].collision=true;
            tile[23].grounded=false;
            tile[23].destructible=true;
            tile[24]=new tile();
            tile[24].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallRightLava.png"));
            tile[24].collision=true;
            tile[24].grounded=false;
            tile[25]=new tile();
            tile[25].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallLeftLava.png"));
            tile[25].collision=true;
            tile[25].grounded=false;
            tile[26]=new tile();
            tile[26].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallRightOceanCracked.png"));
            tile[26].collision=true;
            tile[26].grounded=false;
            tile[26].destructible=true;
            tile[27]=new tile();
            tile[27].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallLeftOceanCracked.png"));
            tile[27].collision=true;
            tile[27].grounded=false;
            tile[27].destructible=true;
            tile[28]=new tile();
            tile[28].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallRightOcean.png"));
            tile[28].collision=true;
            tile[28].grounded=false;
            tile[29]=new tile();
            tile[29].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallLeftOcean.png"));
            tile[29].collision=true;
            tile[29].grounded=false;
            tile[30]=new tile();
            tile[30].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/oceanRightTile.png"));
            tile[30].collision=true;
            tile[31]=new tile();
            tile[31].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/oceanLeftTile.png"));
            tile[31].collision=true;
            tile[32]=new tile();
            tile[32].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/oceanMiddleTile.png"));
            tile[32].collision=true;
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
            em.worldWidth=em.maxWorldHoriz*em.resTileSize;
            em.worldHeight=em.maxWorldVert*em.maxWorldVert;
            for (int row=0; row<height; row++) {
                for (int col=0; col<width; col++) {
                    mapTileNum[col] [row]=data[row*width+col];
                }
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
