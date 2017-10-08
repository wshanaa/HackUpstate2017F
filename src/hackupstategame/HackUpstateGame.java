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
public class HackUpstateGame {
    
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        
        GameEngine game = new GameEngine(GAME_WIDTH, GAME_HEIGHT, "Hack Upstate!");
        game.addScene(new TestScene());
        game.run();
    }
    
}
