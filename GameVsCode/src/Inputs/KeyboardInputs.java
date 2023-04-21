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
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_SPACE:
                //gamePanel.getGame().getPlayer().isFlying = true;
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_SPACE:
                gamePanel.getGame().getPlayer().isFlying = false;
            break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_SPACE:
                gamePanel.getGame().getPlayer().isFlying = true;
            break;

            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer().playerState = DEAD;
            break;
        }
    }
    
}