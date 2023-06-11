package entities;

import java.awt.Graphics;

public interface InterfaceEntity {
    public void update();
    public void render(Graphics g);
    public void loadAnimation();
}