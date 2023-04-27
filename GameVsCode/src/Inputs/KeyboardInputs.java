package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entities.Player;
import main.GamePanel;

import static utils.Constants.PlayerConstants.*;

public class KeyboardInputs implements KeyListener{

    private GamePanel gamePanel;
    private Player player;

    public KeyboardInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        player = gamePanel.getGame().getPlayer();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_E:
                
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
                player.isFlying = true;
            break;

            case KeyEvent.VK_E:
                if(player.playerState != DEAD)
                {
                    player.playerState = EGG;
                }
            break;
        }
    }
    
}