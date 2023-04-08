package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;

import static utils.Constants.PlayerConstants.*;

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
                //System.out.println("Caindo");
            break;

            case KeyEvent.VK_W:
                gamePanel.yDir(0);
            break;

            case KeyEvent.VK_S:
                gamePanel.yDir(0);
            break;

            case KeyEvent.VK_D:
                gamePanel.xDir(0);
            break;

            case KeyEvent.VK_A:
                gamePanel.xDir(0);
            break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_SPACE:
                gamePanel.setAnimation(DEAD);
            break;

            case KeyEvent.VK_W:
                gamePanel.yDir(-5);
            break;

            case KeyEvent.VK_S:
                gamePanel.yDir(5);
            break;

            case KeyEvent.VK_D:
                gamePanel.xDir(5);
            break;

            case KeyEvent.VK_A:
                gamePanel.xDir(-5);
            break;
        }
    }
    
}