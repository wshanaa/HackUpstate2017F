/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hackupstategame;


import java.util.ArrayList;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ken
 */
public class GameEngine {
    
    private class GameCanvas extends Canvas {
        private final BufferStrategy strategy;
        
        public GameCanvas(int width, int height, String title) {
            JFrame container = new JFrame(title);
            JPanel panel = (JPanel) container.getContentPane();
            panel.setPreferredSize(new Dimension(width, height));
            panel.setLayout(null);
            
            setBounds(0, 0, width, height);
            panel.add(this);
            
            setIgnoreRepaint(true);
            
            container.pack();
            container.setResizable(false);
            container.setVisible(true);
            
            container.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
            
            addKeyListener(new KeyInputHandler());
            requestFocus();
            
            createBufferStrategy(2);
            strategy = getBufferStrategy();
        }
        
        public Graphics2D graphics() {
            return (Graphics2D) strategy.getDrawGraphics();
        }
        
        public void flipBuffers() {
            strategy.show();
        }
    }
    
    private class KeyInputHandler extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if(keyCode >= 0 && keyCode < keyState.length) {
                keyState[keyCode] = true;
            }
        }
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if(keyCode >= 0 && keyCode < keyState.length) {
                keyState[keyCode] = false;
            }
        }
    }
    
    public static final int framePeriod = 32;
    public final int width;
    public final int height;
    public long now;
    public Graphics2D graphics;
    public boolean[] keyState;
    
    private final GameCanvas canvas;
    private final ArrayList<Scene> scenes;
    private Scene activeScene;
    private int currentSceneIdx;
    private int newSceneIdx;
    
    public GameEngine(int width, int height, String title) {
        this.width = width;
        this.height = height;
        canvas = new GameCanvas(width, height, title);
        scenes = new ArrayList<>();
        currentSceneIdx = 0;
        newSceneIdx = 0;
        
        keyState = new boolean[256];
        for(int i = 0; i < keyState.length; i++) {
            keyState[i] = false;
        }
    }
    
    public void addScene(Scene scene) {
        scenes.add(scene);
    }
    
    public void switchScenes(int sceneIndex) {
        newSceneIdx = sceneIndex;
    }
    
    public void run() {
        long lastTime = System.currentTimeMillis();
        
        currentSceneIdx = newSceneIdx;
        activeScene = scenes.get(currentSceneIdx);
        activeScene.activate(this);
        
        while(true) {
            now = System.currentTimeMillis();
            lastTime = now;
            
            if(currentSceneIdx != newSceneIdx) {
                currentSceneIdx = newSceneIdx;
                activeScene.deactivate(this);
                activeScene = scenes.get(currentSceneIdx);
                activeScene.activate(this);
            }
            
            graphics = canvas.graphics();
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, width, height);
            
            activeScene.update(this);
            
            graphics.dispose();
            canvas.flipBuffers();
            
            long sleepTime = framePeriod - (System.currentTimeMillis() - now);
            if(sleepTime > 0) {
                try { Thread.sleep(sleepTime); } catch(Exception e) {}
            }
        }
    }
}
