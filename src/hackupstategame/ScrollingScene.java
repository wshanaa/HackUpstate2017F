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
public abstract class ScrollingScene extends Scene {
    
    private final Sprite background;
    private Entity protagonist;
    
    public ScrollingScene(Sprite background) {
        this.background = background;
    }
    
    protected void setProtagonist(Entity entity) {
        protagonist = entity;
    }
    
    protected void drawScene(GameEngine g) {
        // Compute viewport
        int viewLeft = Math.max(0, protagonist.x + (protagonist.getDrawRectangle().width - g.width) / 2);
        int viewRight = Math.min(viewLeft + g.width, background.width());
        if(viewRight == background.width()) { viewLeft = viewRight - g.width; }
        int viewTop = Math.max(0, protagonist.y - g.height / 2);
        int viewBottom = Math.min(viewTop + g.height, background.height());
        if(viewBottom == background.height()) { viewTop = viewBottom - g.height; }
        
        background.draw(g, 0, 0, viewLeft, viewTop, g.width, g.height);
        
        // Draw entities in the viewport
        Rectangle viewRect = new Rectangle(viewLeft, viewTop, g.width, g.height);
        for(Entity ent : entities) {
            Rectangle drawRect = ent.getDrawRectangle();
            Rectangle intersect = drawRect.intersection(viewRect);
            if(!intersect.isEmpty()) {
                ent.draw(g,
                        intersect.x - viewLeft,
                        intersect.y - viewTop,
                        drawRect.x != intersect.x ? drawRect.width - intersect.width : 0,
                        drawRect.y != intersect.y ? drawRect.height - intersect.height : 0,
                        intersect.width,
                        intersect.height);
            }
        }
    }
}
