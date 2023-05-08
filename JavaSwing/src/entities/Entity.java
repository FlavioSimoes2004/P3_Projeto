package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Entity {
    protected float x, y;
    protected BufferedImage sheet;
    protected BufferedImage[][] animation;

    protected int width, height;
    protected Rectangle hitbox;

    public Entity(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        initHitbox();
    }

    public void initHitbox(){
        hitbox = new Rectangle((int) x, (int) y, width, height);
    }

    protected void updateHitbox(){
        hitbox.x = (int) x;
        hitbox.y = (int) y;
    }

    protected void drawHitbox(Graphics g){
        g.setColor(Color.WHITE);
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    public Rectangle getHitbox(){
        return hitbox;
    }
}