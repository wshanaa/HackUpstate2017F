/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hackupstategame;


import java.awt.event.KeyEvent;

/**
 *
 * @author ken
 */
public class TestScene extends ScrollingScene {
    
    private final Entity playerEnt;
    private final Entity npcEnt;
    
    public TestScene() {
        super(new StaticSprite("background.png"));
        playerEnt = new SimpleEntity(new StaticSprite("player.png"));
        npcEnt = new SimpleEntity(new StaticSprite("npc.png"));
        entities.add(playerEnt);
        entities.add(npcEnt);
        setProtagonist(playerEnt);
        playerEnt.x = 100;
        playerEnt.y = 300;
        npcEnt.x = 1500;
        npcEnt.y = 200;
    }
    
    public void activate(GameEngine g) {
    }
    
    public void deactivate(GameEngine g) {
    }
    
    public void update(GameEngine g) {
        if(g.keyState[KeyEvent.VK_UP]) {
            playerEnt.y -= 3;
        }
        if(g.keyState[KeyEvent.VK_DOWN]) {
            playerEnt.y += 3;
        }
        if(g.keyState[KeyEvent.VK_LEFT]) {
            playerEnt.x -= 3;
        }
        if(g.keyState[KeyEvent.VK_RIGHT]) {
            playerEnt.x += 3;
        }
        
        drawScene(g);
    }
}
