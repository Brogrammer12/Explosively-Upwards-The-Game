package Entities;

import main.everythingManager;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
public class reskinSystem {
    everythingManager em;
    public int[] [] pixels;
    public int[] ogPallete;
    public int[] desertPallete;
    public int[] clankerPallete;
    public int[] skinPallete;
    public reskinSystem(everythingManager em) {
        this.em=em;
        ogPallete=new int[11];
        desertPallete=new int[11];
        clankerPallete=new int[11];
        skinPallete=new int[11];
        loadPalletes();
    }
    public void loadPalletes() {
        ogPallete[0]=new Color(40, 40, 180, 255).getRGB();//outside jacket color
        ogPallete[1]=new Color(32, 32, 146, 255).getRGB();//inside jacket color
        ogPallete[2]=new Color(209, 207, 84, 255).getRGB();//yellow dot on jacket
        ogPallete[3]=new Color(26, 26, 118, 255).getRGB();//darker inside jacket part
        ogPallete[4]=new Color(198, 54, 29, 255).getRGB();//t-shirt color
        ogPallete[5]=new Color(165, 165, 165, 255).getRGB();//t shirt design color
        ogPallete[6]=new Color(29, 29, 104, 255).getRGB();//outside pants color
        ogPallete[7]=new Color(17, 17, 74, 255).getRGB();//inner pants color
        ogPallete[8]=new Color(255, 255, 255, 255).getRGB();//white dot on pants
        ogPallete[9]=new Color(134, 92, 28, 255).getRGB();//main gun color
        ogPallete[10]=new Color(129, 79, 5, 255).getRGB();//alt gun color


        desertPallete[0]=new Color(82, 76, 76, 255).getRGB();//outside jacket color
        desertPallete[1]=new Color(67, 61, 61, 255).getRGB();//inside jacket color
        desertPallete[2]=new Color(209, 207, 84, 255).getRGB();//yellow dot on jacket
        desertPallete[3]=new Color(48, 44, 44, 255).getRGB();//darker inside jacket part
        desertPallete[4]=new Color(162, 162, 162, 255).getRGB();//t-shirt color
        desertPallete[5]=new Color(119, 21, 21, 255).getRGB();//t shirt design color
        desertPallete[6]=new Color(59, 56, 56, 255).getRGB();//outside pants color
        desertPallete[7]=new Color(37, 33, 33, 255).getRGB();//inner pants color
        desertPallete[8]=new Color(255, 255, 255, 255).getRGB();//white dot on pants
        desertPallete[9]=new Color(134, 92, 28, 255).getRGB();//main gun color
        desertPallete[10]=new Color(54, 52, 47, 255).getRGB();//alt gun color

        clankerPallete[0]=new Color(165, 165, 166, 255).getRGB();//outside jacket color
        clankerPallete[1]=new Color(109, 109, 109, 255).getRGB();//inside jacket color
        clankerPallete[2]=new Color(168, 17, 17, 255).getRGB();//yellow dot on jacket
        clankerPallete[3]=new Color(0, 206, 13, 255).getRGB();//darker inside jacket part
        clankerPallete[4]=new Color(0, 0, 0, 255).getRGB();//t-shirt color
        clankerPallete[5]=new Color(121, 0, 142, 255).getRGB();//t shirt design color
        clankerPallete[6]=new Color(59, 59, 59, 255).getRGB();//outside pants color
        clankerPallete[7]=new Color(83, 83, 83, 255).getRGB();//inner pants color
        clankerPallete[8]=new Color(187, 78, 0, 255).getRGB();//white dot on pants
        clankerPallete[9]=new Color(104, 108, 0, 255).getRGB();//main gun color
        clankerPallete[10]=new Color(218, 7, 7, 255).getRGB();//alt gun color
    }
    public BufferedImage ReskinPlayer(BufferedImage playerImage, int skinIndex) {
        if (skinIndex==1) {
            for (int i=0; i<ogPallete.length; i++) {
                skinPallete[i]=desertPallete[i];
            }
        }
        else {
            for (int i=0; i<ogPallete.length; i++) {
                skinPallete[i]=clankerPallete[i];
            }
        }
        if (skinIndex!=0) {
            pixels=new int[playerImage.getHeight()] [playerImage.getWidth()];
        for (int i=0; i<playerImage.getHeight(); i++) {
            for (int e=0; e<playerImage.getWidth(); e++) {
                pixels [i] [e]=playerImage.getRGB(e, i);
            }
        }

        for (int i=0; i<playerImage.getHeight(); i++) {
            for (int e=0; e<playerImage.getWidth(); e++) {
                for (int a=0; a<ogPallete.length; a++) {
                    if (pixels[i] [e]==ogPallete[a]) {
                        pixels[i] [e]=skinPallete[a];
                        playerImage.setRGB(e, i, pixels[i] [e]);
                    }
                }
            }
        }
        }
        return playerImage;
    }
}
