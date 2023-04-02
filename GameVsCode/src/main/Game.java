package main;

public class Game implements Runnable{
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int fps_set = 120;
	
	public Game() {
		//Primeiro cria a pintura para depois colocar a moldura
		gamePanel = new GamePanel();
		gameWindow = new GameWindow(gamePanel);
		
		//para o programa identificar o que fazer com o input ele precisa de um requestFocus, se n
		//ele nao sabe o que fazer com o input
		gamePanel.requestFocus();

		startGameLoop();
	}

	public void startGameLoop(){
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double timePerFrame = 1000000000.0 / fps_set;
		long lastFrame = System.nanoTime();
		long actualFrame = System.nanoTime();
		int frames = 0;
		long lastCheck = System.currentTimeMillis();

		while(true)
		{
			actualFrame = System.nanoTime();
			if(actualFrame - lastFrame >= timePerFrame)
			{
				gamePanel.repaint();
				lastFrame = actualFrame;
				frames++;
			}

			if(System.currentTimeMillis() - lastCheck >= 1000)
			{
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS = " + frames);
				frames = 0;
			}
		}
	}
}