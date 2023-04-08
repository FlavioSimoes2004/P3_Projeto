package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;

//JPanel pode ser uma variavel de classe como feito no GameWindow
//JPanel e como um painel de uma pintura, onde la se desenha tudo
public class GamePanel extends JPanel{

	private float xDelta, yDelta;
	private float xDir, yDir;
	private MouseInputs mouseInputs;
	private BufferedImage spaceship;
	private BufferedImage[][] spaceshipAnim;
	
	//variaveis para a animacao rodar
	private int animTick, animIndex;
	//velocidade da animacao, para aumentar a velocidade diminuir o valor da variavel speed
	private int animSpeed = 30;
	private int playerAction = ALIVE;

	//e no JPanel que se coloca os inputs
	public GamePanel() {
		mouseInputs = new MouseInputs();
		importImg();
		loadAnimations();

		setPanelSize();

		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
	}

	private void importImg(){
		File ship = new File("res/player_sprites.png");

		try
		{
			//tem que estar dentro de um try-catch
			spaceship = ImageIO.read(ship);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	private void loadAnimations(){
		spaceshipAnim = new BufferedImage[2][3];

		for(int linha = 0; linha < spaceshipAnim.length; linha++)
		{
			for(int coluna = 0; coluna < spaceshipAnim[linha].length; coluna++)
			{
				spaceshipAnim[linha][coluna] = spaceship.getSubimage(coluna * 192, linha * 192, 192, 192);
			}
		}
	}

	private void setPanelSize(){
		Dimension size = new Dimension(1280, 800);
		setPreferredSize(size);
	}

	private void updateAnimationTick(){
		animTick++;
		if(animTick >= animSpeed)
		{
			animTick = 0;
			animIndex++;

			//se passar do tamanho da array, a animacao volta pro index 0
			if(animIndex >= GetSpriteAmount(playerAction))
			{
				animIndex = 0;
			}
		}
	}

	public void setAnimation(int anim){
		playerAction = anim;
	}

	public void xDir(float dir){
		this.xDir = dir;
	}

	public void yDir(float dir){
		this.yDir = dir;
	}

	private void updatePos(){
		xDelta += xDir;
		yDelta += yDir;
	}
	
	//Graphics e algo q permite desenhar/colocar imagem no jpanel
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		updateAnimationTick();

		setAnimation(playerAction);
		updatePos();

		g.drawImage(spaceshipAnim[playerAction][animIndex], (int) xDelta, (int) yDelta, null);
	}
}