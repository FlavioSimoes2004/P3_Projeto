package main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

//JPanel pode ser uma variavel de classe como feito no GameWindow
//JPanel e como um painel de uma pintura, onde la se desenha tudo
public class GamePanel extends JPanel{

	private float xDelta, yDelta;
	private float xDir, yDir;
	private MouseInputs mouseInputs;
	private int frames;
	private long lastCheck;

	//e no JPanel que se coloca os inputs
	public GamePanel() {
		frames = 0;
		lastCheck = 0;

		mouseInputs = new MouseInputs();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);

		xDelta = 50; yDelta = 100;
		xDir = 0.1f; yDir = 0.1f;
	}

	public void changeX(float value){
		xDelta += value;
	}

	public void changeY(float value){
		yDelta += value;
	}
	
	//Graphics e algo q permite desenhar/colocar imagem no jpanel
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		updateRectangle();
		g.fillRect((int) xDelta, (int) yDelta, 200, 50);
	}

	public void updateRectangle(){
		xDelta += xDir;
		if(xDelta > 640 || xDelta < 0)
		{
			xDir *= -1;
		}

		yDelta += yDir;
		if(yDelta > 480 || yDelta < 0)
		{
			yDir *= -1;
		}
	}
}