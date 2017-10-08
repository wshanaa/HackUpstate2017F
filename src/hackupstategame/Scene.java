/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hackupstategame;


import java.util.LinkedList;

/**
 *
 * @author ken
 */
public abstract class Scene {
    public abstract void activate(GameEngine g);
    public abstract void deactivate(GameEngine g);
    public abstract void update(GameEngine g);
    
    protected abstract void drawScene(GameEngine g);
    
    protected final LinkedList<Entity> entities;
    
    public Scene() {
        entities = new LinkedList<>();
    }
}
