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
    public String realFile="/resources/maps/map1.txt";
    public tileManager(everythingManager em) {
        this.em=em;
        newMap(realFile);
        mapTileNum=new int[em.maxWorldHoriz][em.maxWorldVert];
        tile=new tile[10];
        tileLoader();
        loadMap(realFile);
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
            tile[4]=new tile();
            tile[4].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallLeft.png"));
            tile[4].collision=true;
            tile[4].grounded=false;
            tile[5]=new tile();
            tile[5].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallRight.png"));
            tile[5].collision=true;
            tile[5].grounded=false;
            tile[6]=new tile();
            tile[6].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallLeftCracked.png"));
            tile[6].collision=true;
            tile[6].grounded=false;
            tile[6].destructible=true;
            tile[7]=new tile();
            tile[7].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallRightCracked.png"));
            tile[7].collision=true;
            tile[7].grounded=false;
            tile[7].destructible=true;
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
    public void newMap(String fileName) {
        try {
            int numCol = 0;
            int numRow = 0;
    
            // Read the map file to determine the number of columns and rows
            InputStream is = getClass().getResourceAsStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                String numbers[] = line.split(" ");
                numRow++;
                numCol = numbers.length;
            }
            br.close();
    
            // Load the map data into a new array
            is = getClass().getResourceAsStream(fileName);
            br = new BufferedReader(new InputStreamReader(is));
            int[][] newMapTileNum = new int[numCol][numRow];
            int row = 0;
            while ((line = br.readLine()) != null) {
                String numbers[] = line.split(" ");
                for (int col = 0; col < numCol; col++) {
                    newMapTileNum[col][row] = Integer.parseInt(numbers[col]);
                }
                row++;
            }
            br.close();
    
            if (numCol > 0 && numRow > 0) {
                realFile = fileName;
                em.maxWorldVert = numRow;
                em.maxWorldHoriz = numCol;
    
                // Update the mapTileNum array dimensions and copy data
                mapTileNum = new int[numCol][numRow];
                for (int i = 0; i < numCol; i++) {
                    for (int j = 0; j < numRow; j++) {
                        mapTileNum[i][j] = newMapTileNum[i][j];
                    }
                }
            }
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
