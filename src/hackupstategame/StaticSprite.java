/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hackupstategame;


import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author ken
 */
public class StaticSprite implements Sprite {
    
    private final Image img;
    private final int width;
    private final int height;
    
    public StaticSprite(String ref) {
        BufferedImage sourceImg = null;
        Image img;
        int width;
        int height;
        
        try {
            URL url = this.getClass().getClassLoader().getResource(ref);
            sourceImg = ImageIO.read(url);
            
            width = sourceImg.getWidth();
            height = sourceImg.getHeight();
            
            GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
            img = gc.createCompatibleImage(width, height, Transparency.BITMASK);
            img.getGraphics().drawImage(sourceImg, 0, 0, null);
        } catch(Exception e) {
            img = null;
            width = 0;
            height = 0;
        }
        
        this.img = img;
        this.width = width;
        this.height = height;
    }
    
    public int width() {
        return width;
    }
    
    public int height() {
        return height;
    }
    
    public void draw(GameEngine g, int toX, int toY, int fromX, int fromY, int width, int height) {
        g.graphics.drawImage(img,
                toX, toY, toX + width, toY + height,
                fromX, fromY, fromX + width, fromY + height,
                null);
    }
}
