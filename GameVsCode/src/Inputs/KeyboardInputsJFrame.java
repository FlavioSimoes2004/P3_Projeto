package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class KeyboardInputsJFrame implements KeyListener{

    private JFrame jframe;

    public KeyboardInputsJFrame(JFrame jframe){
        this.jframe = jframe;
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

            break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_ESCAPE:
                jframe.dispose();
                System.exit(1);
            break;
        }
    }
}