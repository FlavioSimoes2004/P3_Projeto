package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import utils.LoadSave;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.SETS.*;

import main.Game;

import audio.AudioPlayer;

public class Player extends Entity{
    public final float playerSpeed = 4;

    private final int size = 192;
    private int animTick, animIndex;
	private int animSpeed = 30;
	public int playerState = ALIVE;
    private float startYpos;
    private boolean canFly;

    private final float startFallSpeed = 4;
    private final float maxFallSpeed = 15;
    private final float addFallSpeed = 2.25f;
    private float fallSpeed;

    public int deadAnim = 0;

    private Game game;
    private AudioPlayer audio;

    public boolean isFlying;

    public Player(Game game, float x, float y, int width, int height){
        super(x, y, width, height);
        this.game = game;
        audio = new AudioPlayer();
        startYpos = y;
        isFlying = false;
        canFly = true;
        fallSpeed = startFallSpeed;
        loadAnimation();
    }

    public void update(){
        if(playerState != DEAD)
        {
            updatePos();
            updateHitbox();
        }
        else
        {
            deadAnim += 1;
            if(deadAnim >= 360)
            {
                reset();
                audio.stopAudio("explosion");
            }
        }

        updateAnimationTick();
        //System.out.println(y);
    }

    public void render(Graphics g){
        g.drawImage(animation[playerState][animIndex], (int) x, (int) y, 192, 192, null);
        //drawHitbox(g);
    }

    public void loadAnimation(){
        sheet = LoadSave.getSpriteAtlas(LoadSave.PLAYER);

        animation = new BufferedImage[3][3];
        animation[0] = new BufferedImage[3];
        animation[1] = new BufferedImage[3];
        animation[2] = new BufferedImage[3];

        for(int linha = 0; linha < animation.length; linha++)
        {
            for(int coluna = 0; coluna < animation[linha].length; coluna++)
            {
                animation[linha][coluna] = sheet.getSubimage(coluna * size, linha * size, size, size);
            }
        }

    }

    //--------------------------------------




    private void updateAnimationTick(){
		animTick++;
		if(animTick >= animSpeed)
		{
			animTick = 0;
			animIndex++;

			//se passar do tamanho da array, a animacao volta pro index 0
			if(animIndex >= animation[playerState].length) //GetSpriteAmount(playerState)
			{
				animIndex = 0;
			}
		}
	}

	private void updatePos(){
		if(isFlying == true && canFly == true)
        {
            fallSpeed = startFallSpeed;
            y -= playerSpeed;
        }
        else if(isFlying == false && canFly == true)
        {
            falling();
        }
	}

    private void falling(){
        y += fallSpeed / 2;
        fallSpeed += addFallSpeed;
        if(fallSpeed > maxFallSpeed)
        {
            fallSpeed = maxFallSpeed;
        }
    }

    public void Died(){
        playerState = DEAD;
        canFly = false;
        game.score.reset();
        audio.playAudio("explosion");
    }

    public void reset(){
        playerState = ALIVE;
        canFly = true;
        y = startYpos;
        deadAnim = 0;
    }

    public void detectCollision(int asteX, int asteY){
        /*if(collX(asteX) == true)
        {
            if(collY(asteY) == true)
            {
                Died();
            }
        }*/

        int distance = getDistance(asteX, asteY);
        if(distance < 159)
        {
            Died();
        }
    }

    /*private boolean collX(int asteX){
        if(x == asteX)
        {
            return true;
        }
        else if(x > asteX)
        {
            if(x - asteX < 192)
            {
                return true;
            }
        }
        else
        {
            if(asteX - x < -192)
            {
                return true;
            }
        }

        return false;
    }

    private boolean collY(int asteY){
        if(y == asteY)
        {
            return true;
        }
        else if(y > asteY)
        {
            int minPosY = (int) y - 63;
            int asteYup = asteY - 96;
            if(minPosY >= asteYup && minPosY <= asteY)
            {
                return true;
            }
        } 
        else
        {
            int minPosY = (int) y + 63;
            int asteYdown = asteY + 96;
            if(minPosY >= asteY && minPosY <= asteYdown)
            {
                return true;
            }
        }

        return false;
    }*/

    public void playerGotOutFromWindow(){
        if(y <= -192 || y >= 1200)
        {
            y = 175;
            Died();
        }
    }

    public int getDistance(int asteX, int asteY){
        int result = (int) Math.pow(asteX - x, 2) + (int) Math.pow(asteY - y, 2);
        result = (int) Math.sqrt(result);
        result = Math.abs(result);

        return result;
    }
}