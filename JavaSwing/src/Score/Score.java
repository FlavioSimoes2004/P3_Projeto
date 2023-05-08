package Score;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Score {
    private int actualScore = 0;
    private int maxScore = 0;
    private int pos = 75;

    public void reset(){
        if(actualScore > maxScore)
        {
            maxScore = actualScore;
        }
        actualScore = 0;
    }

    public void increase(){
        actualScore++;
    }

    public void render(Graphics g){
        String txt1 = "Score: " + actualScore;
        String txt2 = "Max Score: " + maxScore;
        g.setColor(Color.WHITE);
        int fontSize = 20;
        Font f = new Font("Comic Sans MS", Font.PLAIN, fontSize);
        g.setFont(f);
        g.drawString(txt1, pos, pos);
        g.drawString(txt2, pos, pos + 25);
    }
}