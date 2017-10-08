/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hackupstategame;

/**
 *
 * @author ken
 */
public interface Sprite {
    public int width();
    public int height();
    
    public void draw(GameEngine g, int toX, int toY, int fromX, int fromY, int width, int height);
}
