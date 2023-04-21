package levels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class LevelManager {

    public static void render(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 2000, 2000);
    }
}