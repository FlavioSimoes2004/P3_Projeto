package main;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class GameWindow {
	//O JFrame pode ser colocado tmb extends
	//JFrame e como a moldura de uma pintura, nao se desenha nela
	private JFrame jframe;
	
	//Coloca JPanel como parametro, para aparecer na janela, sem
	//a moldura nao aparece nada na pintura
	public GameWindow(GamePanel gamePanel) {
		jframe = new JFrame();
		jframe.setTitle("Jogo");
		//Sem o setDefaultCloseOperation setado, quando clicar no botao de fechar, apenas a janela fecha, nao o programa
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Colocando a pintura dentro da moldura
		jframe.add(gamePanel);
		//para a janela aparecer no meio da tela
		jframe.setLocationRelativeTo(null);

		jframe.setResizable(false);

		//
		jframe.pack();
		
		//Por padrao, o setVisible padrao e false, colocar ele no final
		jframe.setVisible(true);
	}
}