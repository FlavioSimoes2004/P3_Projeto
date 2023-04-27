package audio;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
    private Clip explosion;

    public AudioPlayer(){
        explosion = getClip("explosion");
    }

    private Clip getClip(String name){
        File file = new File("GameVsCode/res/" + name + ".wav");
        AudioInputStream audio;

        try
        {
            audio = AudioSystem.getAudioInputStream(file);
            Clip c = AudioSystem.getClip();
            c.open(audio);
            return c;
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public void playAudio(String audio){
        if("explosion".equals(audio))
        {
            explosion.setMicrosecondPosition(0);
            explosion.start();
        }
    }

    public void stopAudio(String audio){
        if("explosion".equals(audio))
        {
            explosion.stop();
        }
    }
}