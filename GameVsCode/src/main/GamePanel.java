package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Inputs.KeyboardInputs;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;

//JPanel pode ser uma variavel de classe como feito no GameWindow
//JPanel e como um painel de uma pintura, onde la se desenha tudo
public class GamePanel extends JPanel{

	private Game game;

	//e no JPanel que se coloca os inputs
	public GamePanel(Game game) {
		this.game = game;

		setPanelSize();

		addKeyListener(new KeyboardInputs(this));
	}

	private void setPanelSize(){
		Dimension size = new Dimension(1280, 800);
		setPreferredSize(size);
	}
	
	//Graphics e algo q permite desenhar/colocar imagem no jpanel
	//apenas mudar a "pintura"
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

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