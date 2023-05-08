package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Inputs.KeyboardInputsGame;
import Inputs.KeyboardInputsJFrame;

//JPanel pode ser uma variavel de classe como feito no GameWindow
//JPanel e como um painel de uma pintura, onde la se desenha tudo
public class GamePanel extends JPanel{

	private Game game;
	private int width, height;

	//e no JPanel que se coloca os inputs
	public GamePanel(Game game) {
		this.game = game;

		setPanelSize();

		addKeyListener(new KeyboardInputsGame(this));
	}

	public void addInputsWindow(){
		JFrame jframe = game.getWindow().getFrame();
		width = jframe.getWidth();
		height = jframe.getHeight();
		addKeyListener(new KeyboardInputsJFrame(jframe));
	}

	private void setPanelSize(){
		Dimension size = new Dimension(1280, 800);
		setPreferredSize(size);
	}
	
	//Graphics e algo q permite desenhar/colocar imagem no jpanel
	//apenas mudar a "pintura"
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width * 2, height * 2);
		g.setColor(null);
		game.render(g);
	}

	//mudar animacao, posicao, eventos
	public void updateGame(){
		game.update();
	}

	public Game getGame(){
		return game;
	}
}