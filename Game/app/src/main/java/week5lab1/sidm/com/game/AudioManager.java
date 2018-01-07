package week5lab1.sidm.com.game;

import android.media.MediaPlayer;
import android.view.SurfaceView;
import java.util.HashMap;

/**
 * Created by Wafieqa on 1/7/2018.
 */

public class AudioManager
{
    //Singleton
    public final static AudioManager Instance = new AudioManager();

    private SurfaceView view = null;
    private HashMap<Integer, MediaPlayer> audioMap = new HashMap<Integer, MediaPlayer>();

    //protect the singleton :u
    private AudioManager()
    {
    }

    public void Init(SurfaceView _view)
    {
        view = _view;
    }

    public void PlayAudio(int _id)
    {
        // Check if the audio is loaded or not
        if (audioMap.containsKey(_id))
        {
            // We got it!
            MediaPlayer curr = audioMap.get(_id);
            curr.reset();
            curr.start();
        }

        // We do not have the resource loaded :P
        // Have to load and put it into our audio map
        MediaPlayer newAudio = MediaPlayer.create(view.getContext(), _id);
        audioMap.put(_id, newAudio);

        newAudio.start(); // Just play the audio immediately
    }

    public boolean IsPlaying()
    {
        return false;
    }
}
