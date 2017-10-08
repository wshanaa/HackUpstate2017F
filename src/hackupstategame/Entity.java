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
public abstract class Entity {
    
    public int x;
    public int y;
    
    public abstract void draw(GameEngine g, int toX, int toY, int fromX, int fromY, int width, int height);
    
    public abstract Rectangle getDrawRectangle();
}
