package Entities;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
public class meleeRoman extends Entity{
    public BufferedImage right1, right2, left1, left2;
    public meleeRoman() {
        imageLoader();
    }
    public void imageLoader() {
        try {
            right1=ImageIO.read(getClass().getResourceAsStream("/resources/Enemies/meleeRoman/meleeRomanRight1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("/resources/Enemies/meleeRoman/meleeRomanRight2.png"));
            left1=ImageIO.read(getClass().getResourceAsStream("/resources/Enemies/meleeRoman/meleeRomanLeft1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("/resources/Enemies/meleeRoman/meleeRomanLeft2.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void update() {

    }
    public void draw() {

    }
}
