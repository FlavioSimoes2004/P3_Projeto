package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Currency;

import javax.xml.transform.stax.StAXResult;

import entities.*;
import levels.LevelManager;

import static utils.Constants.SETS.*;
import static utils.Constants.PlayerConstants.*;

public class Game implements Runnable{
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;

	private Player player;
	private Asteroid[] asteroids;
	
	public Game() {
		initClasses();
		
		//Primeiro cria a pintura para depois colocar a moldura
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		

		//para o programa identificar o que fazer com o input ele precisa de um requestFocus, se n
		//ele nao sabe o que fazer com o input
		gamePanel.requestFocus();
		
		startGameLoop();
	}

	private void initClasses(){
		player = new Player(175, 250, 192, 126);
		asteroids = new Asteroid[2];
		for(int i = 0; i < asteroids.length; i++)
		{
			asteroids[i] = new Asteroid(1500, 300 / (i + 1), 192, 192);
		}
	}

	public void startGameLoop(){
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void update(){
		player.update();
		//player.playerGotOutFromWindow();
		
		if(player.playerState == ALIVE)
		{
			for(int i = 0; i < asteroids.length; i++)
			{
				asteroids[i].update();
				int[] pos = asteroids[i].getPos();
				player.detectCollision(pos[0], pos[1]);
			}
		}
		else
		{
			if(player.deadAnim == 1)
			{
				for(int i = 0; i < asteroids.length; i++)
				{
					asteroids[i].resetPos();
				}
			}
		}
	}

	public void render(Graphics g){
		LevelManager.render(g);
		for(int i = 0; i < asteroids.length; i++)
		{
			asteroids[i].render(g);
		}
		player.render(g);
	}

	@Override
	public void run() {
		double timePerFrame = 1000000000.0 / (FPS_SET);
		double timePerUpdate = 1000000000.0 / (UPS_SET);

		long previousTime = System.nanoTime();

		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();

		double deltaU = 0;
		double deltaF = 0;

		while(true)
		{
			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			if(deltaU >= 1)
			{
				update();
				updates++;
				deltaU--;
			}

			if(deltaF >= 1)
			{
				gamePanel.repaint();
				frames++;
				deltaF--;
			}

			if(System.currentTimeMillis() - lastCheck >= 1000)
			{
				lastCheck = System.currentTimeMillis();
				//System.out.println("FPS: " + frames + "| UPS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	public Player getPlayer(){
		return player;
	}






	public void windowLostFocus(){

	}

	public void windowGainedFocus(){

	}
}