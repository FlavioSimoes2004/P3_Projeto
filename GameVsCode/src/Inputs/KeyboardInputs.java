package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;

public class KeyboardInputs implements KeyListener{

    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_SPACE:
                System.out.println("Caindo");
            break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_SPACE:
                gamePanel.changeY(-5);
                System.out.println("Voando");
            break;

            case KeyEvent.VK_W:
                gamePanel.changeY(-5);
                System.out.println("W");
            break;

            case KeyEvent.VK_S:
                gamePanel.changeY(5);
                System.out.println("S");
            break;

            case KeyEvent.VK_D:
                gamePanel.changeX(5);
                System.out.println("D");
            break;

            case KeyEvent.VK_A:
                gamePanel.changeX(-5);
                System.out.println("A");
            break;
        }
    }
    
}