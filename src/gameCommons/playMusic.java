package gameCommons;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class playMusic {

    public playMusic()
    {
        try
        {
            FileInputStream fileInputStream = new FileInputStream("music.mp3");
            Player player = new Player(fileInputStream);
            player.play();
            System.out.println("Song is playing");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

}
