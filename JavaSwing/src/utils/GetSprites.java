package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GetSprites {
    public static final String PLAYER = "GameVsCode/res/player_sprites.png";
    public static final String ASTEROID = "GameVsCode/res/asteroid_sprite.png";

    public static BufferedImage getSpriteAtlas(String fileString){
        BufferedImage sheet = null;
        File file = new File(fileString);
        try
        {
            sheet = ImageIO.read(file);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return sheet;
    }
}