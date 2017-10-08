/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hackupstategame;


import java.awt.Rectangle;

/**
 *
 * @author ken
 */
public class SimpleEntity extends Entity {
    
    private final Sprite sprite;
    
    public SimpleEntity(Sprite sprite) {
        this.sprite = sprite;
    }
    
    public void draw(GameEngine g, int toX, int toY, int fromX, int fromY, int width, int height) {
        sprite.draw(g, toX, toY, fromX, fromY, width, height);
    }
    
    public Rectangle getDrawRectangle() {
        return new Rectangle(x, y, sprite.width(), sprite.height());
    }
}
