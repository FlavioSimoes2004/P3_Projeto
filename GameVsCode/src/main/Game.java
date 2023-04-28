package main;

import java.awt.Graphics;

import entities.*;

import static utils.Constants.SETS.*;
import static utils.Constants.PlayerConstants.*;

import Score.Score;

public class Game implements Runnable{
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;

	private Player player;
	private Asteroid[] asteroids;
	public Score score;
	
	public Game() {
		initClasses();
		
		//Primeiro cria a pintura para depois colocar a moldura
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);		

		//para o programa identificar o que fazer com o input ele precisa de um requestFocus, se n
		//ele nao sabe o que fazer com o input
		gamePanel.addInputsWindow();
		gamePanel.requestFocus();
		
		startGameLoop();
	}

	private void initClasses(){
		player = new Player(this, 175, 250, 192, 126);
		asteroids = new Asteroid[2];
		for(int i = 0; i < asteroids.length; i++)
		{
			asteroids[i] = new Asteroid(this, 1500, 300 / (i + 1), 192, 192);
		}
		score = new Score();
	}

	public void startGameLoop(){
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void update(){
		player.update();
		player.playerGotOutFromWindow();
		
		if(player.playerState != DEAD)
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
		for(int i = 0; i < asteroids.length; i++)
		{
			asteroids[i].render(g);
		}
		player.render(g);
		score.render(g);
	}

	@Override
	public void run() {
		double timePerFrame = 1000000000.0 / (FPS_SET);
		double timePerUpdate = 1000000000.0 / (UPS_SET);

		long previousTime = System.nanoTime();
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
				deltaU--;
			}

			if(deltaF >= 1)
			{
				gamePanel.repaint();
				deltaF--;
			}

			if(System.currentTimeMillis() - lastCheck >= 1000)
			{
				lastCheck = System.currentTimeMillis();
			}
		}
	}

	public Player getPlayer(){
		return player;
	}

	public GameWindow getWindow(){
		return gameWindow;
	}
}