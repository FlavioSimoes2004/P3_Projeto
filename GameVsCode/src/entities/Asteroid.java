package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import utils.LoadSave;

import main.Game;

public class Asteroid extends Entity{
    private final int size = 192;
    private final float divisorSize = 1.25f;

    private int animTick = 0, animIndex = 0;
    private int animSpeed = 30;
    private Random random;
    private Game game;

    private float speed = 5f;

    public Asteroid(Game game, float x, float y, int width, int height){
        super(x, y, width, height);
        this.game = game;
        loadAnimation();
        random = new Random();
    }

    public void update(){
        changePos();
        updateHitbox();
        updateAnimationTick();
    }

    public void render(Graphics g){
        g.drawImage(animation[0][animIndex], (int) x, (int) y, (int) (size / divisorSize), (int) (size / divisorSize), null);
        //drawHitbox(g);
    }

    public void loadAnimation(){
        sheet = LoadSave.getSpriteAtlas(LoadSave.ASTEROID);

        animation = new BufferedImage[1][3];
        animation[0] = new BufferedImage[3];

        for(int linha = 0; linha < animation.length; linha++)
        {
            for(int coluna = 0; coluna < animation[linha].length; coluna++)
            {
                animation[linha][coluna] = sheet.getSubimage(coluna * size, linha * size, size, size);
            }
        }
    }

    //-----------------------------------------------



    private void updateAnimationTick(){
		animTick++;
		if(animTick >= animSpeed)
		{
			animTick = 0;
			animIndex++;

			//se passar do tamanho da array, a animacao volta pro index 0
			if(animIndex >= animation[0].length) //GetSpriteAmount(playerState)
			{
				animIndex = 0;
			}
		}
	}

    public void changePos(){
        x -= speed;
        if(x <= 0)
        {
            game.score.increase();
            resetPos();
        }
    }

    public int[] getPos(){
        int[] pos = {(int) x, (int) y};
        return pos;
    }

    public void resetPos(){
        x = 1500;
        y = random.nextInt(750);
    }
}